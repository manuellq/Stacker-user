package com.mlcorrea.stackeruser.ui.renders.binder

import android.view.View
import androidx.annotation.IdRes
import com.mlcorrea.stackeruser.ui.renders.binder.ViewProvider

/**
 * Created by manuel.correa on 29/05/2018
 */
interface ViewFinder {

    fun <V : View> find(@IdRes id: Int, viewProvider: ViewProvider<V>): ViewFinder
    fun <V : View> find(@IdRes id: Int): V
}