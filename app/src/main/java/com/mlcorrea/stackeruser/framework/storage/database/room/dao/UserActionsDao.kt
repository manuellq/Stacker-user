package com.mlcorrea.stackeruser.framework.storage.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserActionsDb
import io.reactivex.Single

/**
 * Created by manuel on 27/07/19
 */
@Dao
interface UserActionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(action: UserActionsDb) : Long

    @Query("SELECT * FROM user_actions WHERE id = :userId")
    fun getUserActionById(userId: Long): Single<UserActionsDb>
}