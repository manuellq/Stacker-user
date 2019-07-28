package com.mlcorrea.stackeruser.framework.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.mlcorrea.stackeruser.framework.network.NetworkController
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by manuel on 28/07/19
 */
class NetworkStatusReceiver : BroadcastReceiver(), KoinComponent {

    private val networkController: NetworkController by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            networkController.networkStatusChanged()
        }
    }

}