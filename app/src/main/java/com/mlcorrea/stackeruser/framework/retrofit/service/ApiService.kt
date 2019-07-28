package com.mlcorrea.stackeruser.framework.retrofit.service

import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.data.dto.base.NetworkPageResponseDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by manuel on 27/07/19
 */
interface ApiService {

    @GET("/2.2/users")
    fun getAlbums(
        @Query("pagesize") pageSize: String, @Query("order") order: String,
        @Query("sort") sort: String, @Query("site") site: String
    ): Observable<NetworkPageResponseDTO<UserDTO>>

}