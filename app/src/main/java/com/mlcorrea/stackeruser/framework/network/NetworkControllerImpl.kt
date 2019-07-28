package com.mlcorrea.stackeruser.framework.network

import android.content.Context
import com.mlcorrea.stackeruser.framework.extension.networkInfo

/**
 * Created by manuel on 27/07/19
 *
 * Injectable class which handles device network connection.
 */
@Suppress("DEPRECATION")
class NetworkControllerImpl(private val context: Context) : NetworkController {

    override val isConnected: Boolean
        get() {
            return try {
                context.networkInfo?.isConnectedOrConnecting ?: false
            } catch (exception: Exception) {
                false
            }
        }
}