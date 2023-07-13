package com.fazlerabbikhan.autogenfields.data.remote.user_detail_dto

import com.fazlerabbikhan.autogenfields.domain.model.UserDetail

data class UserDetailDto(
    val address: String,
    val email: String,
    val id: Int,
    val name: String,
    val outCode: String,
    val outMessage: String,
    val phone: String,
    val username: String
)

fun UserDetailDto.toUserDetail(): UserDetail {
    return UserDetail(
        name = name,
        username = username,
        email = email,
        phone = phone,
        address = address
    )
}

