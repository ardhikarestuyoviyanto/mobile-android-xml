package com.example.news.presenter

import com.example.news.model.NewsAnonimResponse

interface DashboardView {
    fun onSuccessGetNews(newsAnonimResponse: NewsAnonimResponse)
    fun onBadRequestData(message:String)
    fun onSuccessDeleteNews(message: String)
}