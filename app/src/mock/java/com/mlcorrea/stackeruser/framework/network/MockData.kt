package com.mlcorrea.stackeruser.framework.network

import android.content.Context
import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.data.dto.base.NetworkPageResponseDTO
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.reactivex.Observable

/**
 * Created by manuel on 28/07/19
 */
object MockData {

    fun getUsers(context: Context): Observable<NetworkPageResponseDTO<UserDTO>> {
        return Observable.just("users.json")
            .map { name: String ->
                val json = context.assets.open(name).bufferedReader().use { it.readText() }
                json
            }
            .map { response: String ->
                val moshi = Moshi.Builder().build()
                val type = Types.newParameterizedType(NetworkPageResponseDTO::class.java, UserDTO::class.java)
                val jsonAdapter: JsonAdapter<NetworkPageResponseDTO<UserDTO>> = moshi.adapter(type)
                jsonAdapter.fromJson(response)
            }
    }
}