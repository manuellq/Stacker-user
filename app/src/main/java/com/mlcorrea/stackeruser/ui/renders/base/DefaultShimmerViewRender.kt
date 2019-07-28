package com.mlcorrea.stackeruser.ui.renders.base

import com.mlcorrea.stackeruser.ui.renders.ViewRenderer
import com.mlcorrea.stackeruser.ui.renders.model.ShimmerModal

/**
 * Created by manuel.correa on 21/03/2018.
 */
abstract class DefaultShimmerViewRender() :
    ViewRenderer<ShimmerModal, DefaultShimmerViewHolder>(ShimmerModal::class.java)