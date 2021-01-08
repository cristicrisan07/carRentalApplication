package com.example.carrentalservice

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.toolbox.StringRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONArray
import org.json.JSONObject

import kotlin.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var serverResponse: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        try {
            val uID = this.intent.extras!!.get("user_id").toString()
            findViewById<TextView>(R.id.notloggedin).text = ""
            val logUrl = "http://34.107.31.239/UserSubsContr.php"
            val stringRequest: StringRequest = object : StringRequest(Method.POST,
                    logUrl,
                    { response ->

                        serverResponse = response
                        val userMenu: ImageButton = findViewById(R.id.userMenu)


                            userMenu.setOnClickListener {
                                val nextAct = Intent(this, UserMenu::class.java)
                                nextAct.putExtra("user_id", this.intent.extras!!.get("user_id").toString())
                                nextAct.putExtra("firstName", this.intent.extras!!.get("firstName").toString())
                                nextAct.putExtra("lastName", this.intent.extras!!.get("lastName").toString())
                                nextAct.putExtra("serverResponse", serverResponse)
                                startActivityForResult(nextAct, LAUNCH_USER_MENU)
                            }

                    },
                    { _ ->

                        Toast.makeText(this, "nu a mers", Toast.LENGTH_LONG).show()
                    }) {

                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["user_id"] = uID

                    return params
                }

            }
            MySingleton.MySingleton.getInstance(this).addToRequestQueue(stringRequest)
        } catch (excp: Exception) {
            findViewById<TextView>(R.id.notloggedin).text = "Not logged in"
            val userMenu: ImageButton = findViewById(R.id.userMenu)
            userMenu.visibility=INVISIBLE
        }

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


        val fantanele = LatLng(46.767243909964, 23.550879984745006)
        mMap.addMarker(MarkerOptions().position(fantanele).title("Strada Fantanele 7"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fantanele, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)

        var carQuery: String? = null

        mMap.setOnMarkerClickListener {

            if (findViewById<TextView>(R.id.notloggedin).text != "Not logged in") {
                var location = it.title.toString()
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("user_id", this.intent.extras!!.get("user_id").toString())
                val logUrl = "http://34.107.31.239/retrieveCars.php"


                val stringRequest: StringRequest = object : StringRequest(Method.POST,
                        logUrl,
                        { response ->
                                carQuery=response
                            if (carQuery != "There are no cars available at this location") {
                                if (carQuery != "No such address!") {
                                    val nrOfCarsAvailable = JSONArray(response).length()
                                    it.snippet = "$nrOfCarsAvailable cars available"
                                it.isVisible=true
                                    it.showInfoWindow()
                                }
                            }
                            else{
                                it.snippet= "No cars currently available"
                                it.isVisible=true
                                it.showInfoWindow()
                            }
                        },
                        { _ ->

                            Toast.makeText(this, "Unable to retrieve data for this location. Contact support!", Toast.LENGTH_LONG).show()
                        }) {

                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["Location"] = location
                        return params
                    }

                }
                MySingleton.MySingleton.getInstance(this).addToRequestQueue(stringRequest)
                return@setOnMarkerClickListener false

            } else {

                var nextAct = Intent(this, LoginActivity::class.java)
                Toast.makeText(applicationContext, "Please log in or register to see the available cars.", Toast.LENGTH_LONG).show()
                startActivity(nextAct)
                this.finish()
                return@setOnMarkerClickListener true
            }
        }


        val tasnad = LatLng(46.759767527708235, 23.546233828927765)
        mMap.addMarker(MarkerOptions().position(tasnad).title("Strada Tasnad 22"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tasnad, 9.0f))


        //mMap2 = googleMap
        val panaitCerna = LatLng(46.561091141191454, 23.776674811718117)
        mMap.addMarker(MarkerOptions().position(panaitCerna).title("Strada Panait Cerna"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(panaitCerna, 9.0f))



        val fabrciiZahar = LatLng(46.78182783889745, 23.617740371255397)
        mMap.addMarker(MarkerOptions().position(fabrciiZahar).title("Strada Fabricii de Zahar 12"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fabrciiZahar, 9.0f))

        val bucuresti88 = LatLng(46.78172687568074, 23.612289433290293)
        mMap.addMarker(MarkerOptions().position(bucuresti88).title("Strada Bucuresti 88"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bucuresti88, 9.0f))


        val campuPainii = LatLng(46.78539135725131, 23.612882689707682)
        mMap.addMarker(MarkerOptions().position(campuPainii).title("Strada Campu Painii-Clujana"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campuPainii, 9.0f))

        val dunarii143 = LatLng(46.77839537533261, 23.641314882591903)
        mMap.addMarker(MarkerOptions().position(dunarii143).title("Strada Dunarii 143-147"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dunarii143, 9.0f))


        val ceanuMare = LatLng(46.65049992318051, 23.96709612381386)
        mMap.addMarker(MarkerOptions().position(ceanuMare).title("Ceanu Mare"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ceanuMare, 9.0f))


        val transilvaniei48_aiud = LatLng(46.31248684080088, 23.722609760352604)
        mMap.addMarker(MarkerOptions().position(transilvaniei48_aiud).title("Bulevardul Transilvaniei 48"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(transilvaniei48_aiud, 9.0f))


        val vasileGoldis_alba = LatLng(46.068075654973434, 23.55787160341144)
        mMap.addMarker(MarkerOptions().position(vasileGoldis_alba).title("Strada Vasile Goldis  13"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vasileGoldis_alba, 9.0f))


        val piataRomanaBuc = LatLng(44.44425082651276, 26.098013419624767)
        mMap.addMarker(MarkerOptions().position(piataRomanaBuc).title("Piata Romana "))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(piataRomanaBuc, 9.0f))


        val sfintiiVoievozi_buc = LatLng(44.44716142492185, 26.087649363453632)
        mMap.addMarker(MarkerOptions().position(sfintiiVoievozi_buc).title("Strada Sfintii Voievozi 61-47"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sfintiiVoievozi_buc, 9.0f))


        val arculDeTriumf = LatLng(44.46616699207833, 26.0749831778645)
        mMap.addMarker(MarkerOptions().position(arculDeTriumf).title("Bulevardul Marasti 2-14-Arcul de Triumf"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arculDeTriumf, 9.0f))

        val piata1848 = LatLng(46.17442340002705, 23.923152812703783)
        mMap.addMarker(MarkerOptions().position(piata1848).title("Piata 1848"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(piata1848, 9.0f))

        val vivoCj = LatLng(46.750984740425174, 23.53019201588635)
        mMap.addMarker(MarkerOptions().position(vivoCj).title("VIVO Cluj"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vivoCj, 9.0f))


        val primariaFloresti = LatLng(46.743728752288945, 23.485590828065938)
        mMap.addMarker(MarkerOptions().position(primariaFloresti).title("Strada Avram Iancu 170-Primaria Floresti"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(primariaFloresti, 9.0f))

        val someseni = LatLng(46.77843598677337, 23.664690583329207)
        mMap.addMarker(MarkerOptions().position(someseni).title("Calea Someseni"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(someseni, 9.0f))


        val parcului7 = LatLng(46.787840614260176, 23.723763771834868)
        mMap.addMarker(MarkerOptions().position(parcului7).title("Strada Parcului 7-Sannicoara"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parcului7, 9.0f))


        val vlahuta30_40 = LatLng(46.769950068812186, 23.558306680459335)
        mMap.addMarker(MarkerOptions().position(vlahuta30_40).title("Strada Vlahuta 30-40"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vlahuta30_40, 9.0f))


        val piataUnirii = LatLng(44.46616699207833, 26.0749831778645)
        mMap.addMarker(MarkerOptions().position(piataUnirii).title("Piata Unirii"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(piataUnirii, 9.0f))


        val plopilor71_67 = LatLng(46.762982001174365, 23.561061706203994)
        mMap.addMarker(MarkerOptions().position(plopilor71_67).title("Strada Plopilor 71-67"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plopilor71_67, 9.0f))


        val uniriiChinteni = LatLng(46.863942329280185, 23.536021542319787)
        mMap.addMarker(MarkerOptions().position(uniriiChinteni).title("Strada Unirii 12 Chinteni"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uniriiChinteni, 9.0f))


        val piataMarasti = LatLng(46.778751909106504, 23.614758227072144)
        mMap.addMarker(MarkerOptions().position(piataMarasti).title("Piata Marasti"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(piataMarasti, 9.0f))


        mMap.setOnInfoWindowClickListener {


            if (carQuery != "There are no cars available at this location") {

                if (carQuery != "No such address!") {
                    val jsonarr = JSONArray(carQuery)
                    val jsonobj = ArrayList<JSONObject>()
                    for (i in 0 until jsonarr.length()) {
                        jsonobj.add(jsonarr.getJSONObject(i))
                    }
                    for (i in 0 until jsonobj.size) {
                        intent.putExtra(i.toString(), jsonobj[i].getString("VIN"))
                    }

                    startActivityForResult(intent, LAUNCH_CAR_LIST)
                } else {
                    Toast.makeText(applicationContext, "Wrong address. Check code!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "There are no cars available at this location", Toast.LENGTH_LONG).show()
            }


        }


    }


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val LAUNCH_CAR_LIST = 2
        private const val LAUNCH_USER_MENU = 3
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LAUNCH_CAR_LIST) {
            if (resultCode == Activity.RESULT_OK) {
                if (JSONArray(serverResponse).getJSONObject(1).getString("agreeStatus") != "carRented") {
                    serverResponse = serverResponse.replace("nocarRented", "carRented")
                }
            }
        } else {
            TODO("Set behaviour in case of new subscription added")
        }

    }
}