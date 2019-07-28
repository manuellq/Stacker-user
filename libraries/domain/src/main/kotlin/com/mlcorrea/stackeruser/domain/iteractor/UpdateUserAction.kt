package com.mlcorrea.stackeruser.domain.iteractor

import com.mlcorrea.stackeruser.domain.executor.PostExecutionThread
import com.mlcorrea.stackeruser.domain.executor.ThreadExecutor
import com.mlcorrea.stackeruser.domain.repository.PlatformRepository
import io.reactivex.Observable

/**
 * Created by manuel on 27/07/19
 */
class UpdateUserAction constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val platformRepository: PlatformRepository
) :
    UseCase<Boolean, UpdateUserAction.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<Boolean> {
        return platformRepository.updateUserAction(params.userId, params.follow, params.block)
    }

    data class Params(val userId: Long, val follow: Boolean, val block: Boolean)
}