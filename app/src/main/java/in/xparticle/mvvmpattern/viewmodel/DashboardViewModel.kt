package `in`.xparticle.mvvmpattern.viewmodel

import `in`.xparticle.mvvmpattern.base.BaseViewModel
import `in`.xparticle.mvvmpattern.navigator.DashboardNavigator
import android.app.Application

class DashboardViewModel(application: Application):
BaseViewModel<DashboardNavigator>(application){
}