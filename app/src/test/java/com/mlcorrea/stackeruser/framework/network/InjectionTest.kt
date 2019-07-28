package com.mlcorrea.stackeruser.framework.network

import android.content.Context
import com.google.common.truth.Truth
import com.mlcorrea.stackeruser.UnitTest
import com.mlcorrea.stackeruser.data.repository.PlatformRepositoryImpl
import com.mlcorrea.stackeruser.data.storage.PersistentData
import com.mlcorrea.stackeruser.framework.retrofit.apimanager.ApiManager
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

/**
 * Created by manuel on 28/07/19
 */
class InjectionTest : UnitTest() {


    @Before
    fun setUp() {
    }

    @Test
    fun providePlatformRepositoryImpl() {
        val apiManager = mockk<ApiManager>()
        val context = mockk<Context>()
        val persistentData = mockk<PersistentData>()
        val result = Injection.providePlatformRepositoryImpl(context, apiManager, persistentData)

        Truth.assertThat(result).isInstanceOf(PlatformRepositoryImpl::class.java)
    }
}