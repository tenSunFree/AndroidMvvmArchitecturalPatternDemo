package com.home.androidmvvmarchitecturalpatterndemo.utils

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)