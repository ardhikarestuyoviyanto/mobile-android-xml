package com.example.news.presenter

import android.app.ProgressDialog
import android.content.Context
import com.example.news.config.NetworkConfig
import com.example.news.model.AuthResponse
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Response

class AuthPresenter(val authView: AuthView, private val context: Context) {

    private val gson = Gson()

    fun register(nama:String, email:String, password:String, alamat:String){

        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Mohon Tunggu Sebentar ...")
        progressDialog.show()

        NetworkConfig.getService()
            .register(nama, email, password, alamat)
            .enqueue(object : retrofit2.Callback<AuthResponse>{
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    when(response.code()){
                        200->{
                            response.body()?.message?.let { authView.onSuccessRegister(it) }
                        }
                    }
                    if(!response.isSuccessful){
                        val type = object : TypeToken<AuthResponse>(){}.type
                        val errorRes: AuthResponse? = gson.fromJson(response.errorBody()!!.charStream(), type)
                        errorRes?.message?.let { authView.onBadRequest(it) }
                    }
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    authView.onBadRequest(t.localizedMessage)
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

            })


    }

    fun login(email:String, password: String){

        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Mohon Tunggu Sebentar ...")
        progressDialog.show()

        NetworkConfig.getService()
            .login(email, password)
            .enqueue(object : retrofit2.Callback<AuthResponse>{
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    when{
                        response.code() == 200->{
                            response.body()?.let { authView.onSuccessLogin(it) }
                        }
                    }
                    if(!response.isSuccessful){
                        val type = object : TypeToken<AuthResponse>(){}.type
                        val errorRes: AuthResponse? = gson.fromJson(response.errorBody()!!.charStream(), type)
                        errorRes?.message?.let { authView.onBadRequest(it) }
                    }
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    authView.onBadRequest(t.localizedMessage)
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

            })

    }

    fun logout(token:String){

        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Mohon Tunggu Sebentar ...")
        progressDialog.show()

        NetworkConfig.getService()
            .logout(token)
            .enqueue(object : retrofit2.Callback<AuthResponse>{
                override fun onResponse(
                    call: Call<AuthResponse>,
                    response: Response<AuthResponse>
                ) {
                    when{
                        response.code() == 200->{
                            response.body()?.message?.let { authView.onSuccessLogout(it) }
                        }
                        response.code() == 401->{
                            authView.onBadRequest("Session Invalid, Please Back Login")
                        }
                    }
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                    authView.onBadRequest(t.localizedMessage)
                    progressDialog.hide()
                    progressDialog.dismiss()
                }

            })

    }

}