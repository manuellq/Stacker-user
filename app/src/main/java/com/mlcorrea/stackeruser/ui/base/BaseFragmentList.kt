package com.mlcorrea.stackeruser.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import com.mlcorrea.stackeruser.R
import com.mlcorrea.stackeruser.domain.model.adapter.ViewModelData
import com.mlcorrea.stackeruser.domain.network.NetworkRequestState
import com.mlcorrea.stackeruser.ui.renders.RendererRecyclerViewSortedAdapter
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * Created by manuel on 28/07/19
 */
abstract class BaseFragmentList(private val layout: Int) : BaseFragment() {


    @BindView(R.id.ui_recycler_view_list)
    lateinit var uiRecyclerView: RecyclerView
    @BindView(R.id.ui_swipe_refresh)
    lateinit var uiSwipeRefresh: SwipeRefreshLayout

    abstract val getViewModel: BaseSimpleListViewModel
    abstract fun onCreateInitViewModel(savedInstanceState: Bundle?)
    abstract fun initViews()

    lateinit var renderRecyclerView: RendererRecyclerViewSortedAdapter

    private val disposables = CompositeDisposable()


    override val fragmentLayout: Int
        get() = layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        onCreateInitViewModel(savedInstanceState)
        getViewModel.handleResponseList().observe(this, Observer { handleResponseNet(it) })
        getViewModel.getNetworkState().observe(this, Observer { handleNetworkState(it) })
        getViewModel.getProgressBarRefreshLive()
            .observe(this, Observer { showProgressBarRecyclerView(it) })
    }


    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun handleResponseNet(list: List<ViewModelData>?) {
        setAdapter(list)
    }

    private fun handleNetworkState(networkRequestState: NetworkRequestState?) {
        renderRecyclerView.setNetworkState(networkRequestState)
        if (networkRequestState?.exception != null) {
            showSnackBar(getErrorMessage(networkRequestState.exception as Exception))
        }
    }

    private fun showProgressBarRecyclerView(visible: Boolean?) {
        if (visible == null) return
        uiSwipeRefresh.post {
            Timber.d("setProgressBarLoadingState, shouldBeLoading : $visible")
            uiSwipeRefresh.isRefreshing = visible
        }
    }

    private fun setAdapter(list: List<ViewModelData>?) {
        if (list == null) return
        // getViewModel.stopProgressBareRefresh()
        renderRecyclerView.removeAll(list)
    }
}