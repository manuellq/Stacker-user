package com.mlcorrea.stackeruser.framework.storage.database.room.wrapper

import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.domain.model.UserAction
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserActionsDb
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserDb

/**
 * Created by manuel on 27/07/19
 */
class RoomModelWrapImpl : RoomModelWrap {

    override fun wrapUser(userDb: UserDb): User {
        userDb.apply {
            return User(id, profileImage, name, reputation, location, creationDate)
        }
    }

    override fun wrapUserActionDb(userActionsDb: UserActionsDb): UserAction {
        userActionsDb.apply {
            return UserAction(id, follow, block)
        }
    }

    override fun wrapUserAction(userId: Long, follow: Boolean, block: Boolean): UserActionsDb {
        return UserActionsDb(userId, follow, block)
    }

    override fun wrapUser(userDto: UserDTO): UserDb {
        userDto.apply {
            return UserDb(id, profileImage, name, reputation, location, creationDate)
        }
    }

}