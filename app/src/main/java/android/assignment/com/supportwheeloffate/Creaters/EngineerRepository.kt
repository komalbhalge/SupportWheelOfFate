package android.assignment.com.supportwheeloffate.Creaters

import android.assignment.com.supportwheeloffate.Data.Engineer
import android.assignment.com.supportwheeloffate.Interfaces.IEngineerRepository

class EngineerRepository : IEngineerRepository {
    var engineerList: MutableList<Engineer>? = null

    constructor(lEngineersList:MutableList<Engineer>){
        engineerList= lEngineersList
    }
    override fun ReadAll(): MutableList<Engineer> {
        return this!!.engineerList!!
    }

    override fun Add(engineer: Engineer) {
        engineerList!!.add(engineer)
    }

    override fun Remove(id: Int) {
        val index= engineerList!!.indexOfFirst { it.id==id }
        if(index>=0){
            val engineer:Engineer = engineerList!![index]
            engineerList!!.remove(engineer)
        }

    }

    override fun Find(id: Int): Engineer {
        var engineer:Engineer=Engineer(0,"Dummy")
        val index= engineerList!!.indexOfFirst { it.id==id }
        if(index>=0){
            engineer = engineerList!![index]
           return engineer
        }
        return engineer
    }
}