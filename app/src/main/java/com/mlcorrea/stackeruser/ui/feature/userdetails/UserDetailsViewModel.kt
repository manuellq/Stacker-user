package com.mlcorrea.stackeruser.ui.feature.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.mlcorrea.stackeruser.domain.iteractor.UpdateUserAction
import com.mlcorrea.stackeruser.domain.model.User
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

class UserDetailsViewModel constructor(private val updateUserAction: UpdateUserAction) : ViewModel() {

    private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> = _userData

    override fun onCleared() {
        updateUserAction.dispose()
        super.onCleared()
    }

    fun setUserData(user: User) {
        _userData.value = user
    }

    fun onClickedFollow() {
        val user = _userData.value
        user?.let {
            _userData.value = user.copy(follow = !it.follow)
        }
        updateAction(_userData.value)
    }

    fun onClickedBlock() {
        val user = _userData.value
        user?.let {
            _userData.value = user.copy(block = !it.block)
        }
        updateAction(_userData.value)
    }

    private fun updateAction(user: User?) {
        user?.apply {
            updateUserAction.execute(UpdateActionObserver(), UpdateUserAction.Params(id, follow, block))
        }
    }

    inner class UpdateActionObserver : DisposableObserver<Boolean>() {
        override fun onComplete() {
            //
        }

        override fun onNext(t: Boolean) {
            Timber.d("Updated action $t")
        }

        override fun onError(e: Throwable) {
            Timber.e(e)
        }

    }

}
