package com.example.news.model

data class KategoriResponse(
	val data: List<DataItemKategori?>? = null
)

data class DataItemKategori(
	val name: String? = null,
	val id: Int? = null
)