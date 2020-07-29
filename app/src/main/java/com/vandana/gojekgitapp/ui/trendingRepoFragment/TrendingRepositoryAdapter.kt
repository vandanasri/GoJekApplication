package com.vandana.gojekgitapp.ui.trendingRepoFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vandana.gojekgitapp.R
import com.vandana.gojekgitapp.data.model.TrendingRepositoryData
import kotlinx.android.synthetic.main.item_recyclerview.view.*
import kotlinx.android.synthetic.main.item_rv_expand.view.*
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class TrendingRepositoryAdapter (private var trendingRepositoryDataList: List<TrendingRepositoryData>) :
    RecyclerView.Adapter<TrendingRepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
        return ViewHolder(parent.context, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun getItemCount(): Int {
        return trendingRepositoryDataList.size
    }

    private fun getItem(position: Int): TrendingRepositoryData {
        return trendingRepositoryDataList[position]
    }

    class ViewHolder(private var mContext: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        fun bind(value: TrendingRepositoryData, position: Int) {
            itemView.tvTextName.text = value.author
            itemView.rvTextRepository.text = value.name
            Glide.with(itemView.context).load(value.avatar).into(itemView.rvImage)
            itemView.rvExpandable.visibility = View.GONE

            itemView.setOnClickListener {
                if(itemView.rvExpandable.visibility == View.GONE) {
                    itemView.rvExpandable.visibility = View.VISIBLE
                    it.tvDetail.text = value.description
                    it.tvLanguage.text = value.language
                    it.tvStarsNumber.text = value.stars.toString()
                    it.tvFork.text = value.forks.toString()
                }else{
                    itemView.rvExpandable.visibility = View.GONE
                }
            }


        }
    }

}