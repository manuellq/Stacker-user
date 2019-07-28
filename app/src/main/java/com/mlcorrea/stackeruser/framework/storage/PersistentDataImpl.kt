package com.mlcorrea.stackeruser.framework.storage

import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.data.storage.PersistentData
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.domain.model.UserAction
import com.mlcorrea.stackeruser.framework.storage.database.DatabaseController
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserActionsDb
import com.mlcorrea.stackeruser.framework.storage.database.room.entity.UserDb
import com.mlcorrea.stackeruser.framework.storage.database.room.wrapper.RoomModelWrap
import io.reactivex.Single

/**
 * Created by manuel on 27/07/19
 */
class PersistentDataImpl constructor(
    private var databaseController: DatabaseController,
    private var roomModelWrap: RoomModelWrap
) : PersistentData {

    override fun getUsersPagedAfter(userId: Long, requestedLoadSize: Int): Single<List<User>> {
        return databaseController.getUsersPagedAfter(userId, requestedLoadSize)
            .map { response: List<UserDb> -> response.map { userDb: UserDb -> roomModelWrap.wrapUser(userDb) } }
    }

    override fun getUserActionById(userId: Long): Single<UserAction> {
        return databaseController.getUserActionById(userId)
            .map { response: UserActionsDb -> roomModelWrap.wrapUserActionDb(response) }
    }

    override fun insertUserActions(userId: Long, follow: Boolean, block: Boolean): Int {
        return databaseController.insertUserActions(roomModelWrap.wrapUserAction(userId, follow, block))
    }

    override fun insertUsers(users: List<UserDTO>) {
        databaseController.insertUsers(users.map { userDTO: UserDTO -> roomModelWrap.wrapUser(userDTO) })
    }
}