package com.mlcorrea.stackeruser.framework.storage.database

import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserActionsDb
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserDb
import io.reactivex.Single

/**
 * Created by manuel on 27/07/19
 */
interface DatabaseController {

    fun insertUsers(users: List<UserDb>)

    fun insertUserActions(UserAction: UserActionsDb): Int

    fun getUserActionById(userId: Long): Single<UserActionsDb>

    fun getUsersPagedAfter(userId: Long, requestedLoadSize: Int): Single<List<UserDb>>
}