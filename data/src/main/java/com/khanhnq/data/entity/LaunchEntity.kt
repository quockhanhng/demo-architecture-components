package com.khanhnq.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.khanhnq.data.base.EntityMapper
import com.khanhnq.data.base.ModelEntity
import com.khanhnq.domain.model.Launch

@Entity(tableName = "launch")
data class LaunchEntity(
    @PrimaryKey
    val id: String = "",
    @ColumnInfo(name = "date_unix")
    val dateUnix: Long = 0L,
    val name: String? = "",
    val details: String? = ""
) : ModelEntity()

class LaunchEntityMapper : EntityMapper<Launch, LaunchEntity> {
    override fun mapToDomain(entity: LaunchEntity) = Launch(
        entity.id,
        entity.dateUnix,
        entity.name,
        entity.details
    )

    override fun mapToEntity(model: Launch) = LaunchEntity(
        model.id,
        model.dateUnix,
        model.name,
        model.details
    )
}
