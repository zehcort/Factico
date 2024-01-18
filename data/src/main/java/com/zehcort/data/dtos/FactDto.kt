package com.zehcort.data.dtos

import com.zehcort.data.entities.uf.Fact as FactEntity
import com.zehcort.domain.models.Fact as FactDomain

fun FactEntity.toDomain(): FactDomain = FactDomain(
    id = id,
    text = text,
    source = source,
    sourceURL = sourceURL,
    language = language,
    permalink = permalink
)