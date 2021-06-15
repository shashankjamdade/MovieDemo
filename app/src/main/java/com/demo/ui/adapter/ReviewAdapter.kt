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
import com.demo.model.response.CastItem
import com.demo.model.response.MovieResultsItem
import com.demo.model.response.ReviewResultsItem
import com.demo.util.CommonUtils
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(
    var reviewlist: ArrayList<ReviewResultsItem>,
    var context: Context,
    var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder =
        ReviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        )

    override fun getItemCount(): Int = reviewlist.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviewlist[position])

    }

    fun refreshAdapter(reviews: List<ReviewResultsItem>) {
        reviewlist.clear()
        reviewlist.addAll(reviews)
        notifyDataSetChanged()
    }

    inner class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mView = view
        fun bind(review:ReviewResultsItem) {
            mView.text_author.text = review?.author
            mView.text_date.text = CommonUtils.changeDateFormat(review?.createdAt, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "dd MMM yyyy")
            mView.text_content.text = review?.content
            if(review?.authorDetails?.avatarPath!=null) {
                Glide.with(context)
                    .load(CommonUtils.getImgPath(review?.authorDetails?.avatarPath, 200))
                    .thumbnail(Glide.with(context).load(R.drawable.placeholder))
                    .fitCenter()
                    .circleCrop()
                    .into(mView.image_logo)
            }
        }
    }
}