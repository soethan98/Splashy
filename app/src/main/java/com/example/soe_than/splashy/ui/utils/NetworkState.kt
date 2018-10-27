package com.example.soe_than.splashy.ui.utils

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}



data class  NetworkState private constructor(
        val string: Status,
        val msg: String? = null){
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }

}


