package com.mlcorrea.stackeruser.domain.repository

import com.mlcorrea.stackeruser.domain.model.PageList
import com.mlcorrea.stackeruser.domain.model.User
import io.reactivex.Observable

/**
 * Created by manuel on 27/07/19
 */
interface PlatformRepository {

    fun getUsers(page: Int, pageSize: Int, order: String, sort: String, site: String): Observable<PageList<User>>

    fun updateUserAction(userId: Long, follow: Boolean, block: Boolean): Observable<Boolean>
}