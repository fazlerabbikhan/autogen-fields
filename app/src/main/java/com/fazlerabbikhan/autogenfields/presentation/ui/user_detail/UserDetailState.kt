package com.fazlerabbikhan.autogenfields.presentation.ui.user_detail

import com.fazlerabbikhan.autogenfields.domain.model.UserDetail

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: UserDetail? = null,
    val error: String = ""
)