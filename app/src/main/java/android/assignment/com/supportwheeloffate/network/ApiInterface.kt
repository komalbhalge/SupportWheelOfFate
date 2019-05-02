package android.assignment.com.supportwheeloffate.network

import android.assignment.com.supportwheeloffate.Data.EmployeeResponse
import retrofit2.Call
import retrofit2.http.GET

//import retro
interface ApiInterface {
    @GET("getListEmployee")
    fun getEmployees(): Call<EmployeeResponse>

}