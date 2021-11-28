package com.nursultan.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class WalletActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
    }
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, WalletActivity::class.java)
        }
    }
}