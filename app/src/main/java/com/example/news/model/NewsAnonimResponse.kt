package com.example.news.model

data class NewsAnonimResponse(
	val total: Int? = null,
	val data: List<DataItemNewsAnonim?>? = null
)

data class DataItemNewsAnonim(
	val author_name: String? = null,
	val kategori_id: Int? = null,
	val total_like: Int? = null,
	val tag_name: String? = null,
	val dilihat: Int? = null,
	val id: Int? = null,
	val judul: String? = null,
	val gambar: String? = null,
	val isi: String? = null,
	val kategori_name: String? = null
)