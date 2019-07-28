package com.mlcorrea.stackeruser.domain.executor

import java.util.concurrent.Executor

/**
 * Created by manuel on 27/07/19
 *
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * out of the UI thread.
 */
interface ThreadExecutor : Executor