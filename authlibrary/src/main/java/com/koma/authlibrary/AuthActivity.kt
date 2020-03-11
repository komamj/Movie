/*
 * Copyright 2020 komamj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
