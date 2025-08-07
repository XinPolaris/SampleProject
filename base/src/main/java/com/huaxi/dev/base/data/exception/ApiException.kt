package com.huaxi.dev.base.data.exception

class ApiException(val code: Int, message: String) : Exception(message)
