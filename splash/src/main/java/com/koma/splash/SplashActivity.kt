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

package com.koma.splash

import android.Manifest
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.Postcard
import com.koma.home.api.PATH_HOME_ACTIVITY
import com.koma.permisson.RequestPermissionActivity
import com.koma.router.NavigatorCallback
import com.koma.router.Router
import com.koma.splash.databinding.SplashActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : RequestPermissionActivity() {
    private lateinit var binding: SplashActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("onCreate")

        binding = DataBindingUtil.setContentView(this, R.layout.splash_activity_splash)
        binding.lifecycleOwner = this

        initPermissions()
    }

    private fun initPermissions() {
        if (isPermissionGranted()) {
            launchMainPage()
        } else {
            requestPermission()
        }
    }

    @AfterPermissionGranted(PERMISSION_REQUEST_CODE)
    private fun launchMainPage() {
        if (isPermissionGranted()) {
            Router.build(PATH_HOME_ACTIVITY)
                .withTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                .navigation(this, object : NavigatorCallback() {
                    override fun onArrival(postcard: Postcard?) {
                        finish()
                    }
                })
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        EasyPermissions.requestPermissions(
            this,
            getString(R.string.splash_app_name),
            PERMISSION_REQUEST_CODE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    private fun isPermissionGranted(): Boolean {
        return EasyPermissions.hasPermissions(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }
}
