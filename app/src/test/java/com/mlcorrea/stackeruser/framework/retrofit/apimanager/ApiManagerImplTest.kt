package com.mlcorrea.stackeruser.framework.retrofit.apimanager

import com.google.common.truth.Truth
import com.mlcorrea.stackeruser.UnitTest
import com.mlcorrea.stackeruser.framework.retrofit.helper.ApiServiceHelperController
import com.mlcorrea.stackeruser.framework.retrofit.service.ApiService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

/**
 * Created by manuel on 28/07/19
 */
class ApiManagerImplTest : UnitTest() {

    @MockK
    lateinit var apiServiceHelperController: ApiServiceHelperController


    @Before
    fun setUp() {

    }

    @Test
    fun `Check if the app init the API services`() {
        //Given
        val retrofit = mockk<Retrofit>()
        val apiService = mockk<ApiService>()
        every { apiServiceHelperController.createRetrofit() }.returns(retrofit)
        every { apiServiceHelperController.createService(retrofit) }.returns(apiService)
        val apiManager = ApiManagerImpl(apiServiceHelperController)
        //When
        val resultApiService = apiManager.apiServices
        //Then
        Truth.assertThat(resultApiService).isEqualTo(apiService)
    }
}