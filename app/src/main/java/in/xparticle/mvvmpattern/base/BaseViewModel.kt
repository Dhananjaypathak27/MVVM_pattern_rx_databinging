package `in`.xparticle.mvvmpattern.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(application: Application): AndroidViewModel(application) {
    private var mNavigator:WeakReference<N>? = null
    var isLoading = ObservableBoolean(false)

    var navigator:N?
    get() = mNavigator!!.get()
    set(navigator){
        this.mNavigator = WeakReference<N>(navigator)
    }
}