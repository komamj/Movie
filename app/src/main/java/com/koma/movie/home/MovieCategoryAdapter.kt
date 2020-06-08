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

package com.koma.movie.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.koma.movie.R
import com.koma.movie.databinding.ItemMovieCategoryBinding

class MovieCategoryAdapter :
    MovieAdapter<MovieCategoryAdapter.MovieCategoryVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryVH {
        return MovieCategoryVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_category,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieCategoryVH, position: Int) {
        bind(holder, position)
    }

    private fun bind(viewHolder: MovieCategoryVH, position: Int) {
        val movie = getItem(position)
        viewHolder.binding.movie = movie
        viewHolder.binding.clickListener = createOnClickListener(movie)
        viewHolder.binding.executePendingBindings()
    }

    class MovieCategoryVH(val binding: ItemMovieCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}
