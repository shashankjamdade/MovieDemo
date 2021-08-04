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
import com.demo.util.Constants
import kotlinx.android.synthetic.main.item_movie.view.*
import javax.inject.Inject

class MovieListAdapter(
    var movieList: ArrayList<MovieResultsItem>,
    var context: Context,
    var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder =
        MoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movieList[position])

        holder.itemView.text_book_now.setOnClickListener {
            itemClickListener.setClickedInfo(
                position,
                holder.itemView.text_book_now,
                movieList?.get(position)
            )
        }
        holder.itemView.rl_item_layout.setOnClickListener {
            itemClickListener.setClickedInfo(
                position,
                holder.itemView.rl_item_layout,
                movieList?.get(position)
            )
        }
    }

    fun refreshAdapter(movies: List<MovieResultsItem>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    inner class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mView = view
        fun bind(movie: MovieResultsItem) {
            mView.text_movienname.text = movie?.title
            mView.text_release_date.text =
                CommonUtils.changeDateFormat(movie?.releaseDate, "yyyy-MM-dd", "dd MMM yyyy")
            Glide.with(context).load(CommonUtils.getImgPath(movie?.posterPath!!, 200))
                .thumbnail(Glide.with(context).load(R.drawable.placeholder))
                .fitCenter()
                .into(mView.imageView)
        }
    }
}