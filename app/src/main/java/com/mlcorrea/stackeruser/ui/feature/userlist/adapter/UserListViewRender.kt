package com.mlcorrea.stackeruser.ui.feature.userlist.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.mlcorrea.stackeruser.R
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.ui.renders.ViewRenderer
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/**
 * Created by manuel on 28/07/19
 */
class UserListViewRender(private val clickListener: (View, User) -> Unit = { _: View, _: User -> }) :
    ViewRenderer<User, UserListViewModel>(User::class.java) {

    @SuppressLint("CheckResult")
    override fun bindView(model: User, holder: UserListViewModel) {
        holder.apply {
            val context = itemView.context
            uiReputation.text = model.reputation.toString()
            uiName.text = model.name
            uiFollowed.visibility = if (model.follow) View.VISIBLE else View.GONE
            uiDisableFrame.visibility = if (model.block) View.VISIBLE else View.GONE

            val image = model.profileImage
            if (image != null && image.isNotEmpty()) {
                Picasso.with(context)
                    .load(image)
                    .placeholder(R.color.colorPrimary)
                    .error(R.color.colorAccent)
                    .into(uiImage)
            } else {
                Picasso.with(context)
                    .load(R.drawable.ic_person_24dp)
                    .placeholder(R.color.colorPrimary)
                    .error(R.color.colorAccent)
                    .into(uiImage)
            }
        }

        RxView.clicks(holder.itemView)
            .throttleFirst(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                clickListener(holder.itemView, model)
            }
    }

    override fun createViewHolder(parent: ViewGroup): UserListViewModel {
        return UserListViewModel(inflate(R.layout.layout_item_user, parent))
    }
}