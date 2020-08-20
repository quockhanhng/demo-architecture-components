package com.khanhnq.demo_architecture_components.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launch")
data class Launch(
    @PrimaryKey
    val id: String = "",
    @ColumnInfo(name = "date_unix")
    val dateUnix: Long = 0L,
    val name: String? = "",
    val details: String? = ""
)
