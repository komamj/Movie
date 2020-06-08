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
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.koma.movie.R
import com.koma.movie.databinding.ItemHomeBinding
import com.koma.movie.home.entities.HomeModel

class HomeAdapter : ListAdapter<HomeModel, HomeAdapter.HomeVH>(HomeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVH {
        return HomeAdapter.HomeVH(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_home,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeVH, position: Int) {
        bind(holder, position)
    }

    private fun bind(viewHolder: HomeVH, position: Int) {
        val homeModel = getItem(position)
        viewHolder.binding.homeModel = homeModel
        viewHolder.binding.clickListener = createOnClickListener(homeModel)
        viewHolder.binding.executePendingBindings()
    }

    private fun createOnClickListener(homeModel: HomeModel): View.OnClickListener {
        return View.OnClickListener {
            // todo launch detail page.
        }
    }

    class HomeVH(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)

    private class HomeDiffCallback : DiffUtil.ItemCallback<HomeModel>() {
        override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
            return false
        }
    }
}
