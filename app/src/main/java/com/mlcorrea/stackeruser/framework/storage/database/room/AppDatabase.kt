package com.mlcorrea.stackeruser.framework.storage.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mlcorrea.stackeruser.framework.storage.database.room.dao.UserActionsDao
import com.mlcorrea.stackeruser.framework.storage.database.room.dao.UserDao
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserActionsDb
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserDb

/**
 * Created by manuel on 27/07/19
 */

@Database(
    entities = [(UserDb::class), (UserActionsDb::class)],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun userActionsDao(): UserActionsDao

}