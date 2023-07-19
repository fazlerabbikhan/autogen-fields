package com.fazlerabbikhan.autogenfields.data.remote

import com.fazlerabbikhan.autogenfields.data.remote.user_detail_dto.UserDetailDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("users/{id}")
    suspend fun getUserData(@Path("id") userId: Int): UserDetailDto

    @PUT("users/{id}")
    suspend fun updateUserData(@Path("id") userId: Int, @Body userDetailDto: UserDetailDto): UserDetailDto
}