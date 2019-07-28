package com.mlcorrea.stackeruser.data.repository

import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.data.dto.base.NetworkPageResponseDTO
import com.mlcorrea.stackeruser.data.network.ApiController
import com.mlcorrea.stackeruser.data.storage.PersistentData
import com.mlcorrea.stackeruser.domain.model.PageList
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.domain.model.UserAction
import com.mlcorrea.stackeruser.domain.repository.PlatformRepository
import io.reactivex.Observable

/**
 * Created by manuel on 27/07/19
 */
class PlatformRepositoryImpl constructor(
    private val apiController: ApiController,
    private val persistentData: PersistentData
) : PlatformRepository {


    override fun updateUserAction(userId: Long, follow: Boolean, block: Boolean): Observable<Boolean> {
        return Observable.defer {
            Observable.just(persistentData.insertUserActions(userId, follow, block))
                .map { response: Int -> response >= 1 }
        }
    }

    override fun getUsers(
        page: Int,
        pageSize: Int,
        order: String,
        sort: String,
        site: String
    ): Observable<PageList<User>> {
        return getUsersNetwork(page, pageSize, order, sort, site)
            .flatMap { page: PageList<User> ->
                getCheckForUserActions(page.data ?: emptyList())
                    .map { finalUsers: List<User> ->
                        page.copy(data = finalUsers)
                    }
            }
    }

    private fun getCheckForUserActions(users: List<User>): Observable<List<User>> {
        return Observable.just(users)
            .concatMapIterable { userList: List<User> -> userList }
            .flatMap { user: User ->
                persistentData.getUserActionById(user.id)
                    .map { userAction: UserAction ->
                        user.copy(follow = userAction.follow, block = userAction.block)
                    }
                    .onErrorReturnItem(user)
                    .toObservable()
            }
            .toList()
            .toObservable()
    }

    private fun getUsersNetwork(
        page: Int,
        pageSize: Int,
        order: String,
        sort: String,
        site: String
    ): Observable<PageList<User>> {
        return apiController.getUsers(page, pageSize, order, sort, site)
            .map { response: NetworkPageResponseDTO<UserDTO> ->
                val userList = response.data?.map { userDTO: UserDTO -> userDTO.unwrapDto() } ?: emptyList()
                PageList(userList, response.hasMore, response.quotaMax, response.quotaRemaining)
            }
    }
}