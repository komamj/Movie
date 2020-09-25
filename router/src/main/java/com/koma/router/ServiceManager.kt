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
