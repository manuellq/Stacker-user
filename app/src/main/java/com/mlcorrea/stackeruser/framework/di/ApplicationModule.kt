package com.mlcorrea.stackeruser.framework.di

import com.mlcorrea.stackeruser.BuildConfig
import com.mlcorrea.stackeruser.data.executor.JobExecutor
import com.mlcorrea.stackeruser.domain.executor.PostExecutionThread
import com.mlcorrea.stackeruser.domain.executor.ThreadExecutor
import com.mlcorrea.stackeruser.domain.iteractor.GetUsers
import com.mlcorrea.stackeruser.domain.iteractor.UpdateUserAction
import com.mlcorrea.stackeruser.framework.network.Injection
import com.mlcorrea.stackeruser.ui.UIThread
import com.mlcorrea.stackeruser.ui.feature.userdetails.UserDetailsActivity
import com.mlcorrea.stackeruser.ui.feature.userdetails.UserDetailsViewModel
import com.mlcorrea.stackeruser.ui.feature.userlist.UserListFragment
import com.mlcorrea.stackeruser.ui.feature.userlist.UserListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by manuel on 27/07/19
 */

const val IS_DEBUG_QUALIFIER = "isDebug"

val appModule = module {

    single {
        Injection.providePlatformRepositoryImpl(get(), get(), get())
    }

}

val dataModule = module {

    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UIThread() }
    factory(named(IS_DEBUG_QUALIFIER)) { BuildConfig.DEBUG }

}

val fragmentScope = module {
    scope(named<UserListFragment>()) {
        viewModel { UserListVM(get(), get()) }
        scoped { GetUsers(get(), get(), get()) }
        scoped { UpdateUserAction(get(), get(), get()) }
    }

}

val activityScope = module {

    scope(named<UserDetailsActivity>()) {
        viewModel { UserDetailsViewModel(get()) }
        scoped { UpdateUserAction(get(), get(), get()) }
    }
}