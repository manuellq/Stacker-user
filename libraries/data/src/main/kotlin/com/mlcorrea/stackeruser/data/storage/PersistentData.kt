package com.mlcorrea.stackeruser.data.storage

import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.domain.model.UserAction
import io.reactivex.Single

/**
 * Created by manuel on 27/07/19
 */
interface PersistentData {

    fun insertUsers(users: List<UserDTO>)

    fun insertUserActions(userId: Long, follow: Boolean, block: Boolean): Int

    fun getUserActionById(userId: Long): Single<UserAction>

    fun getUsersPagedAfter(userId: Long, requestedLoadSize: Int): Single<List<User>>
}