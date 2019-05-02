package android.assignment.com.supportwheeloffate

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.assignment.com.supportwheeloffate.Creaters.*
import android.assignment.com.supportwheeloffate.Data.EmployeeResponse
import android.assignment.com.supportwheeloffate.Data.Engineer
import android.assignment.com.supportwheeloffate.Interfaces.IEngineerPool
import android.assignment.com.supportwheeloffate.ViewModels.EmployeeViewModel
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {
    private lateinit var engineerPool1: IEngineerPool
    private var mEngineerIds: MutableList<Int> = ArrayList()
     var  cashedEngineers: MutableList<Engineer> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Create progressBar dynamically...

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        if (progressBar != null) {
            progressBar.visibility =  View.VISIBLE
        }


        var mEngineers: MutableList<Engineer> = fetchEmployeeData()

        button.setOnClickListener {

            initEngineer(engineerPool1, mEngineers)

        }
    }

    private fun initEngineerToday(engineer1: Engineer, engineer2: Engineer) {
        tvEmployeeName1.setText("Day: "+engineer1.name)
        tvEmployeeName2.setText("Night: "+engineer2.name)
    }
    private fun initEngineerYesterday(engineer1: Engineer, engineer2: Engineer) {
       p_emloyee1.setText("Day: "+engineer1.name)
        p_emloyee2.setText("Night: "+engineer2.name)
    }

    private fun initList(engineers: MutableList<Engineer>) {
        Log.e("KKTAG", "engineers: " + engineers.size)
        var engineerRepository: EngineerRepository = EngineerRepository(engineers)
        var randomAdapter: RandomAdapter = RandomAdapter(Random())
        var factory: EngineerPoolFactory = EngineerPoolFactory(engineerRepository, randomAdapter)
        engineerPool1 = factory.Create(1)
    }

    private fun initEngineer(engineerPool1: IEngineerPool, engineers: MutableList<Engineer>) {
        val engineer1: Engineer = engineerPool1.PullRandom(engineers)
        val engineer2: Engineer = engineerPool1.PullRandom(engineers)
        Log.e("HomeActivity", " >NAME 1: " + engineer1.name)
        Log.e("HomeActivity", " >NAME 2: " + engineer2.name)

        if (cashedEngineers !=null&&!cashedEngineers.isEmpty()&&cashedEngineers.size==2){
            yesterdayLayout.visibility =View.VISIBLE
            initEngineerYesterday(cashedEngineers.get(0),cashedEngineers.get(1))
            cashedEngineers = ArrayList()
        }
        cashedEngineers.add(engineer1)
        cashedEngineers.add(engineer2)
        initEngineerToday(engineer1, engineer2)
        engineerPool1.cashEngineers(cashedEngineers)

    }


    private fun fetchEmployeeData(): MutableList<Engineer> {
        var mEngineers: MutableList<Engineer> = ArrayList()
        val mEmployeeViewModel = ViewModelProviders.of(this@HomeActivity).get(EmployeeViewModel::class.java)
        mEmployeeViewModel.getEmployeeList()?.observe(this, Observer<EmployeeResponse> { employeeList ->

            mEngineers.addAll(employeeList?.engineers!!)

            initList(mEngineers)
            for (engineer: Engineer in mEngineers) {
                mEngineerIds.add(engineer.id)
            }
            progressBar.visibility = View.INVISIBLE
        })

        return mEngineers
    }
}
