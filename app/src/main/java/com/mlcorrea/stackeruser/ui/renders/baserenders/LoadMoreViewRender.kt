package com.mlcorrea.stackeruser.ui.renders.baserenders

import android.view.View
import android.view.ViewGroup
import com.mlcorrea.stackeruser.R
import com.mlcorrea.stackeruser.domain.network.NetworkRequestState
import com.mlcorrea.stackeruser.domain.network.StatusRequest
import com.mlcorrea.stackeruser.ui.renders.base.DefaultLoadMoreViewHolder
import com.mlcorrea.stackeruser.ui.renders.base.DefaultLoadMoreViewRender
import timber.log.Timber

/**
 * Created by manuel.correa on 20/03/2018.
 */
class LoadMoreViewRender(private val loadMore: LoadMoreViewListener?) :
    DefaultLoadMoreViewRender<DefaultLoadMoreViewHolder>() {

    interface LoadMoreViewListener {
        fun onRetryClick()
    }

    override fun bindView(model: NetworkRequestState, holder: DefaultLoadMoreViewHolder) {
        holder.uiRetryBtn.setOnClickListener {
            Timber.d("retry")
            loadMore?.onRetryClick()
        }
        //error message
        holder.uiErrorMessage.visibility =
            if (model.exception != null) View.VISIBLE else View.GONE
        //loading and retry
        holder.uiRetryBtn.visibility =
            if (model.status == StatusRequest.FAILED) View.VISIBLE else View.GONE
        holder.uiProgressBar.visibility =
            if (model.status == StatusRequest.RUNNING || model.status == StatusRequest.INIT) View.VISIBLE else View.GONE
    }

    override fun createViewHolder(parent: ViewGroup): DefaultLoadMoreViewHolder {
        return DefaultLoadMoreViewHolder(
            inflate(R.layout.layout_item_network_state, parent)
        )
    }
}