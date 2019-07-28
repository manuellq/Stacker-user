package com.mlcorrea.stackeruser.framework.storage.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserDb
import io.reactivex.Single

/**
 * Created by manuel on 27/07/19
 */

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<UserDb>)

    @Query("SELECT * FROM user WHERE reputation > :key ORDER BY reputation DESC limit :requestedLoadSize")
    fun getUsersPagedAfter(key: Long, requestedLoadSize: Int): Single<List<UserDb>>
}