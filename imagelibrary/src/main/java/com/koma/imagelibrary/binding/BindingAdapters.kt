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

package com.koma.imagelibrary.binding

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.koma.imagelibrary.GlideApp

private const val IMAGE_URL = "http://image.tmdb.org/t/p/w342"

@BindingAdapter(value = ["imageUrl"])
fun bindImageUrl(view: ImageView, imageUrl: String?) {
    GlideApp.with(view.context)
        .load("$IMAGE_URL$imageUrl")
        .placeholder(ColorDrawable(Color.GRAY))
        .transition(DrawableTransitionOptions.withCrossFade())
        .thumbnail(0.1f)
        .into(view)
}
