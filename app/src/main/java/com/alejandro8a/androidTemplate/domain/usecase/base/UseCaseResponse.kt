package com.alejandro8a.androidTemplate.domain.usecase.base

import com.alejandro8a.androidTemplate.network.ErrorModel

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(errorModel: ErrorModel)
}