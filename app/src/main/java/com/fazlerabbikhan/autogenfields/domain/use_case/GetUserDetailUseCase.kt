package com.fazlerabbikhan.autogenfields.domain.use_case

import com.fazlerabbikhan.autogenfields.common.Resource
import com.fazlerabbikhan.autogenfields.data.remote.user_detail_dto.toUserDetail
import com.fazlerabbikhan.autogenfields.domain.model.UserDetail
import com.fazlerabbikhan.autogenfields.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userId: Int): Flow<Resource<UserDetail>> = flow {
        try {
            emit(Resource.Loading<UserDetail>())
            val user = repository.getUserData(userId).toUserDetail()
            emit(Resource.Success<UserDetail>(user))
        } catch(e: HttpException) {
            emit(Resource.Error<UserDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<UserDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}