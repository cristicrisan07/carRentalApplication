package com.example.carrentalservice

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.core.app.ActivityCompat
import com.android.volley.toolbox.StringRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.delay

class MarkAsDelivered:AppCompatActivity() , OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark_as_delivered)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        this.setFinishOnTouchOutside(false)
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        setUpMap()
       val markersDetails=this.intent.extras!!.get("map") as ArrayList<MarkerOptions>
        for(marker:MarkerOptions in markersDetails){
            mMap.addMarker(marker)
        }
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markersDetails[markersDetails.size-1].position, 9.0f))

        mMap.setOnInfoWindowClickListener {
            val location=it.title
            if(this.intent.extras!!.get("subscription").toString()!="isSubscribed"){
                Toast.makeText(applicationContext,"You will be redirected to the payment processor...",Toast.LENGTH_LONG).show()

                startActivity(Intent(this,Payment::class.java).putExtra("Money",this.intent.extras!!.get("Money").toString()))
            }
            val uID = this.intent.extras!!.get("uID").toString()
            val VIN = this.intent.extras!!.get("VIN").toString()
            val logUrl = "http://10.0.2.2:8080/uploads/Park.php"
            val stringRequest: StringRequest = object : StringRequest(Method.POST,
                    logUrl,
                    {response ->
                        if(response=="Delivered!")
                        {setResult(Activity.RESULT_OK)}
                        else{
                            if(response=="No such address!")
                            {
                                Toast.makeText(applicationContext,"No such address PHP error; contact support!",Toast.LENGTH_LONG).show()
                                setResult(Activity.RESULT_CANCELED)
                            }
                        }
                        this.finish()
                    },
                    {
                        Toast.makeText(this, "Could not subscribe!", Toast.LENGTH_LONG).show()
                    }) {

                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["idClient"]=uID
                    params["location"]=location
                    params["vin"]=VIN
                    return params
                }

            }
            MySingleton.MySingleton.getInstance(this).addToRequestQueue(stringRequest)
        }

    }
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }
    override fun onMarkerClick(p0: Marker?) = false
}