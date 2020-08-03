package com.alejandro8a.androidTemplate.network

import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * This class trace exceptions(api call or parse data or connection errors) &
 * depending on what exception returns a [ErrorModel]
 *
 * */
class ApiErrorHandle {
    fun traceErrorException(throwable: Throwable?): ErrorModel {
        val errorModel: ErrorModel? = when (throwable) {

            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)
                if (throwable.code() == 401) {
                    ErrorModel(throwable.message(), throwable.code(), ErrorModel.ErrorStatus.UNAUTHORIZED)
                } else {
                    getHttpError(throwable.response()?.errorBody(), throwable.code())
                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                ErrorModel(throwable.message, ErrorModel.ErrorStatus.TIMEOUT)
            }

            // handle connection error
            is IOException -> {
                ErrorModel(throwable.message, ErrorModel.ErrorStatus.NO_CONNECTION)
            }
            else -> null
        }
        return errorModel ?: ErrorModel("No Defined Error!", 0, ErrorModel.ErrorStatus.BAD_RESPONSE)
    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    private fun getHttpError(body: ResponseBody?, code: Int): ErrorModel {
        return try {
            // use response body to get error detail
            ErrorModel(body.toString(), code, ErrorModel.ErrorStatus.BAD_RESPONSE)
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(message = e.message, errorStatus = ErrorModel.ErrorStatus.NOT_DEFINED)
        }

    }
}

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}