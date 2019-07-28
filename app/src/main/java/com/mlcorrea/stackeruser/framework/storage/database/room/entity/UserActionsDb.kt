package com.mlcorrea.stackeruser.framework.storage.database.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by manuel on 27/07/19
 */
@Entity(tableName = "user_actions")
data class UserActionsDb(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "follow")
    val follow: Boolean,
    @ColumnInfo(name = "block")
    val block: Boolean
)