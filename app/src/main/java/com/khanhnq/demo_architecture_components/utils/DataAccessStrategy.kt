package com.khanhnq.demo_architecture_components.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.khanhnq.demo_architecture_components.utils.Result.Status.ERROR
import com.khanhnq.demo_architecture_components.utils.Result.Status.SUCCESS
import kotlinx.coroutines.Dispatchers

fun <T, A> loadData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> = liveData(Dispatchers.IO) {

    emit(Result.loading())

    val source = databaseQuery.invoke().map { Result.success(it) }
    emitSource(source)

    val response = networkCall.invoke()
    if (response.status == SUCCESS) {
        saveCallResult(response.data!!)
    } else if (response.status == ERROR) {
        emit(Result.error(response.message!!))
        emitSource(source)
    }
}
