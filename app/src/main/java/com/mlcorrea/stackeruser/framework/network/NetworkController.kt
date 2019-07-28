package com.mlcorrea.stackeruser.framework.network

import androidx.lifecycle.LiveData

/**
 * Created by manuel on 27/07/19
 */
interface NetworkController {

    val getLiveConnectionStatus: LiveData<Boolean>

    val isConnected: Boolean

    fun networkStatusChanged()
}