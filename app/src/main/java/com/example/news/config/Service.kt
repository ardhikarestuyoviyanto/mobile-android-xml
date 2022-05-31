package com.example.news.config

import com.example.news.model.AuthResponse
import com.example.news.model.CrudNewsResponse
import com.example.news.model.KategoriResponse
import com.example.news.model.NewsAnonimResponse
import retrofit2.Call
import retrofit2.http.*

interface Service {
    // anonim users get all news
    @GET("news")
    fun getallnews():Call<NewsAnonimResponse>

    // register
    @FormUrlEncoded
    @POST("auth/register")
    fun register(
        @Field("nama")nama:String,
        @Field("email")email:String,
        @Field("password")password:String,
        @Field("alamat")alamat:String
    ):Call<AuthResponse>

    // login
    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email")email:String,
        @Field("password")password:String,
    ):Call<AuthResponse>

    // logout
    @GET("auth/logout")
    fun logout(
        @Header("Authorization") token:String
    ):Call<AuthResponse>

    // get news from users
    @GET("user/news")
    fun getallnewsusers(
        @Header("Authorization") token:String
    ):Call<NewsAnonimResponse>

    // delete news
    @DELETE("user/news/{id}")
    fun deletenews(
        @Path("id") id:Int,
        @Header("Authorization") token:String
    ):Call<CrudNewsResponse>

    // get category
    @GET("kategori")
    fun getkategori():Call<KategoriResponse>
}