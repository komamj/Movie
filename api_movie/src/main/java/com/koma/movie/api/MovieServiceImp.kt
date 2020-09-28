package com.koma.movie.api

import android.content.Context
import androidx.fragment.app.Fragment
import com.koma.feature.movie.MovieWrapperFragment
import com.koma.router.movie.MovieService
import timber.log.Timber

class MovieServiceImp : MovieService {
    override fun init(context: Context?) {
        Timber.d("init")
    }

    override fun provideMovieFragment(): Fragment {
        return MovieWrapperFragment.newInstance()
    }
}
