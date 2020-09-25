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

package com.koma.auth.data.source

import com.google.firebase.auth.FirebaseUser
import com.koma.auth.data.entities.User
import com.koma.common.data.entities.Resource

interface AuthRepository {
    suspend fun login(email: String, password: String): Resource<FirebaseUser?>

    fun persistenceUser(user: User)

    fun logout()

    fun getUser(): FirebaseUser?
}
