package grechhub.cc.ua

import android.location.Location

interface IWorkWithFindAchivenment {
    fun showLocationOnGPS(location: Location?)
    fun showEnablrGPS(string: String)
    fun blockAccesToGPS()
}