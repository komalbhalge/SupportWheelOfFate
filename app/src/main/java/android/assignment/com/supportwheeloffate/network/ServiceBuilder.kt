package android.assignment.com.supportwheeloffate.network

import android.arch.lifecycle.MutableLiveData
import android.assignment.com.supportwheeloffate.Data.EmployeeResponse
import android.util.Log
import android.widget.Toast
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {
    val liveUserResponse: MutableLiveData<EmployeeResponse> = MutableLiveData()
    private val BASE_URL = "https://c5db81cc-85ab-499c-ac24-2ebc86a15157.mock.pstmn.io/"

    private val okHttp = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build())
    private val retrofit = builder.build()

    fun <T> buildservice(serviceType: Class<T>): T {
        Log.e("ServiceBuilder", "buildservice")

        return retrofit.create(serviceType)
    }

    fun loadEmployeeData(): MutableLiveData<EmployeeResponse>? {
        Log.e("ServiceBuilder","loadAndroidData")
        val service = buildservice(ApiInterface::class.java)
        val requestCall = service.getEmployees()
        requestCall.enqueue(object : Callback<EmployeeResponse> {
            override fun onFailure(call: Call<EmployeeResponse>?, t: Throwable?) {
                Log.e("ServiceBuilder", "onFailure")
            }

            override fun onResponse(call: Call<EmployeeResponse>?, response: Response<EmployeeResponse>?) {
                Log.e("ServiceBuilder", "onResponse")

                val employeeList = response?.body()
                liveUserResponse?.value = employeeList
                Log.e("ServiceBuilder", "engineers size: " + liveUserResponse?.value?.engineers?.size.toString())

            }

        })
        return liveUserResponse

    }
}