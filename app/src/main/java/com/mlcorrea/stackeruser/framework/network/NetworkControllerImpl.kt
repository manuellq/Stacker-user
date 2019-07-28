package com.mlcorrea.stackeruser.framework.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mlcorrea.stackeruser.framework.extension.networkInfo

/**
 * Created by manuel on 27/07/19
 *
 * Injectable class which handles device network connection.
 */
@Suppress("DEPRECATION")
class NetworkControllerImpl(private val context: Context) : NetworkController {

    private val _networkStatus = MutableLiveData<Boolean>().apply { value = true }
    private val networkStatus: LiveData<Boolean> = _networkStatus

    override val getLiveConnectionStatus: LiveData<Boolean>
        get() = networkStatus

    override val isConnected: Boolean
        get() {
            return try {
                context.networkInfo?.isConnectedOrConnecting ?: false
            } catch (exception: Exception) {
                false
            }
        }

    override fun networkStatusChanged() {
        val status = _networkStatus.value
        if (status != isConnected) {
            _networkStatus.value = isConnected
        }
    }
}