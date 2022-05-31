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

class AnonimNewsAdapter(val context: Context):RecyclerView.Adapter<AnonimNewsAdapter.MyHolder>() {

    var dataList = emptyList<DataItemNewsAnonim>()

    internal fun setDataList(dataList: List<DataItemNewsAnonim>){
        this.dataList = dataList
    }

    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.findViewById(R.id.anonim_news_image)
        var title: TextView = itemView.findViewById(R.id.anonim_news_title)
        var author: TextView = itemView.findViewById(R.id.anonim_news_author)
        var kategori: TextView = itemView.findViewById(R.id.anonim_news_kategori)
        var like: TextView = itemView.findViewById(R.id.anonim_like_total)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.list_anonim_news, parent, false)
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
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}