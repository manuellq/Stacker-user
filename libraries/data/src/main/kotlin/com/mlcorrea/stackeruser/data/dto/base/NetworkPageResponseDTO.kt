package com.mlcorrea.stackeruser.data.dto.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 27/07/19
 */

@JsonClass(generateAdapter = true)
data class NetworkPageResponseDTO<T>(
    @Json(name = "items")
    val data: List<T>?,
    @Json(name = "has_more")
    val hasMore: Boolean?,
    @Json(name = "quota_max")
    val quotaMax: Int?,
    @Json(name = "quota_remaining")
    val quotaRemaining: Int?
)