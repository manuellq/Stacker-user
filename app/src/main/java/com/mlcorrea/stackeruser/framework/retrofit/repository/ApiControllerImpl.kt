package com.mlcorrea.stackeruser.framework.retrofit.repository

import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.data.dto.base.NetworkPageResponseDTO
import com.mlcorrea.stackeruser.data.network.ApiController
import com.mlcorrea.stackeruser.framework.retrofit.apimanager.ApiManager
import io.reactivex.Observable

/**
 * Created by manuel on 27/07/19
 */
class ApiControllerImpl constructor(private val apiManager: ApiManager) : ApiController {

    override fun getUsers(
        pageSize: String,
        order: String,
        sort: String,
        site: String
    ): Observable<NetworkPageResponseDTO<UserDTO>> {
        return apiManager.apiServices.getAlbums(pageSize,order,sort,site)
    }
}