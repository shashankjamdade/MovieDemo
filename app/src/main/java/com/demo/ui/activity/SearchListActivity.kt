package com.demo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.demo.R
import com.demo.db.dao.MovieDao
import com.demo.listener.ItemClickListener
import com.demo.model.response.GetMovieListResponse
import com.demo.model.response.MovieResultsItem
import com.demo.ui.adapter.MovieListAdapter
import com.demo.util.CommonUtils
import com.demo.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.common_toolbar.*
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.layout_progress.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchListActivity : AppCompatActivity(), ItemClickListener {

    private var movieListAdapter: MovieListAdapter? = null
    private val viewModel: UserViewModel by viewModels()
    private var mGetMovieListResponse: GetMovieListResponse? = null
    private var searchList: ArrayList<MovieResultsItem>? = arrayListOf()


    @Inject lateinit var movieDao:MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        image_back?.visibility = View.VISIBLE
        image_search?.visibility = View.GONE
        image_back?.setOnClickListener {
            finish()
        }
        initAdapter()
        if(intent?.hasExtra("res")!!){
            mGetMovieListResponse = intent?.getParcelableExtra("res")
            mGetMovieListResponse?.let {
//                setDataToUI(it?.results)
            }
        }
        edt_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchList?.clear()
                if (s.toString().length!! > 0) {
                    text_recent_search_lable.visibility = View.GONE
                    mGetMovieListResponse?.results?.forEach {
                        if(it?.originalTitle?.toLowerCase()?.contains(s?.toString()?.trim()!!)!!){
                            searchList?.add(it)
                        }
                    }
                    setDataToUI(searchList)
                } else {
                    searchList?.clear()
                    fetchAlreadySearchMovieFromDb()
                }
            }
        })

        fetchAlreadySearchMovieFromDb()

    }

    fun fetchAlreadySearchMovieFromDb(){
        movieDao?.loadMovieList()?.observe(this, object : Observer<List<MovieResultsItem>> {
            override fun onChanged(@Nullable itemlist: List<MovieResultsItem>?) {
                if(itemlist!=null && itemlist?.size>0){
                    CommonUtils.printLog("DATARETR----> ", "${Gson().toJson(itemlist)}")
                    text_recent_search_lable.visibility = View.VISIBLE
                    setDataToUI(itemlist)
                }
            }
        })
    }

    /**
     * Initialize adapter
     */
    private fun initAdapter() {
        movieListAdapter = MovieListAdapter(
            arrayListOf(),
            this@SearchListActivity,
            this@SearchListActivity
        )
        rv_movies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieListAdapter
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
            var intent = Intent(this@SearchListActivity, MovieDetailActivity::class.java)
            intent?.putExtra("movie", obj)
            startActivity(intent)
        }
    }
    override fun setClickedInfo(data: Any?) {

    }

}