package `in`.xparticle.mvvmpattern.base

import `in`.xparticle.mvvmpattern.R
import `in`.xparticle.mvvmpattern.receiver.NetworkChangeCallback
import `in`.xparticle.mvvmpattern.receiver.NetworkChangeReveiver
import `in`.xparticle.mvvmpattern.utility.AppConstants.INTERNET_CONNECTION_CHANGE_CHECK
import android.content.BroadcastReceiver
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T: ViewDataBinding,V:ViewModel> : AppCompatActivity(),
    NetworkChangeCallback {

    var viewDataBinding: T? = null
    private var internetCheckReceiver:BroadcastReceiver?= null

    private var mViewModel: V? = null
    abstract val bindingVariable: Int
    abstract val layoutId: Int
    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        performDataBinding()
        registerBroadcastReceiver(this)
    }

    private fun registerBroadcastReceiver(baseActivity: BaseActivity<T,V>){
        internetCheckReceiver = NetworkChangeReveiver(baseActivity)
        registerReceiver(
            internetCheckReceiver,
            IntentFilter(INTERNET_CONNECTION_CHANGE_CHECK)
        )
    }

    private fun performDataBinding(){
        viewDataBinding = DataBindingUtil.setContentView(this,layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.setVariable(bindingVariable,mViewModel)
        viewDataBinding!!.executePendingBindings()
    }
}