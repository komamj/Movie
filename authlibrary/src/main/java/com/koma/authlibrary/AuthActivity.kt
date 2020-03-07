package com.koma.authlibrary

import android.content.Intent
import android.os.Bundle
import com.koma.authlibrary.databinding.ActivityAuthBinding
import com.koma.authlibrary.login.LoginActivity
import com.koma.authlibrary.register.RegisterActivity
import com.koma.commonlibrary.base.BaseActivity

class AuthActivity : BaseActivity<ActivityAuthBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun getLayoutId() = R.layout.activity_auth
}