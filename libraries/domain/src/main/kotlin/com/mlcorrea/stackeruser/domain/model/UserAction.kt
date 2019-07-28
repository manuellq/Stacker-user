package com.mlcorrea.stackeruser.domain.model

/**
 * Created by manuel on 28/07/19
 */
data class UserAction(
    val id: Long,
    val follow: Boolean,
    val block: Boolean
)