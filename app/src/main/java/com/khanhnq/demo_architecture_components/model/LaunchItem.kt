package com.khanhnq.demo_architecture_components.model

import com.khanhnq.demo_architecture_components.base.ItemMapper
import com.khanhnq.demo_architecture_components.base.ModelItem
import com.khanhnq.domain.model.Launch

data class LaunchItem(
    val id: String = "",
    val dateUnix: Long = 0L,
    val name: String? = "",
    val details: String? = ""
) : ModelItem()

class LaunchItemMapper : ItemMapper<Launch, LaunchItem> {
    override fun mapToPresentation(model: Launch) = LaunchItem(
        model.id,
        model.dateUnix,
        model.name,
        model.details
    )

    override fun mapToDomain(modelItem: LaunchItem) = Launch(
        modelItem.id,
        modelItem.dateUnix,
        modelItem.name,
        modelItem.details
    )
} 
