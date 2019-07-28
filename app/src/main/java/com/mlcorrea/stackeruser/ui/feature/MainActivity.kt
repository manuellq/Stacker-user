package com.mlcorrea.stackeruser.ui.feature

import android.os.Bundle
import com.mlcorrea.stackeruser.R
import com.mlcorrea.stackeruser.ui.base.BaseActivity
import com.mlcorrea.stackeruser.ui.feature.userlist.UserListFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val visibleFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (visibleFragment !is UserListFragment) {
            addFragment(R.id.nav_host_fragment, UserListFragment.newInstance())
        }
    }
}
