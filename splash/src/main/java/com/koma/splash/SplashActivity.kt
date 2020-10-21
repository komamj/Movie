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
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.koma.common.base.BaseActivity
import com.koma.home.api.PATH_HOME_ACTIVITY
import com.koma.splash.databinding.SplashActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber

@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashActivitySplashBinding>(),
    EasyPermissions.PermissionCallbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("onCreate")

        initPermissions()
    }

    private fun initPermissions() {
        if (isPermissionGranted()) {
            launchMainPage()
        } else {
            requestPermission()
        }
    }

    private fun launchMainPage() {
        ARouter.getInstance().build(PATH_HOME_ACTIVITY)
            .withTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            .navigation(this, object : NavigationCallback {
                override fun onFound(postcard: Postcard?) {
                    finish()
                }

                override fun onLost(postcard: Postcard?) {
                }

                override fun onArrival(postcard: Postcard?) {
                }

                override fun onInterrupt(postcard: Postcard?) {
                }

            })
    }

    private fun requestPermission() {
        EasyPermissions.requestPermissions(
            this,
            getString(R.string.splash_app_name),
            REQUEST_CODE,
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        @NonNull permissions: Array<String>,
        @NonNull grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            // Do something after user returned from app settings screen, like showing a Toast.
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        launchMainPage()
    }

    override fun getLayoutId() = R.layout.splash_activity_splash

    companion object {
        private const val REQUEST_CODE = 100
    }
}
