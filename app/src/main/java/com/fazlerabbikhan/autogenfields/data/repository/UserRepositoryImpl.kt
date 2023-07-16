package com.fazlerabbikhan.autogenfields.data.repository

import android.util.Log
import com.fazlerabbikhan.autogenfields.data.remote.ApiService
import com.fazlerabbikhan.autogenfields.data.remote.user_detail_dto.UserDetailDto
import com.fazlerabbikhan.autogenfields.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: ApiService
) : UserRepository {

    override suspend fun getUserData(userId: Int): UserDetailDto {
        Log.d("UserRepositoryImpl", "getUserData: Making API request")
        val userDetailDto = api.getUserData(userId)
        Log.d("UserRepositoryImpl", "getUserData: API response received: $userDetailDto")
        return userDetailDto
    }
}
