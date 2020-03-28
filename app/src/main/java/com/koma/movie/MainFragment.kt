package com.koma.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.koma.commonlibrary.base.BaseFragment
import com.koma.movie.databinding.FragmentMainBinding
import com.koma.movie.widget.ZoomOutPageTransformer

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        init()
    }

    private fun init() {
        with(binding) {
            (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

            viewPager.setPageTransformer(ZoomOutPageTransformer())
            viewPager.offscreenPageLimit = 2
            viewPager.adapter = MainAdapter(this@MainFragment)
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    when (position) {
                        1 -> {
                            bottomNavigationView.selectedItemId = R.id.nav_tv
                        }
                        2 -> {
                            bottomNavigationView.selectedItemId = R.id.nav_people
                        }
                        else -> {
                            bottomNavigationView.selectedItemId = R.id.nav_home
                        }
                    }
                }
            })

            bottomNavigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_home -> {
                        viewPager.setCurrentItem(0, true)
                        true
                    }
                    R.id.nav_tv -> {
                        viewPager.setCurrentItem(1, true)
                        true
                    }
                    R.id.nav_people -> {
                        viewPager.setCurrentItem(2, true)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun getLayoutId() = R.layout.fragment_main
}