package com.demo.ui.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.R
import com.demo.api.ApiName
import com.demo.listener.ItemClickListener
import com.demo.model.response.MovieResultsItem
import com.demo.util.CommonUtils
import kotlinx.android.synthetic.main.item_movie.view.text_release_date
import kotlinx.android.synthetic.main.item_similar_movie.view.*

class SimilarMovieListAdapter(
    var movieList: ArrayList<MovieResultsItem>,
    var context: Context,
    var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<SimilarMovieListAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_similar_movie, parent, false)
        )

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movieList[position])

    }

    fun refreshAdapter(movies: List<MovieResultsItem>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mView = view
        fun bind(movie: MovieResultsItem) {
            mView.text_author.text = movie?.title
            mView.text_release_date.text =
                CommonUtils.changeDateFormat(movie?.releaseDate, "yyyy-MM-dd", "dd MMM yyyy")
            Glide.with(context).load(CommonUtils.getImgPath(movie?.posterPath!!, 200))
                .thumbnail(Glide.with(context).load(R.drawable.placeholder))
                .fitCenter()
                .into(mView.image_movie_poster)
        }
    }
}