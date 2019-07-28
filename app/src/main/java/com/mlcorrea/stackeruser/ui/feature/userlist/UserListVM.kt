package com.mlcorrea.stackeruser.ui.feature.userlist

import com.mlcorrea.stackeruser.domain.exception.HttpNoInternetConnectionException
import com.mlcorrea.stackeruser.domain.iteractor.GetUsers
import com.mlcorrea.stackeruser.domain.iteractor.UpdateUserAction
import com.mlcorrea.stackeruser.domain.model.PageList
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.domain.network.NetworkRequestState
import com.mlcorrea.stackeruser.framework.livedata.SingleLiveEvent
import com.mlcorrea.stackeruser.ui.base.BaseSimpleListViewModel
import io.reactivex.observers.DisposableObserver
import timber.log.Timber
import kotlin.properties.Delegates

class UserListVM constructor(
    private val getUsers: GetUsers, private val updateUserAction: UpdateUserAction
) :
    BaseSimpleListViewModel() {

    private var _snackBarError: SingleLiveEvent<Exception> = SingleLiveEvent()
    val snackBarError: SingleLiveEvent<Exception> = _snackBarError
    private var isViewModelLoaded = false
    private var connectionStatus: Boolean by Delegates.observable(true) { property, oldValue, newValue ->
        if (oldValue != newValue) {
            if (newValue) {
                refresh()
            } else {
                setProgressbarRefresh(false)
                setListNetwork(emptyList())
                setNetworkValue(NetworkRequestState.EMPTY)
                _snackBarError.value = HttpNoInternetConnectionException()
            }
        }
    }

    override fun onCleared() {
        getUsers.dispose()
        updateUserAction.dispose()
        super.onCleared()
    }

    override fun getNetworkData() {
        getUsers.execute(object : DisposableObserver<PageList<User>>() {
            override fun onComplete() {
                Timber.d("onComplete")
            }

            override fun onNext(page: PageList<User>) {
                onSuccessResponse(page.data ?: emptyList())
            }

            override fun onError(e: Throwable) {
                onErrorResponse(e)
                Timber.e(e)
            }
        }, GetUsers.Params(1, 20, "desc", "reputation", "stackoverflow"))
    }

    fun setViewModel() {
        if (isViewModelLoaded) return
        isViewModelLoaded = true
        setNetworkValue(NetworkRequestState.INIT)
        getNetworkData()
    }

    fun updateUser(user: User?) {
        user?.let { newUser ->
            getListNetwork()?.toMutableList()?.let {
                for ((count, item) in it.withIndex()) {
                    if (item is User && item.id == newUser.id) {
                        it[count] = user
                        setListNetwork(it)
                        break
                    }
                }
            }
        }
    }


    fun setNewConnectionStatus(connected: Boolean) {
        connectionStatus = connected
    }

}
