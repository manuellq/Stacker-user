package com.mlcorrea.stackeruser.ui.renders.base

import com.mlcorrea.stackeruser.domain.network.NetworkRequestState
import com.mlcorrea.stackeruser.ui.renders.ViewHolderA
import com.mlcorrea.stackeruser.ui.renders.ViewRenderer

/**
 * Created by manuel.correa on 21/03/2018.
 */
abstract class DefaultLoadMoreViewRender<VH : ViewHolderA> :
    ViewRenderer<NetworkRequestState, VH>(NetworkRequestState::class.java)