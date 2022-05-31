package com.example.news.presenter

import com.example.news.model.KategoriResponse

interface CreateNewsView {
    fun onSuccessGetKategori(kategoriResponse: KategoriResponse)
}