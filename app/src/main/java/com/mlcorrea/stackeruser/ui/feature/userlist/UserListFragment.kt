package com.mlcorrea.stackeruser.ui.feature.userlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mlcorrea.stackeruser.R
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.domain.model.adapter.ViewModelData
import com.mlcorrea.stackeruser.framework.extension.observe
import com.mlcorrea.stackeruser.framework.network.NetworkController
import com.mlcorrea.stackeruser.ui.base.BaseFragmentList
import com.mlcorrea.stackeruser.ui.base.BaseSimpleListViewModel
import com.mlcorrea.stackeruser.ui.feature.userdetails.UserDetailsActivity
import com.mlcorrea.stackeruser.ui.feature.userlist.adapter.UserListDiffCallback
import com.mlcorrea.stackeruser.ui.feature.userlist.adapter.UserListViewRender
import com.mlcorrea.stackeruser.ui.renders.RendererRecyclerViewSortedAdapter
import com.mlcorrea.stackeruser.ui.renders.baserenders.EmptyListViewRender
import com.mlcorrea.stackeruser.ui.renders.baserenders.LoadMoreViewRender
import com.mlcorrea.stackeruser.ui.renders.diffutils.SortedListComparatorWrapper
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope
import timber.log.Timber

class UserListFragment : BaseFragmentList(R.layout.user_list_fragment) {

    private val viewModel: UserListVM by currentScope.inject()
    private val networkController: NetworkController by inject()

    override val getViewModel: BaseSimpleListViewModel
        get() = viewModel

    override fun onCreateInitViewModel(savedInstanceState: Bundle?) {
        viewModel.apply {
            observe(snackBarError, ::handleError)
        }
        networkController.getLiveConnectionStatus.observe(this, Observer { status -> handleConnectionStatus(status) })
        viewModel.setViewModel()
    }

    override fun initViews() {
        //Set recycler view
        renderRecyclerView =
            RendererRecyclerViewSortedAdapter(
                emptyViewRender = EmptyListViewRender(),
                loadMoreViewRender = LoadMoreViewRender(object :
                    LoadMoreViewRender.LoadMoreViewListener {
                    override fun onRetryClick() {
                        viewModel.retry()
                    }
                })
            )
        val sortedListComparatorWrapper: SortedListComparatorWrapper<ViewModelData>
        sortedListComparatorWrapper = UserListDiffCallback(renderRecyclerView)
        renderRecyclerView.setComparatorWrapper(sortedListComparatorWrapper)
        renderRecyclerView.registerRenderer(
            UserListViewRender { view, user ->
                Timber.d("onclick user")
                if (!user.block) {
                    openDetails(user)
                }
            }
        )
        context?.let { context ->
            val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            val horizontalDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

            uiRecyclerView.addItemDecoration(horizontalDecoration)
            uiRecyclerView.layoutManager = linearLayoutManager
        }

        uiRecyclerView.adapter = renderRecyclerView
        uiSwipeRefresh.setOnRefreshListener { viewModel.refresh() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == USER_DETAIL_RESULT && resultCode == Activity.RESULT_OK && data != null) {
            viewModel.updateUser(data.getSerializableExtra(UserDetailsActivity.INTENT_RESULT_USER) as? User)
        }
    }

    override fun onDestroy() {
        networkController.getLiveConnectionStatus.removeObservers(this)
        super.onDestroy()
    }

    private fun openDetails(user: User) {
        context?.let {
            startActivityForResult(UserDetailsActivity.newIntent(it, user), USER_DETAIL_RESULT)
        }
    }

    private fun handleConnectionStatus(status: Boolean?) {
        status?.let {
            viewModel.setNewConnectionStatus(it)
        }
    }

    private fun handleError(exception: Exception?) {
        exception?.let {
            showSnackBar(getErrorMessage(it))
        }
    }

    companion object {
        private const val USER_DETAIL_RESULT = 234
        @JvmStatic
        fun newInstance() = UserListFragment()
    }
}
