package com.mlcorrea.stackeruser.data.dto

import com.mlcorrea.stackeruser.data.dto.base.BaseDto
import com.mlcorrea.stackeruser.domain.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by manuel on 27/07/19
 */

@JsonClass(generateAdapter = true)
data class UserDTO(
    @Json(name = "account_id")
    val id: Long,
    @Json(name = "profile_image")
    val profileImage: String?,
    @Json(name = "display_name")
    val name: String?,
    @Json(name = "reputation")
    val reputation: Long?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "creation_date")
    val creationDate: String
) : BaseDto<User> {

    override fun unwrapDto(): User {
        return User(id, profileImage, name, reputation, location, creationDate)
    }

}