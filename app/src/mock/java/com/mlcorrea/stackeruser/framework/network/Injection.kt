package com.mlcorrea.stackeruser.framework.network

import android.content.Context
import com.mlcorrea.stackeruser.data.repository.PlatformRepositoryImpl
import com.mlcorrea.stackeruser.data.storage.PersistentData
import com.mlcorrea.stackeruser.domain.repository.PlatformRepository
import com.mlcorrea.stackeruser.framework.retrofit.apimanager.ApiManager

/**
 * Created by manuel on 27/07/19
 */
object Injection {

    fun providePlatformRepositoryImpl(
        context: Context,
        apiManager: ApiManager,
        persistentData: PersistentData
    ): PlatformRepository {
        return PlatformRepositoryImpl(MockApiControllerImpl(context), persistentData)
    }
}