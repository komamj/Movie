package com.koma.router

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.koma.router.movie.MovieService

class ServiceManager {
    @Autowired
    lateinit var movieService: MovieService

    init {
        ARouter.getInstance().inject(this)
    }
}
