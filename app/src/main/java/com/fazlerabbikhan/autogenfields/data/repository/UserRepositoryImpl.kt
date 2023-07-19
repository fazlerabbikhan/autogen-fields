package com.fazlerabbikhan.autogenfields.data.repository

import com.fazlerabbikhan.autogenfields.data.remote.ApiService
import com.fazlerabbikhan.autogenfields.data.remote.user_detail_dto.UserDetailDto
import com.fazlerabbikhan.autogenfields.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: ApiService
) : UserRepository {

    override suspend fun getUserData(userId: Int): UserDetailDto {
        return api.getUserData(userId)
    }

    override suspend fun updateUserData(userId: Int, userDetailDto: UserDetailDto): UserDetailDto {
        return api.updateUserData(userId, userDetailDto)
    }
}