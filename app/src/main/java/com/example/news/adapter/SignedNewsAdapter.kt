package com.example.news.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.model.DataItemNewsAnonim
import com.example.news.presenter.DashboardPresenter
import com.example.news.storage.SessionManager

class SignedNewsAdapter(val context: Context, val dashboardPresenter: DashboardPresenter):RecyclerView.Adapter<SignedNewsAdapter.MyHolder>() {

    var dataList = emptyList<DataItemNewsAnonim>()
    val sessionManager = SessionManager(context)

    internal fun setDataList(dataList: List<DataItemNewsAnonim>){
        this.dataList = dataList
    }

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.findViewById(R.id.signed_news_image)
        var title: TextView = itemView.findViewById(R.id.signed_news_title)
        var author: TextView = itemView.findViewById(R.id.signed_news_author)
        var kategori: TextView = itemView.findViewById(R.id.signed_news_kategori)
        var like: TextView = itemView.findViewById(R.id.signed_like_total)
        var update: TextView = itemView.findViewById(R.id.signed_news_update)
        var delete: TextView = itemView.findViewById(R.id.signed_news_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_signed_news, parent, false)
        return MyHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var listData =dataList[position]

        Glide.with(holder.itemView.context).load(listData.gambar).into(holder.image)
        holder.title.text = listData.judul
        holder.author.text = "By  "+listData.author_name
        holder.kategori.text = listData.kategori_name
        holder.like.text = "| total like : "+listData.total_like
        holder.title.setOnClickListener {

        }
        holder.update.setOnClickListener {

        }
        holder.delete.setOnClickListener {
            listData.id?.let { it1 ->
                dashboardPresenter.deletenews(
                    sessionManager.getToken()!!,
                    it1
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}