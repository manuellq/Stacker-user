package com.mlcorrea.stackeruser.ui.feature.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mlcorrea.stackeruser.UnitTest
import com.mlcorrea.stackeruser.domain.exception.HttpNoInternetConnectionException
import com.mlcorrea.stackeruser.domain.iteractor.GetUsers
import com.mlcorrea.stackeruser.domain.iteractor.UpdateUserAction
import com.mlcorrea.stackeruser.domain.model.adapter.ViewModelData
import com.mlcorrea.stackeruser.domain.network.NetworkRequestState
import com.mlcorrea.stackeruser.domain.network.StatusRequest
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Created by manuel on 29/07/19
 */
class UserListVMTest : UnitTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK(relaxUnitFun = true)
    lateinit var getUsers: GetUsers
    @MockK
    lateinit var updateUserAction: UpdateUserAction

    @MockK(relaxUnitFun = true)
    lateinit var snackbarError: Observer<Exception>
    @MockK(relaxUnitFun = true)
    lateinit var networkState: Observer<NetworkRequestState>
    @MockK(relaxUnitFun = true)
    lateinit var progressbar: Observer<Boolean>
    @MockK
    lateinit var response: Observer<List<ViewModelData>>

    private lateinit var viewModel: UserListVM

    @Before
    fun setUp() {
        viewModel = UserListVM(getUsers, updateUserAction)
    }

    @Test
    fun `no internet connection detected`() {
        //Given
        viewModel.snackBarError.observeForever(snackbarError)
        viewModel.getNetworkState().observeForever(networkState)
        viewModel.getProgressBarRefreshLive().observeForever(progressbar)
        //When
        viewModel.setNewConnectionStatus(false)
        //Then
        verify { progressbar.onChanged(false) }
        verify { networkState.onChanged(eq(NetworkRequestState(StatusRequest.EMPTY, null))) }
        verify { snackbarError.onChanged(any<HttpNoInternetConnectionException>()) }
    }
}