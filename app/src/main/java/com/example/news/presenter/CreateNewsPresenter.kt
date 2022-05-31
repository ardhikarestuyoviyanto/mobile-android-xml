package com.example.news.presenter

import android.app.ProgressDialog
import android.content.Context
import com.example.news.config.NetworkConfig
import com.example.news.model.KategoriResponse
import retrofit2.Call
import retrofit2.Response

class CreateNewsPresenter(private val context: Context, val createNewsView: CreateNewsView) {

    fun getkategori(){
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Mohon Tunggu Sebentar ...")
        progressDialog.show()

        NetworkConfig.getService()
            .getkategori()
            .enqueue(object : retrofit2.Callback<KategoriResponse>{
                override fun onResponse(
                    call: Call<KategoriResponse>,
                    response: Response<KategoriResponse>
                ) {
                    when{
                        response.code() == 200->{
                            response.body()?.let { createNewsView.onSuccessGetKategori(it) }
                        }
                    }
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<KategoriResponse>, t: Throwable) {
                    t.localizedMessage
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

            })
    }

}