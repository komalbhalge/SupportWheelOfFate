package android.assignment.com.supportwheeloffate.Creaters

import android.assignment.com.supportwheeloffate.Interfaces.IRandomAdapter
import android.assignment.com.supportwheeloffate.Data.Engineer
import android.assignment.com.supportwheeloffate.Interfaces.IEngineerPool
import android.util.Log


class EngineerPool : IEngineerPool {
    private var randomAdapter: IRandomAdapter
    private var mEngineersAvailable: MutableList<Engineer>
    private var mCahedEngineerse: MutableList<Engineer> = ArrayList()

    constructor(lRandomAdapter: IRandomAdapter) {

        mEngineersAvailable = ArrayList()
        randomAdapter = lRandomAdapter
    }

    override fun PullRandom(engineers: MutableList<Engineer>): Engineer {
        var engineer: Engineer
        mEngineersAvailable = engineers.toMutableList()
        if (mCahedEngineerse != null && !mCahedEngineerse.isEmpty()) {

            /*Delete previously selected Engineers*/
            for (engineer in mCahedEngineerse) {
                if (mEngineersAvailable.contains(engineer)) {
                    mEngineersAvailable.remove(engineer)
                }
            }
        }
        engineer = mEngineersAvailable!!.get(randomAdapter.Next(mEngineersAvailable!!.size))

        /*Add Engineer to selected Engineers*/
        mCahedEngineerse.add(engineer)

        return engineer
    }

    override fun Add(engineers: MutableList<Engineer>) {
        mEngineersAvailable = engineers.toMutableList()
    }

    override fun cashEngineers(cashedEngineers: MutableList<Engineer>) {
        mCahedEngineerse = cashedEngineers.toMutableList()
    }

    override var Available: Int
        get() = mEngineersAvailable!!.size
        set(value) {}


}