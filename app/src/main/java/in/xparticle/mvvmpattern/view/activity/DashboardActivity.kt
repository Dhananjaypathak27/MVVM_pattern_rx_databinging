package `in`.xparticle.mvvmpattern.view.activity

import `in`.xparticle.mvvmpattern.BR
import `in`.xparticle.mvvmpattern.R
import `in`.xparticle.mvvmpattern.base.BaseActivity
import `in`.xparticle.mvvmpattern.databinding.ActivityDashboardBinding
import `in`.xparticle.mvvmpattern.navigator.DashboardNavigator
import `in`.xparticle.mvvmpattern.viewmodel.DashboardViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider

class DashboardActivity : BaseActivity<ActivityDashboardBinding,
        DashboardViewModel>(),DashboardNavigator {

    private var mActivityDashboardBinding: ActivityDashboardBinding? = null

    override val bindingVariable : Int
    get() = BR.dashboardViewModel
    override val layoutId: Int
        get() = R.layout.activity_dashboard
    override val viewModel: DashboardViewModel
        get() = ViewModelProvider(this).get(DashboardViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityDashboardBinding = viewDataBinding
        viewModel.navigator = this

        viewDataBinding?.nameTV?.text = "hello dhananjay"

        viewDataBinding?.nameTV?.setOnClickListener(View.OnClickListener {
            viewModel.callApiForAuth()
        })

    }

    override fun onNetworkChanged(status: Boolean) {

    }

    override fun navigatorOnSuccessResponse() {

    }

    override fun navigatorOnFailureResponse() {

    }
}