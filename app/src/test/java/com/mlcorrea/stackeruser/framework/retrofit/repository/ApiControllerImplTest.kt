package com.mlcorrea.stackeruser.framework.retrofit.repository

import com.mlcorrea.stackeruser.UnitTest
import com.mlcorrea.stackeruser.data.dto.UserDTO
import com.mlcorrea.stackeruser.data.dto.base.NetworkPageResponseDTO
import com.mlcorrea.stackeruser.data.network.ApiController
import com.mlcorrea.stackeruser.domain.exception.APIError
import com.mlcorrea.stackeruser.framework.retrofit.apimanager.ApiManager
import com.mlcorrea.stackeruser.framework.retrofit.service.ApiService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

/**
 * Created by manuel on 28/07/19
 */
class ApiControllerImplTest : UnitTest() {

    @MockK
    lateinit var apiManager: ApiManager
    @MockK
    lateinit var apiService: ApiService


    private lateinit var apiController: ApiController

    @Before
    fun setUp() {
        apiController = ApiControllerImpl(apiManager)
        every { apiManager.apiServices }.returns(apiService)
    }

    @Test
    fun `get users but there is not response from backend`() {

        //Given
        val testObserver = TestObserver<Any>()
        every { apiService.getUsers(any(), any(), any(), any(), any()) }.returns(Observable.error(APIError(1, "error")))
        //When
        val result = apiController.getUsers(1, 2, "", "", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertError { error: Throwable -> error is APIError }
        testObserver.assertNotComplete()
        testObserver.assertNoValues()
    }

    @Test
    fun `get users but there is  response from backend`() {
        //Given
        val testObserver = TestObserver<Any>()
        val input: NetworkPageResponseDTO<UserDTO> = NetworkPageResponseDTO(null, true, null, null)
        every { apiService.getUsers(any(), any(), any(), any(), any()) }.returns(Observable.just(input))
        //When
        val result = apiController.getUsers(1, 2, "", "", "")
        result.subscribe(testObserver)
        //Then
        testObserver.assertNoErrors()
        testObserver.assertComplete()
    }
}