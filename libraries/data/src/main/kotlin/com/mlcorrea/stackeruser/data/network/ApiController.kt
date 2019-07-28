package com.mlcorrea.stackeruser.data.network

import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.data.dto.base.NetworkPageResponseDTO
import io.reactivex.Observable

/**
 * Created by manuel on 27/07/19
 */
interface ApiController {

    fun getUsers(
        pageSize: String,
        order: String,
        sort: String,
        site: String
    ): Observable<NetworkPageResponseDTO<UserDTO>>
}