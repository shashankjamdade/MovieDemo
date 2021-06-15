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
import com.demo.util.CommonUtils
import com.demo.util.Constants
import kotlinx.android.synthetic.main.item_credit.view.*

class CreditAdapter(
    var creditList: ArrayList<CastItem>,
    var context: Context,
    var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<CreditAdapter.CreditViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder =
        CreditViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_credit, parent, false)
        )

    override fun getItemCount(): Int = creditList.size

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        holder.bind(creditList[position])

    }

    fun refreshAdapter(credits: List<CastItem>) {
        creditList.clear()
        creditList.addAll(credits)
        notifyDataSetChanged()
    }

    inner class CreditViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mView = view
        fun bind(credit: CastItem) {
            mView.text_cast_name.text = credit?.originalName
            mView.text_dept.text = credit?.knownForDepartment
            Glide.with(context).load(CommonUtils.getImgPath(credit?.profilePath, 200))
                .thumbnail(Glide.with(context).load(R.drawable.placeholder))
                .fitCenter()
                .into(mView.image_profile)
        }
    }
}