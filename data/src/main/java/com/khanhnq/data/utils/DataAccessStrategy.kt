package com.khanhnq.data.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.khanhnq.domain.usecase.base.Result
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
    if (response.status == Result.Status.SUCCESS) {
        response.data?.let { saveCallResult(it) }
    } else if (response.status == Result.Status.ERROR) {
        emit(Result.error(response.message))
        emitSource(source)
    }
}
