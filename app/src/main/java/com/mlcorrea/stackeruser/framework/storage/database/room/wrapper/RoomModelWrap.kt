package com.mlcorrea.stackeruser.framework.storage.database.room.wrapper

import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.domain.model.UserAction
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserActionsDb
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserDb

/**
 * Created by manuel on 27/07/19
 */
interface RoomModelWrap {

    fun wrapUser(userDto: UserDTO): UserDb

    fun wrapUser(userDb: UserDb): User

    fun wrapUserAction(userId: Long, follow: Boolean, block: Boolean): UserActionsDb

    fun wrapUserActionDb(userActionsDb: UserActionsDb): UserAction
}