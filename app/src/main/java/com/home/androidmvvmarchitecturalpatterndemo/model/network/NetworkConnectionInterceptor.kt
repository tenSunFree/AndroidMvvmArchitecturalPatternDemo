package com.home.androidmvvmarchitecturalpatterndemo.model.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.home.androidmvvmarchitecturalpatterndemo.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 網絡連接攔截器
 */
class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NoInternetException("沒有網路")
        return chain.proceed(chain.request())
    }

    /**
     * 判斷是否有網路
     */
    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        // T.let返回的是作用域中的最後一個物件, 它的值和型別都可以改變
        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true // WIFI
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true // 移動網絡
                    else -> false
                }
            }
        }
        return result
    }
}