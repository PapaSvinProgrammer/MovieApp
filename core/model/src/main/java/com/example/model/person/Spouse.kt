package com.example.model.person

data class Spouse(
    val id: Int,
    val name: String?,
    val children: Int?,
    val divorced: Boolean?,
    val relation: String?
)