package android.assignment.com.supportwheeloffate.Interfaces

interface IEngineerPoolFactory {
    fun Create(shiftsPerEngineerPerPeriod: Int): IEngineerPool
}