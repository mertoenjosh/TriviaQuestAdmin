package com.mertoenjosh.questprovider.data.util

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mertoenjosh.questprovider.data.network.models.response.ErrorResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import okhttp3.ResponseBody
import timber.log.Timber
import java.net.SocketTimeoutException

object Helpers {
    private val serverErrorOccurred = MutableLiveData<Boolean>()

    val handler = CoroutineExceptionHandler { _, exception ->
        Timber.e("Exception Caught..................... ${exception.printStackTrace()}")

        when (exception) {
            is SocketTimeoutException -> {
                serverErrorOccurred.postValue(true)
            }
        }
    }

    fun getErrorResponse(errorBody: ResponseBody?): ErrorResponse {

        val type = object : TypeToken<ErrorResponse>() {}.type

        return Gson().fromJson(errorBody?.charStream(), type)
    }

}