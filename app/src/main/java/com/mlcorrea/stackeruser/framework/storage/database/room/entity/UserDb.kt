package com.mlcorrea.stackeruser.framework.storage.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mlcorrea.stackeruser.data.dto.base.BaseDto
import com.mlcorrea.stackeruser.domain.model.User

/**
 * Created by manuel on 27/07/19
 */

@Entity(tableName = "user")
data class UserDb(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "profile_image")
    val profileImage: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "reputation")
    val reputation: Long?,
    @ColumnInfo(name = "location")
    val location: String?,
    @ColumnInfo(name = "creation_date")
    val creationDate: Long
) : BaseDto<User> {

    override fun unwrapDto(): User {
        return User(id, profileImage, name, reputation, location, creationDate)
    }

}