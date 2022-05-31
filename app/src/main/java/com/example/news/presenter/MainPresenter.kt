package com.example.news.presenter

import android.content.Context
import com.example.news.config.NetworkConfig
import com.example.news.model.NewsAnonimResponse
import retrofit2.Call
import retrofit2.Response

class MainPresenter(private val context: Context, val mainView: MainView) {

    fun getallnews(){
        NetworkConfig.getService()
            .getallnews()
            .enqueue(object : retrofit2.Callback<NewsAnonimResponse>{
                override fun onResponse(
                    call: Call<NewsAnonimResponse>,
                    response: Response<NewsAnonimResponse>
                ) {
                    if(response.code() == 200){
                        response.body()?.let { mainView.onSuccessGet(it) }
                    }else{
                        mainView.onFailedGet("Internal Server Error")
                    }
                }

                override fun onFailure(call: Call<NewsAnonimResponse>, t: Throwable) {
                    mainView.onFailedGet(t.localizedMessage)
                }

            })
    }

}