package com.mlcorrea.stackeruser.ui.feature.userlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.domain.model.adapter.ViewModelData
import com.mlcorrea.stackeruser.ui.renders.diffutils.SortedListComparatorWrapper
import java.util.*

/**
 * Created by manuel on 28/07/19
 */
class UserListDiffCallback(adapter: RecyclerView.Adapter<*>) :
    SortedListComparatorWrapper<ViewModelData>(
        adapter,
        DEFAULT_ORDER
    ) {

    override fun areItemsTheSame(oldItem: ViewModelData?, newItem: ViewModelData?): Boolean {
        return if (oldItem is User && newItem is User) {
            oldItem.id == newItem.id
        } else {
            throw IllegalArgumentException("Model must be Quiz")
        }
    }

    override fun areContentsTheSame(oldItem: ViewModelData?, newItem: ViewModelData?): Boolean {
        return if (oldItem is User && newItem is User) {
            oldItem.follow == newItem.follow && oldItem.block == newItem.block
        } else {
            false
        }
    }

    companion object {
        private val DEFAULT_ORDER = OrderByReputation()
    }

}

class OrderByReputation : Comparator<ViewModelData> {
    override fun compare(oldItem: ViewModelData, newItem: ViewModelData): Int {
        return if (oldItem is User && newItem is User) {
            newItem.reputation?.compareTo(oldItem.reputation ?: 0) ?: 0
        } else {
            throw IllegalArgumentException("Model must be Quiz order title")
        }
    }
}