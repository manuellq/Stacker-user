package com.mlcorrea.stackeruser.domain.iteractor

import com.mlcorrea.stackeruser.domain.executor.PostExecutionThread
import com.mlcorrea.stackeruser.domain.executor.ThreadExecutor
import com.mlcorrea.stackeruser.domain.model.PageList
import com.mlcorrea.stackeruser.domain.model.User
import com.mlcorrea.stackeruser.domain.repository.PlatformRepository
import io.reactivex.Observable

/**
 * Created by manuel on 27/07/19
 */
class GetUsers constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val platformRepository: PlatformRepository
) :
    UseCase<PageList<User>, GetUsers.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<PageList<User>> {
        return platformRepository.getUsers(params.pageSize, params.order, params.sort, params.site)
    }

    data class Params(val pageSize: String, val order: String, val sort: String, val site: String)
}