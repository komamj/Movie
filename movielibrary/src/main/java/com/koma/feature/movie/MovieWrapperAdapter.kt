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

package com.koma.feature.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.koma.feature.movie.data.entities.MovieWrapper
import com.koma.feature.movie.databinding.ItemMovieWrapperBinding

class MovieWrapperAdapter :
    ListAdapter<MovieWrapper, MovieWrapperAdapter.HomeVH>(HomeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        return HomeVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie_wrapper,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        bind(holder, position)
    }

    private fun bind(viewHolder: HomeVH, position: Int) {
        val movieWrapper = getItem(position)
        viewHolder.binding.movieWrapper = movieWrapper
        viewHolder.binding.clickListener = createOnClickListener(movieWrapper)
        viewHolder.binding.executePendingBindings()
    }

    private fun createOnClickListener(movieWrapper: MovieWrapper): View.OnClickListener {
        return View.OnClickListener {
            // todo launch detail page.
        }
    }

    class HomeVH(val binding: ItemMovieWrapperBinding) : RecyclerView.ViewHolder(binding.root)

    private class HomeDiffCallback : DiffUtil.ItemCallback<MovieWrapper>() {
        override fun areItemsTheSame(oldItem: MovieWrapper, newItem: MovieWrapper): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: MovieWrapper, newItem: MovieWrapper): Boolean {
            return false
        }
    }
}
