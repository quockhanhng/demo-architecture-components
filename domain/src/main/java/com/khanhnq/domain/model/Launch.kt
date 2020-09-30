package com.khanhnq.domain.model

data class Launch(
    val id: String = "",
    val dateUnix: Long = 0L,
    val name: String? = "",
    val details: String? = ""
) : Model()
