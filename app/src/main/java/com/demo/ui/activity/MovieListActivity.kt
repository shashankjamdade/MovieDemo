package com.demo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.R
import com.demo.db.dao.MovieDao
import com.demo.listener.ItemClickListener
import com.demo.model.response.GetMovieListResponse
import com.demo.model.response.MovieResultsItem
import com.demo.ui.adapter.MovieListAdapter
import com.demo.util.CommonUtils
import com.demo.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movielist.*
import kotlinx.android.synthetic.main.common_toolbar.*
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.layout_progress.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity(), ItemClickListener {

    private var movieListAdapter: MovieListAdapter? = null
    private val viewModel: UserViewModel by viewModels()
    private var mGetMovieListResponse: GetMovieListResponse? = null
    @Inject
    lateinit var movieDao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movielist)
        image_search?.setOnClickListener {
            mGetMovieListResponse?.let {
                var intent = Intent(this@MovieListActivity, SearchListActivity::class.java)
                intent?.putExtra("res", it)
                startActivity(intent)
            }
        }

        initAdapter()
        getMovieList();
    }

    /**
     * Initialize adapter
     */
    private fun initAdapter() {
        movieListAdapter = MovieListAdapter(
            arrayListOf(),
            this@MovieListActivity,
            this@MovieListActivity
        )
        rv_movies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieListAdapter
        }
    }

    /**
     * Fetch movie list from API
     */
    private fun getMovieList() {
        if (CommonUtils.isOnline(this)) {
            rl_progress.visibility = View.VISIBLE
            viewModel.getMovieList().observe(this, Observer {
                it?.let {
                    rl_progress.visibility = View.GONE
                    if (it?.results != null && it?.results?.size!! > 0) {
                        mGetMovieListResponse = it
                        setDataToUI(it?.results)
                    } else {
                        CommonUtils.createSnackBar(
                            findViewById(android.R.id.content),
                            resources?.getString(R.string.no_net)!!
                        )
                    }
                }
            })
        } else {
        }
    }


    fun setDataToUI(itemlist: List<MovieResultsItem>?) {
        if (itemlist != null && itemlist?.size > 0) {
            movieListAdapter?.refreshAdapter(itemlist!!)
        } else {
            CommonUtils.createSnackBar(
                findViewById(android.R.id.content),
                resources?.getString(R.string.no_data)!!
            )
        }
    }

    override fun setClickedInfo(position: Int, view: View?, obj: Any) {
        super.setClickedInfo(position, view, obj)
        if(obj is  MovieResultsItem){
            CoroutineScope(Dispatchers.IO).launch {
                movieDao.insertMovie(obj)
            }
            var intent = Intent(this@MovieListActivity, MovieDetailActivity::class.java)
            intent?.putExtra("movie", obj)
            startActivity(intent)
        }
    }
    override fun setClickedInfo(data: Any?) {

    }

}