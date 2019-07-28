package com.mlcorrea.stackeruser.ui.feature.userlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mlcorrea.stackeruser.UnitTest
import com.mlcorrea.stackeruser.domain.iteractor.GetUsers
import com.mlcorrea.stackeruser.domain.iteractor.UpdateUserAction
import com.mlcorrea.stackeruser.domain.network.NetworkRequestState
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.observers.TestObserver
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

    @MockK
    lateinit var snackbarError: Observer<Exception>
    @MockK
    lateinit var networkState: Observer<NetworkRequestState>

    private lateinit var viewModel: UserListVM

    @Before
    fun setUp() {
        viewModel = UserListVM(getUsers, updateUserAction)
    }

    @Test
    fun `get data from network`() {

    }
}