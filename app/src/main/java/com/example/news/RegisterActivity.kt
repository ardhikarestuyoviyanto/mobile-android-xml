package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.news.databinding.ActivityRegisterBinding
import com.example.news.library.snackbar
import com.example.news.model.AuthResponse
import com.example.news.presenter.AuthPresenter
import com.example.news.presenter.AuthView

class RegisterActivity : AppCompatActivity(), AuthView {
    private lateinit var binding: ActivityRegisterBinding
    private var authPresenter = AuthPresenter(this, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            when {
                binding.nama.text.toString().isEmpty()->{
                    binding.root.snackbar("masukkan nama anda dahulu")
                }
                binding.email.text.toString().isEmpty()->{
                    binding.root.snackbar("masukkan email anda dahulu")
                }
                binding.password.toString().isEmpty()->{
                    binding.root.snackbar("masukkan password anda dahulu")
                }
                binding.alamat.toString().isEmpty()->{
                    binding.root.snackbar("masukkan alamat anda dahulu")
                }
                else->{
                    authPresenter.register(
                        binding.nama.text.toString(),
                        binding.email.text.toString(),
                        binding.password.text.toString(),
                        binding.alamat.text.toString()
                    )
                }
            }
        }
    }

    override fun onSuccessRegister(message: String) {
        binding.root.snackbar(message)
    }

    override fun onBadRequest(message: String) {
        binding.root.snackbar(message)
    }

    override fun onSuccessLogin(authResponse: AuthResponse) {}
    override fun onSuccessLogout(message: String) {}

}