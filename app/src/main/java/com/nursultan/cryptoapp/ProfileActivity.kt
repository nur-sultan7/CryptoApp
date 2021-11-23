package com.nursultan.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.nursultan.cryptoapp.data.User
import com.nursultan.cryptoapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    private lateinit var binding: ActivityProfileBinding
    private val modelView by lazy {
        ViewModelProviders.of(this)[ProfileViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        modelView.getUser("zalina21").observe(this, {
            with(binding)
            {
                edtLogin.setText(it.login)
                edtFirstName.setText(it.firstName)
                edtLastName.setText(it.lastName)
                edtPassword.setText(it.password)
                edtUSDT.setText(it.money.toString())
            }

        })
        addUser()


    }

    private fun addUser() {
        val user = User("Zalina", "Takaeva", "zalina21", "fadsf5445", 5000)
        modelView.insertUser(user)
    }

}