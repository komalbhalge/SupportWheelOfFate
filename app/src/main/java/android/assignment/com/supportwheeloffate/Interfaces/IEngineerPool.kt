package android.assignment.com.supportwheeloffate.Interfaces

import android.assignment.com.supportwheeloffate.Data.Engineer

interface IEngineerPool {
    fun Add(engineers:MutableList<Engineer>)
    fun cashEngineers(cashedEngineers: MutableList<Engineer>)
   fun  PullRandom(engineers: MutableList<Engineer>):Engineer
    var Available:Int
}