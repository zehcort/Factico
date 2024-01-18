package com.zehcort.domain.models

data class Fact(
    val id: String,
    val text: String,
    val source: String,
    val sourceURL: String,
    val language: String,
    val permalink: String
)