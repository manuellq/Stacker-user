package com.mlcorrea.stackeruser.framework.network

import android.content.Context
import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.data.dto.base.NetworkPageResponseDTO
import com.mlcorrea.stackeruser.data.network.ApiController
import io.reactivex.Observable

/**
 * Created by manuel on 28/07/19
 */
class MockApiControllerImpl(private val context: Context) : ApiController {

    override fun getUsers(
        page: Int,
        pageSize: Int,
        order: String,
        sort: String,
        site: String
    ): Observable<NetworkPageResponseDTO<UserDTO>> {
        return MockData.getUsers(context)
    }
}