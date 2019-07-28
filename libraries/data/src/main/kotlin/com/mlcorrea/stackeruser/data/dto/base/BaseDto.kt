package com.mlcorrea.stackeruser.data.dto.base

/**
 * Created by manuel on 27/07/19
 */
interface BaseDto<T> {

    fun unwrapDto(): T
}