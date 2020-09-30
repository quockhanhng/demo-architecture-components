package com.khanhnq.domain.usecase.base

interface UseCase<in Param, out T> {
    fun execute(param: Param): T
}

interface UseCaseNoParam<out T> {
    fun execute(): T
}
