package com.khanhnq.data.base

import com.khanhnq.domain.model.Model

interface EntityMapper<M : Model, ME : ModelEntity> {
    fun mapToDomain(entity: ME): M

    fun mapToEntity(model: M): ME
}
