package com.example.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.news.databinding.ActivityLoginBinding
import com.example.news.library.snackbar
import com.example.news.model.AuthResponse
import com.example.news.presenter.AuthPresenter
import com.example.news.presenter.AuthView
import com.example.news.storage.SessionManager

class LoginActivity : AppCompatActivity(), AuthView {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager:SessionManager
    private var authPresenter = AuthPresenter(this, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sessionManager = SessionManager(this)

        binding.buttonLogin.setOnClickListener {
            when{
                binding.email.text.toString().isEmpty()->{
                    binding.root.snackbar("email masih kosong")
                }
                binding.password.text.toString().isEmpty()->{
                    binding.root.snackbar("password masih kosong")
                }
                else->{
                    authPresenter.login(
                        binding.email.text.toString(),
                        binding.password.text.toString()
                    )
                }
            }
        }
    }

    override fun onSuccessLogin(authResponse: AuthResponse) {
        authResponse.nama?.let { sessionManager.setNama(it) }
        authResponse.token?.let { sessionManager.setToken(it) }
        startActivity(Intent(this, DashboardActivity::class.java))
    }

    override fun onBadRequest(message: String) {
        binding.root.snackbar(message)
    }

    override fun onSuccessRegister(message: String) {}
    override fun onSuccessLogout(message: String) {}

}