package com.koma.router

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.koma.router.movie.MovieService
import com.koma.router.people.PeopleService
import com.koma.router.tv.TvService

class ServiceManager {
    @Autowired
    lateinit var movieService: MovieService

    @Autowired
    lateinit var peopleService: PeopleService

    @Autowired
    lateinit var tvService: TvService

    init {
        ARouter.getInstance().inject(this)
    }

    fun <T> getService(name: String, clazz: Class<T>): T {
        return ARouter.getInstance().build(name).navigation() as T
    }
}
