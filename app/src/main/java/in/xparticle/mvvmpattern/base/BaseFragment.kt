package `in`.xparticle.mvvmpattern.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<T:ViewDataBinding,V: ViewModel>:Fragment() {

    private var viewDataBinding:T? = null
    private var mViewModel:ViewModel? = null
    abstract val bindingVariableFragment : Int
    abstract val layoutIdFragment : Int
    abstract val viewModelFragment: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater,layoutIdFragment,container,false)
        this.mViewModel = if (mViewModel == null) viewModelFragment else mViewModel
        viewDataBinding!!.setVariable(bindingVariableFragment,mViewModel)
        viewDataBinding!!.executePendingBindings()
        return viewDataBinding!!.root
    }
}