package com.mlcorrea.stackeruser.ui

import com.mlcorrea.stackeruser.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by manuel on 27/07/19
 */
class UIThread : PostExecutionThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}