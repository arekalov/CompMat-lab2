package com.arekalov.compmatlab2.data.singlesolvingmethods

import com.arekalov.compmatlab2.data.models.SingleSolvingParams

fun interface SingleSolvingMethods {
    fun solve(params: SingleSolvingParams)
}