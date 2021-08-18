package `in`.xparticle.mvvmpattern.viewmodel

import `in`.xparticle.mvvmpattern.base.BaseViewModel
import `in`.xparticle.mvvmpattern.model.MModel
import `in`.xparticle.mvvmpattern.navigator.DashboardNavigator
import `in`.xparticle.mvvmpattern.network.ApiClient
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DashboardViewModel(application: Application):
BaseViewModel<DashboardNavigator>(application){

    private var mId:String? = null
    private var mDisposable:Disposable? = null
    private var ctx:Application = application

    init {
        initData()
    }

    private fun initData()= viewModelScope.launch(Dispatchers.IO) {

    }

    fun callApiForAuth(){

        val body: HashMap<String,String> = HashMap()
        body["id"] = "101"

        ApiClient.apiInterface.getMM(body).subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MModel>{
                override fun onSubscribe(d: Disposable) {
                    mDisposable = d
                }

                override fun onNext(t: MModel) {
                   if(t.code == 200){
                       navigator?.navigatorOnSuccessResponse()
                   }
                    else{
                        navigator?.navigatorOnFailureResponse()
                   }
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(ctx, "Network error", Toast.LENGTH_SHORT).show()
                }

                override fun onComplete() {
                    mDisposable?.dispose()
                }

            })
    }
}