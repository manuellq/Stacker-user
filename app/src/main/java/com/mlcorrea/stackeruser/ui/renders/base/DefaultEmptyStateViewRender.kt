package com.mlcorrea.stackeruser.ui.renders.base

import android.view.ViewGroup
import com.mlcorrea.stackeruser.R
import com.mlcorrea.stackeruser.ui.renders.ViewRenderer
import com.mlcorrea.stackeruser.ui.renders.model.EmptyListModel

/**
 * Created by manuel.correa on 27/03/2018.
 */
abstract class DefaultEmptyStateViewRender :
    ViewRenderer<EmptyListModel, DefaultEmptyStateViewHolder>(EmptyListModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): DefaultEmptyStateViewHolder {
        return DefaultEmptyStateViewHolder(
            inflate(R.layout.layout_item_empty_list_state, parent)
        )
    }
}