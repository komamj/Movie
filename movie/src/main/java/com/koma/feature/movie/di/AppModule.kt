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

package com.koma.feature.movie.di

import android.app.Application
import androidx.room.Room
import com.koma.feature.movie.BuildConfig
import com.koma.feature.movie.data.source.MovieRepository
import com.koma.feature.movie.data.source.MovieRepositoryImp
import com.koma.feature.movie.data.source.local.MovieDao
import com.koma.feature.movie.data.source.local.MovieDatabase
import com.koma.feature.movie.data.source.remote.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.ENDPOINT)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(
            application,
            MovieDatabase::class.java, "movie_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFilmDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }

    @Singleton
    @Provides
    fun provideWebService(retrofit: Retrofit): WebService {
        return retrofit.create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(movieRepository: MovieRepositoryImp): MovieRepository {
        return movieRepository
    }
}
