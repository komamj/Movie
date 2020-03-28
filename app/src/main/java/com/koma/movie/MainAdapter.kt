package com.koma.movie

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.koma.movie.home.HomeAdapter
import com.koma.movie.home.HomeFragment
import com.koma.movie.home.MovieCategoryFragment

class MainAdapter constructor(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount() = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> {
                HomeFragment()
            }
            2 -> {
                HomeFragment()
            }
            else -> {
                HomeFragment()
            }
        }
    }

    companion object {
        private const val ITEM_COUNT = 3
    }
}