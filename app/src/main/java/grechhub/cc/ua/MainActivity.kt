package grechhub.cc.ua


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.*


class MainActivity : AppCompatActivity() {
    var tvStatusGPS:TextView?=null
    var tvEnabledGPS: TextView? = null
    var tvLocationGPS: TextView? = null
    var locationManager: LocationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvStatusGPS=findViewById(R.id.tvStatusGPS)
        tvEnabledGPS=findViewById(R.id.tvEnabledGPS)
        tvLocationGPS=findViewById(R.id.tvLocationGPS)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
       // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, locationListener)
        Log.v("gps demo", "start")
        ActivityCompat.requestPermissions(
            this, listOf<String>(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ).toTypedArray(),0
        )

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
try {
    locationManager!!.requestLocationUpdates(
        LocationManager.GPS_PROVIDER, (
                1000 * 1).toLong(), 10f, locationListener!!
    )
}catch (e:Exception){
    val x= e
    val d = x
    Log.v("gps demo", "requestLocationUpdates")
}
            Log.v("gps demo", "requestLocationUpdates")
            return;
        }
       // checkEnabled()
       /* if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager!!.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, (
                    1000 * 1).toLong(), 10f, locationListener!!
        )*/
        Log.v("gps demo", "requestLocationUpdates")
        checkEnabled()

    }
    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationManager!!.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, (
                    1000 * 10).toLong(), 10f, locationListener!!
        )
        Log.v("gps demo", "requestLocationUpdates")
        checkEnabled()
    }

    override fun onPause() {
        super.onPause()
        locationManager!!.removeUpdates(locationListener!!)
    }
////////
private var locationListener: LocationListener? = object : LocationListener {
    override fun onLocationChanged(location: Location) {
        showLocation(location)
    }

    override fun onProviderDisabled(provider: String) {
        checkEnabled()
    }

    override fun onProviderEnabled(provider: String) {
        checkEnabled()
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        showLocation(locationManager?.getLastKnownLocation(provider))
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        if (provider == LocationManager.GPS_PROVIDER) {
            tvStatusGPS?.setText("Status: $status")
        }
    }
}
    //////
    private fun showLocation(location: Location?) {
        if (location == null) return
        if (location.provider == LocationManager.GPS_PROVIDER) {
            formatLocation(location)?.let { Log.v("gps demo", it) }
            tvLocationGPS?.setText(formatLocation(location))

        }
    }
    private fun formatLocation(location: Location?): String? {
        return if (location == null) "" else java.lang.String.format(
            "Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3\$tF %3\$tT",
            location.latitude, location.longitude, Date(
                location.time
            )
        )
    }

    private fun checkEnabled() {
        Log.v("gps demo","Enabled: " +
                                     locationManager
                                ?.isProviderEnabled(LocationManager.GPS_PROVIDER))
        tvEnabledGPS?.setText(
            "Enabled: "
                    + locationManager
                ?.isProviderEnabled(LocationManager.GPS_PROVIDER)
        )

       // showLocation(locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER))


    }

    fun onClickLocationSettings(view: View?) {
        startActivity(
            Intent(
                Settings.ACTION_LOCATION_SOURCE_SETTINGS
            )
        )
    }
}