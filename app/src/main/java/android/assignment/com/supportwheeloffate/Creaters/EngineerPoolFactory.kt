package android.assignment.com.supportwheeloffate.Creaters

import android.assignment.com.supportwheeloffate.Interfaces.IEngineerPool
import android.assignment.com.supportwheeloffate.Interfaces.IEngineerPoolFactory
import android.assignment.com.supportwheeloffate.Interfaces.IEngineerRepository
import android.assignment.com.supportwheeloffate.Interfaces.IRandomAdapter


class EngineerPoolFactory :IEngineerPoolFactory{

    private lateinit var mEngineerRepository: IEngineerRepository;
    private lateinit var mRandomAdapter: IRandomAdapter;

    constructor(lEngineerRepository: IEngineerRepository, lRandomAdapter: IRandomAdapter) {
        mEngineerRepository = lEngineerRepository
        mRandomAdapter = lRandomAdapter
    }

    override fun Create(shiftsPerEngineerPerPeriod: Int): IEngineerPool {
        val pool = EngineerPool(mRandomAdapter)
        val engineers = mEngineerRepository.ReadAll()
        for (i in 0 until shiftsPerEngineerPerPeriod) {
            pool.Add(engineers)
        }
        return pool
    }
}