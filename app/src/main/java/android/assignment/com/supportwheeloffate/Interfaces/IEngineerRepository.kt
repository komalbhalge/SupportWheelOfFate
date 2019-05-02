package android.assignment.com.supportwheeloffate.Interfaces

import android.assignment.com.supportwheeloffate.Data.Engineer

interface IEngineerRepository {

    /// Reads all the engineers from the repository
    fun ReadAll():MutableList<Engineer>
    /// Adds a new engineer to the repository
    fun Add(engineer:Engineer);

    /// Removes a specific engineer from the repository
    fun Remove( id:Int);

    /// Retrieves an engineer from the repository

    fun Find( id:Int):Engineer;

}