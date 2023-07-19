package com.fazlerabbikhan.autogenfields.domain.repository

import com.fazlerabbikhan.autogenfields.data.remote.user_detail_dto.UserDetailDto

interface UserRepository {
    suspend fun getUserData(userId: Int): UserDetailDto

    suspend fun updateUserData(userId: Int, userDetailDto: UserDetailDto): UserDetailDto
}