package com.example.news.presenter

import com.example.news.model.AuthResponse

interface AuthView {
    fun onSuccessRegister(message:String)
    fun onSuccessLogin(authResponse: AuthResponse)
    fun onSuccessLogout(message: String)
    fun onBadRequest(message:String)
}