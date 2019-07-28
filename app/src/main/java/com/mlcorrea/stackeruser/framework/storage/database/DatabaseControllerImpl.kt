package com.mlcorrea.stackeruser.framework.storage.database

import com.mlcorrea.stackeruser.framework.storage.database.room.dao.UserActionsDao
import com.mlcorrea.stackeruser.framework.storage.database.room.dao.UserDao
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserActionsDb
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserDb
import io.reactivex.Single

/**
 * Created by manuel on 27/07/19
 */
class DatabaseControllerImpl constructor(private val userDao: UserDao, private val userActionsDao: UserActionsDao) :
    DatabaseController {

    override fun getUsersPagedAfter(userId: Long, requestedLoadSize: Int): Single<List<UserDb>> {
        return userDao.getUsersPagedAfter(userId, requestedLoadSize)
    }

    override fun insertUserActions(UserAction: UserActionsDb): Int {
        return userActionsDao.insert(UserAction).toInt()
    }

    override fun insertUsers(users: List<UserDb>) {
        userDao.insert(users)
    }

    override fun getUserActionById(userId: Long): Single<UserActionsDb> {
        return userActionsDao.getUserActionById(userId)
    }
}