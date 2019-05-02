package android.assignment.com.supportwheeloffate.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.assignment.com.supportwheeloffate.Data.EmployeeResponse
import android.assignment.com.supportwheeloffate.network.ServiceBuilder

class EmployeeViewModel: ViewModel() {
    private val mService = ServiceBuilder()

    fun getEmployeeList():MutableLiveData<EmployeeResponse>?{
        return mService.loadEmployeeData()
    }
}