package com.example.news.presenter

import android.app.ProgressDialog
import android.content.Context
import com.example.news.config.NetworkConfig
import com.example.news.model.CrudNewsResponse
import com.example.news.model.NewsAnonimResponse
import retrofit2.Call
import retrofit2.Response

class DashboardPresenter(private val context: Context, val dashboardView: DashboardView) {

    fun getAllNews(token:String){
        NetworkConfig.getService()
            .getallnewsusers(token)
            .enqueue(object : retrofit2.Callback<NewsAnonimResponse>{
                override fun onResponse(
                    call: Call<NewsAnonimResponse>,
                    response: Response<NewsAnonimResponse>
                ) {
                    when{
                        response.code() == 200->{
                            response.body()?.let { dashboardView.onSuccessGetNews(it) }
                        }
                        response.code() == 401->{
                            dashboardView.onBadRequestData("Session Expired, Please Back Login")
                        }
                    }
                }

                override fun onFailure(call: Call<NewsAnonimResponse>, t: Throwable) {
                    dashboardView.onBadRequestData(t.localizedMessage)
                }

            })
    }

    fun deletenews(token:String, id:Int){

        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Mohon Tunggu Sebentar ...")
        progressDialog.show()

        NetworkConfig.getService()
            .deletenews(id, token)
            .enqueue(object : retrofit2.Callback<CrudNewsResponse>{
                override fun onResponse(
                    call: Call<CrudNewsResponse>,
                    response: Response<CrudNewsResponse>
                ) {
                    when{
                        response.code() == 200->{
                            response.body()?.message?.let { dashboardView.onSuccessDeleteNews(it) }
                        }
                        response.code() == 401->{
                            dashboardView.onBadRequestData("Session Expired, Please Back Login")
                        }
                    }
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<CrudNewsResponse>, t: Throwable) {
                    dashboardView.onBadRequestData(t.localizedMessage)
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

            })
    }

}