package com.mertoenjosh.questprovider.util

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mertoenjosh.questprovider.data.models.response.ErrorResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import okhttp3.ResponseBody
import timber.log.Timber
import java.net.SocketTimeoutException

object Utils {
    val serverErrorOccurred = MutableLiveData<Boolean>()

    val handler = CoroutineExceptionHandler { _, exception ->
        Timber.e("Caught..................... ${exception.printStackTrace()}")
        when (exception) {
            is SocketTimeoutException -> {
                serverErrorOccurred.postValue(true)
            }
        }
    }

    fun getErrorBody(errorBody: ResponseBody?): ErrorResponse {

        val type = object : TypeToken<ErrorResponse>() {}.type

        return Gson().fromJson(errorBody?.charStream(), type)
    }

}