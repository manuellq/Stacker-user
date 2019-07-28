package com.mlcorrea.stackeruser.domain.model

/**
 * Created by manuel on 27/07/19
 */
data class PageList<T>(
    val data: List<T>?,
    val hasMore: Boolean?,
    val quotaMax: Int?,
    val quotaRemaining: Int?
)