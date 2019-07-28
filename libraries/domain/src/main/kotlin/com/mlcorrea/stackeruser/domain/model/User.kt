package com.mlcorrea.stackeruser.domain.model

import com.mlcorrea.stackeruser.domain.model.adapter.ViewModelData
import java.io.Serializable

/**
 * Created by manuel on 27/07/19
 */
data class User(
    val id: Long,
    val profileImage: String?,
    val name: String?,
    val reputation: Long?,
    val location: String?,
    val creationDate: String,
    val follow: Boolean = false,
    val block: Boolean = false
) : ViewModelData, Serializable