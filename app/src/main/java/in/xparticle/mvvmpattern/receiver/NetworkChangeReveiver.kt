package `in`.xparticle.mvvmpattern.receiver

import `in`.xparticle.mvvmpattern.utility.AppConstants.INTERNET_CONNECTION_CHANGE_CHECK
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build

class NetworkChangeReveiver(callback: NetworkChangeCallback?) :BroadcastReceiver() {

    private val mListenerCallback: NetworkChangeCallback? = callback
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent!!.action == INTERNET_CONNECTION_CHANGE_CHECK){
            val status = isNetworkAvailable(context)
            mListenerCallback?.onNetworkChanged(status)
        }
    }

    private fun isNetworkAvailable(context: Context?):Boolean{

        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return  false
            return when{
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        else{
            connectivityManager.activeNetworkInfo?.run {
                return when(type){
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> true
                }
            }
        }
        return false
    }
}

interface NetworkChangeCallback{
 fun  onNetworkChanged(status:Boolean)
}