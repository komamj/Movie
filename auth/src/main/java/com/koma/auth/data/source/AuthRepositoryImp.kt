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

import androidx.annotation.NonNull
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.koma.auth.data.entities.User
import com.koma.common.data.entities.Resource
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

@Singleton
class AuthRepositoryImp @Inject constructor(
    @NonNull private val auth: FirebaseAuth,
    @NonNull private val database: DatabaseReference
) :
    AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Resource<FirebaseUser?> {
        return withContext(context = Dispatchers.IO) {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
                val user = auth.currentUser
                user?.run {
                    persistenceUser(buildUser(user))
                }
                return@withContext Resource.Success(user)
            } catch (exception: FirebaseAuthException) {
                return@withContext Resource.Error(exception)
            }
        }
    }

    private fun buildUser(firebaseUser: FirebaseUser): User {
        return User(
            id = firebaseUser.uid,
            name = firebaseUser.displayName,
            photoUrl = ""
        )
    }

    override fun persistenceUser(user: User) {
    }

    override fun logout() {
        auth.signOut()
    }

    override fun getUser(): FirebaseUser? {
        return auth.currentUser
    }
}
