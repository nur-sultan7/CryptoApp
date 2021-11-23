package com.nursultan.cryptoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nursultan.cryptoapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var bindingVIew: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingVIew = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bindingVIew.root)
        setClickListeners()

    }
    private fun setClickListeners()
    {
        bindingVIew.tvRegistrationBtn.setOnClickListener {
            val intent = Intent(applicationContext, RegistrationActivity::class.java)
            startActivity(intent)
        }
        bindingVIew.btnLogin.setOnClickListener {
            val intent = Intent(this, CoinPriceListActivity::class.java)
            startActivity(intent)
        }
    }
}