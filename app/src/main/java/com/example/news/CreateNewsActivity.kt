package com.example.news

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.news.databinding.ActivityCreateNewsBinding
import com.example.news.model.KategoriResponse
import com.example.news.presenter.CreateNewsPresenter
import com.example.news.presenter.CreateNewsView

class CreateNewsActivity : AppCompatActivity(), CreateNewsView {
    private lateinit var binding: ActivityCreateNewsBinding
    private var createNewsPresenter = CreateNewsPresenter(this, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreateNewsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        createNewsPresenter.getkategori()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSuccessGetKategori(kategoriResponse: KategoriResponse) {
        val spinner = binding.kategori
        val arr = ArrayList<String>()
        for(item in kategoriResponse.data!!){
            arr.add(item?.id.toString()+"|"+item?.name.toString())
        }
        val adapterSpinner = ArrayAdapter(this, R.layout.simple_spinner_item, arr)
        spinner.adapter = adapterSpinner
    }


}