package com.mlcorrea.stackeruser.ui.feature.userlist.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mlcorrea.stackeruser.R
import com.mlcorrea.stackeruser.ui.renders.ViewHolderA

/**
 * Created by manuel on 28/07/19
 */
class UserListViewModel(itemView: View) : ViewHolderA(itemView) {
    val uiName: TextView = itemView.findViewById<View>(R.id.ui_name) as TextView
    val uiReputation: TextView = itemView.findViewById<View>(R.id.ui_reputation) as TextView
    val uiImage: ImageView = itemView.findViewById<View>(R.id.ui_profile_photo) as ImageView
    val uiFollowed: TextView = itemView.findViewById<View>(R.id.ui_followed) as TextView
    val uiDisableFrame: View = itemView.findViewById(R.id.ui_disable_frame) as View

}