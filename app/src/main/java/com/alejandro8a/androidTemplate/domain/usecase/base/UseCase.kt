package com.alejandro8a.androidTemplate.domain.usecase.base

import com.alejandro8a.androidTemplate.network.ApiErrorHandle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException

abstract class UseCase<Type, in Params>(private val apiErrorHandle: ApiErrorHandle) where Type : Any {

    abstract suspend fun run(params: Params? = null): Type

    fun invoke(
        scope: CoroutineScope,
        params: Params?,
        onResult: (UseCaseResponse<Type>)
    ) {
        val backgroundJob = scope.async {
            run(params)
        }
        scope.launch {
            backgroundJob.await().let {
                try {
                    onResult.onSuccess(it)
                } catch (e: HttpException) {
                    onResult.onError(apiErrorHandle.traceErrorException(e))
                }
            }
        }
    }
}