package com.demo.ui.activity

import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewTreeObserver.OnScrollChangedListener
import android.view.animation.TranslateAnimation
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.R
import com.demo.listener.ItemClickListener
import com.demo.model.response.*
import com.demo.ui.adapter.CreditAdapter
import com.demo.ui.adapter.ReviewAdapter
import com.demo.ui.adapter.SimilarMovieListAdapter
import com.demo.util.CommonUtils
import com.demo.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movielist.*
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.layout_credits.*
import kotlinx.android.synthetic.main.layout_movie_detail.*
import kotlinx.android.synthetic.main.layout_progress.*
import kotlinx.android.synthetic.main.layout_similar_movies.*


@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity(), ItemClickListener {

    private var movieListAdapter: SimilarMovieListAdapter? = null
    private var creditAdapter: CreditAdapter? = null
    private var reviewAdapter: ReviewAdapter? = null
    private val viewModel: UserViewModel by viewModels()
    private var movieResultsItem: MovieResultsItem? = null
    var isBottomlistVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initAdapter()
//        fetchUserList();
        if (intent?.hasExtra("movie")!!) {
            movieResultsItem = intent?.getParcelableExtra("movie")
            movieResultsItem?.let {
                getMovieDetail(it?.id)
                getMovieCredits(it?.id)
                getMovieReviews(it?.id)
                getSimilarMovieList(it?.id)
            }
        }

        nestedscrollview.getViewTreeObserver().addOnScrollChangedListener(OnScrollChangedListener {
            val view = nestedscrollview.getChildAt(nestedscrollview.getChildCount() - 1) as View
            val diff: Int = view.bottom - (nestedscrollview.getHeight() + nestedscrollview
                .getScrollY())
            CommonUtils.printLog("VIEW_DIFF_VISIBLE", "${diff}")
            if (diff < (rv_similar_movies.height - 50)) {
                if(!isBottomlistVisible) {
                    isBottomlistVisible = true
                    translateViewToLeftAnim(rv_similar_movies)
                }
            }
        })

        /*nestedscrollview?.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (!isBottomlistVisible) {
                var flag = isViewVisible(rv_similar_movies)
                CommonUtils.printLog("VIEW_VISIBLE", "${flag}")
                if (flag) {
                    isBottomlistVisible = true
                    translateViewToLeftAnim(rv_similar_movies)
                }
            }
        }*/

    }

    /**
     * Initialize adapter
     */
    private fun initAdapter() {
        //Credit adapter
        creditAdapter = CreditAdapter(
            arrayListOf(),
            this@MovieDetailActivity,
            this@MovieDetailActivity
        )
        rv_credits.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = creditAdapter
        }

        //Reviews
        reviewAdapter = ReviewAdapter(
            arrayListOf(),
            this@MovieDetailActivity,
            this@MovieDetailActivity
        )
        rv_reviews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reviewAdapter
        }

        //Similar ads
        movieListAdapter = SimilarMovieListAdapter(
            arrayListOf(),
            this@MovieDetailActivity,
            this@MovieDetailActivity
        )
        rv_similar_movies.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = movieListAdapter
        }
    }

    /**
     * Fetch movie detail from API
     */
    private fun getMovieDetail(id: Int) {
        if (CommonUtils.isOnline(this)) {
            rl_progress.visibility = View.VISIBLE
            viewModel.getMovieDetail(movieResultsItem?.id!!).observe(this, Observer {
                it?.let {
                    rl_progress.visibility = View.GONE
                    setDetailToUI(it)
                }
            })
        } else {
        }
    }

    /**
     * Fetch movie credits from API
     */
    private fun getMovieCredits(id: Int) {
        if (CommonUtils.isOnline(this)) {
            rl_progress.visibility = View.VISIBLE
            viewModel.getMovieCredits(movieResultsItem?.id!!).observe(this, Observer {
                it?.let {
                    rl_progress.visibility = View.GONE
                    setCreditListToUI(it)
                }
            })
        } else {
        }
    }

    /**
     * Fetch movie reviews from API
     */
    private fun getMovieReviews(id: Int) {
        if (CommonUtils.isOnline(this)) {
            rl_progress.visibility = View.VISIBLE
            viewModel.getMovieReviews(movieResultsItem?.id!!).observe(this, Observer {
                it?.let {
                    rl_progress.visibility = View.GONE
                    setReviewsToUI(it)
                }
            })
        } else {
        }
    }

    /**
     * Fetch similar movie from API
     */
    private fun getSimilarMovieList(id: Int) {
        if (CommonUtils.isOnline(this)) {
            rl_progress.visibility = View.VISIBLE
            viewModel.getSimilarMovieList(movieResultsItem?.id!!).observe(this, Observer {
                it?.let {
                    rl_progress.visibility = View.GONE
                    setSimilarMoviesToUI(it)
                }
            })
        } else {
        }
    }


    fun setDetailToUI(getMovieDetailResponse: GetMovieDetailResponse) {
        getMovieDetailResponse?.let {
            var geners = ""
            it?.genres?.forEach { gen ->
                geners = geners + if (geners?.length > 0) ", ${gen?.name}" else "${gen?.name}"
            }
            var producers = ""
            it?.productionCompanies?.forEach { gen ->
                producers =
                    producers + if (producers?.length > 0) ", ${gen?.name}" else "${gen?.name}"
            }
            Glide.with(this).load(CommonUtils.getImgPath(it?.posterPath, 400))
                .thumbnail(Glide.with(this).load(R.drawable.placeholder))
                .fitCenter()
                .into(img_poster)
            text_title?.text = it?.title
            text_release_date.text =
                CommonUtils.changeDateFormat(it?.releaseDate, "yyyy-MM-dd", "dd MMM yyyy")
            text_geners?.text = geners
            text_rating?.text = "${it?.voteAverage?.toString()}/10"
            text_producer?.text = producers
            text_revenue?.text = "$${it?.revenue}"
            text_overview?.text = "$${it?.overview}"
        }
    }

    fun setCreditListToUI(getMovieCreditsResponse: GetMovieCreditsResponse) {
        getMovieCreditsResponse?.let {
            creditAdapter?.refreshAdapter(it?.cast!!)
        }
    }

    fun setReviewsToUI(getMovieReviewResponse: GetMovieReviewResponse) {
        getMovieReviewResponse?.let {
            reviewAdapter?.refreshAdapter(it?.results!!)
        }
    }

    fun setSimilarMoviesToUI(getSimilarMovieListResponse: GetSimilarMovieListResponse) {
        getSimilarMovieListResponse?.let {
            movieListAdapter?.refreshAdapter(it?.results!!)
        }
    }

    override fun setClickedInfo(position: Int, view: View?, obj: Any) {
        super.setClickedInfo(position, view, obj)
    }

    override fun setClickedInfo(data: Any?) {
    }

    fun translateViewToLeftAnim(view: View, duration: Long = 1000) {
        val displayMetrics = DisplayMetrics()
        windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        var width = displayMetrics.widthPixels.toFloat()
        CommonUtils.printLog("DEVICE_WIDTH", "${width}")
        var animation: TranslateAnimation = TranslateAnimation(width, 0f, 0f, 0f)
        animation.duration = duration
        animation.repeatCount = 0
        animation.repeatMode = 0
        view?.startAnimation(animation)
    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        nestedscrollview.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        CommonUtils.printLog("TOP-BOTTOM", "HH ${view.height} == ${top}, ${scrollBounds.top} ==== ${bottom}, ${scrollBounds.bottom}")
        return if (scrollBounds.top < top && scrollBounds.bottom > bottom) {
            true //View is visible.
        } else {
            false //View is NOT visible.
        }
    }


}