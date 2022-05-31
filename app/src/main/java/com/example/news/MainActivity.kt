package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.news.adapter.AnonimNewsAdapter
import com.example.news.databinding.ActivityMainBinding
import com.example.news.library.snackbar
import com.example.news.model.DataItemNewsAnonim
import com.example.news.model.NewsAnonimResponse
import com.example.news.presenter.MainPresenter
import com.example.news.presenter.MainView

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var anonimNewsAdapter: AnonimNewsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var mainPresenter = MainPresenter(this, this)
    private var dataItemNewsAnonim = mutableListOf<DataItemNewsAnonim>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainPresenter.getallnews()
        swipeRefreshLayout = binding.refresh
        swipeRefreshLayout.setOnRefreshListener {
            dataItemNewsAnonim.clear()
            mainPresenter.getallnews()
            Handler().postDelayed(Runnable {
                swipeRefreshLayout.isRefreshing = false
            }, 2000)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.option_menu_auth, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.login_menu->{
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.register_menu->{
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSuccessGet(newsAnonimResponse: NewsAnonimResponse) {
        dataItemNewsAnonim = newsAnonimResponse.data as MutableList<DataItemNewsAnonim>
        val recyclerView = binding.recycleViewData
        anonimNewsAdapter = AnonimNewsAdapter(this)
        recyclerView.adapter = anonimNewsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        anonimNewsAdapter.setDataList(dataItemNewsAnonim)
    }

    override fun onFailedGet(message: String) {
        binding.root.snackbar(message)
    }
}