package com.shannan.nakollaol.domain.exception

abstract class FailureResolver<in Type> {

    abstract suspend fun resolve(i: Type): Failure

    class None
}
