package android.assignment.com.supportwheeloffate.Creaters

import android.assignment.com.supportwheeloffate.Interfaces.IRandomAdapter
import java.util.*

class RandomAdapter  : IRandomAdapter
    {

        var random:Random =Random()
        constructor( lRandom:Random) {
            random= lRandom
        }
        override fun Next(max: Int): Int {
            return random.nextInt(max)
        }
}