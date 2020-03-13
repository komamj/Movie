package com.koma.movie.data.source.local

import com.nhaarman.mockitokotlin2.mock
import org.junit.Before

class LocalDataSourceTest {
    private lateinit var dataSource: LocalDataSource

    private val preferenceHelper: PreferenceHelper = mock()

    private val movieDao: MovieDao = mock()

    @Before
    fun `init`() {
        dataSource = LocalDataSource(preferenceHelper, movieDao)
    }
}