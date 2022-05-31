package com.example.news

import android.annotation.SuppressLint
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
import com.example.news.adapter.SignedNewsAdapter
import com.example.news.databinding.ActivityDashboardBinding
import com.example.news.library.snackbar
import com.example.news.model.AuthResponse
import com.example.news.model.DataItemNewsAnonim
import com.example.news.model.NewsAnonimResponse
import com.example.news.presenter.AuthPresenter
import com.example.news.presenter.AuthView
import com.example.news.presenter.DashboardPresenter
import com.example.news.presenter.DashboardView
import com.example.news.storage.SessionManager

class DashboardActivity : AppCompatActivity(), AuthView, DashboardView {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var signedNewsAdapter: SignedNewsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var authPresenter = AuthPresenter(this, this)
    private var dashboardPresenter = DashboardPresenter(this, this)
    private var dataItemNewsAnonim = mutableListOf<DataItemNewsAnonim>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sessionManager = SessionManager(this)

        binding.root.snackbar("Hello, "+sessionManager.getNama())

        swipeRefreshLayout = binding.refresh
        dashboardPresenter.getAllNews(sessionManager.getToken()!!)

        swipeRefreshLayout.setOnRefreshListener {
            dataItemNewsAnonim.clear()
            dashboardPresenter.getAllNews(sessionManager.getToken()!!)
            Handler().postDelayed(Runnable {
                swipeRefreshLayout.isRefreshing = false
            }, 2000)
        }

        binding.createNews.setOnClickListener {
            startActivity(Intent(this, CreateNewsActivity::class.java))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.option_menu_logout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.logout_menu->{
                authPresenter.logout(sessionManager.getToken()!!)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSuccessLogout(message: String) {
        binding.root.snackbar(message)
        Handler().postDelayed({
            sessionManager.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 4000)
    }

    override fun onBadRequest(message: String) {
        binding.root.snackbar(message)
        Handler().postDelayed({
            sessionManager.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 4000)
    }

    override fun onSuccessGetNews(newsAnonimResponse: NewsAnonimResponse) {
        dataItemNewsAnonim = newsAnonimResponse.data as MutableList<DataItemNewsAnonim>
        val recyclerView = binding.recycleViewData
        signedNewsAdapter = SignedNewsAdapter(this, dashboardPresenter)
        recyclerView.adapter = signedNewsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        signedNewsAdapter.setDataList(dataItemNewsAnonim)
    }

    override fun onBadRequestData(message: String) {
        binding.root.snackbar(message)
        Handler().postDelayed({
            sessionManager.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 4000)
    }

    override fun onSuccessDeleteNews(message: String) {
        binding.root.snackbar(message)
        dataItemNewsAnonim.clear()
        dashboardPresenter.getAllNews(sessionManager.getToken()!!)
    }

    override fun onSuccessRegister(message: String) {}
    override fun onSuccessLogin(authResponse: AuthResponse) {}
}