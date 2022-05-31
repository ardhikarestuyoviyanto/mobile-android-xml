package com.example.news.presenter

import com.example.news.model.NewsAnonimResponse

interface MainView {
    fun onSuccessGet(newsAnonimResponse: NewsAnonimResponse)
    fun onFailedGet(message:String)
}