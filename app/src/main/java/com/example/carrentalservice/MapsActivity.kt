package com.example.carrentalservice

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.carrentalservice.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener{

    private lateinit var mMap: GoogleMap
    private lateinit var mMap1: GoogleMap
    private lateinit var mMap2: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

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
        mMap.addMarker(MarkerOptions().position(fantanele).title("Strada Fantanele 7").snippet("3 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fantanele, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)
        mMap.setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener {
            val  intent=  Intent(this,SecondActivity::class.java)
            startActivity(intent)
        })
       //mMap1 = googleMap
        val tasnad = LatLng(46.759767527708235, 23.546233828927765)
        mMap.addMarker(MarkerOptions().position(tasnad).title("Strada Tasnad 22").snippet("1 car available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tasnad, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        //mMap2 = googleMap
        val panaitCerna = LatLng(46.561091141191454, 23.776674811718117)
        mMap.addMarker(MarkerOptions().position(panaitCerna).title("Strada Panait Cerna").snippet("3 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(panaitCerna, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)


        val fabrciiZahar = LatLng(46.78182783889745, 23.617740371255397)
        mMap.addMarker(MarkerOptions().position(fabrciiZahar).title("Strada Fabricii de Zahar 12").snippet("4 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fabrciiZahar, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val bucuresti88 = LatLng(46.78172687568074, 23.612289433290293)
        mMap.addMarker(MarkerOptions().position(bucuresti88).title("Strada Bucuresti 88").snippet("3 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bucuresti88, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val campuPainii = LatLng(46.78539135725131, 23.612882689707682)
        mMap.addMarker(MarkerOptions().position(campuPainii).title("Strada Campu Painii-Clujana").snippet("2 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campuPainii, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val dunarii143 = LatLng(46.77839537533261, 23.641314882591903)
        mMap.addMarker(MarkerOptions().position(dunarii143).title("Strada Dunarii 143-147").snippet("0 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dunarii143, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val ceanuMare = LatLng(46.65049992318051, 23.96709612381386)
        mMap.addMarker(MarkerOptions().position(ceanuMare).title("Ceanu Mare").snippet("1 car available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ceanuMare, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val transilvaniei48_aiud = LatLng(46.31248684080088, 23.722609760352604)
        mMap.addMarker(MarkerOptions().position(transilvaniei48_aiud).title("Bulevardul Transilvaniei 48").snippet("3 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(transilvaniei48_aiud, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val vasileGoldis_alba = LatLng(46.068075654973434, 23.55787160341144)
        mMap.addMarker(MarkerOptions().position(vasileGoldis_alba).title("Strada Vasile Goldis  13").snippet("5 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vasileGoldis_alba, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val piataRomanaBuc = LatLng(44.44425082651276, 26.098013419624767)
        mMap.addMarker(MarkerOptions().position(piataRomanaBuc).title("Piata Romana ").snippet("7 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(piataRomanaBuc, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val sfintiiVoievozi_buc = LatLng(44.44716142492185, 26.087649363453632)
        mMap.addMarker(MarkerOptions().position(sfintiiVoievozi_buc).title("Strada Sfintii Voievozi 61-47").snippet("3 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sfintiiVoievozi_buc, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val arculDeTriumf = LatLng(44.46616699207833, 26.0749831778645)
        mMap.addMarker(MarkerOptions().position(arculDeTriumf).title("Bulevardul Marasti 2-14-Arcul de Triumf").snippet("2 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(arculDeTriumf, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val piata1848 = LatLng(46.17442340002705, 23.923152812703783)
        mMap.addMarker(MarkerOptions().position(piata1848).title("Piata 1848").snippet("4 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(piata1848, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val vivoCj = LatLng(46.750984740425174, 23.53019201588635)
        mMap.addMarker(MarkerOptions().position(vivoCj).title("VIVO Cluj").snippet("5 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vivoCj, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val primariaFloresti = LatLng(46.743728752288945, 23.485590828065938)
        mMap.addMarker(MarkerOptions().position(primariaFloresti).title("Strada Avram Iancu 170-Primaria Floresti").snippet("3 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(primariaFloresti, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val someseni = LatLng(46.77843598677337, 23.664690583329207)
        mMap.addMarker(MarkerOptions().position(someseni).title("Calea Someseni").snippet("3 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(someseni, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)


        val parcului7 = LatLng(46.787840614260176, 23.723763771834868)
        mMap.addMarker(MarkerOptions().position(parcului7).title("Strada Parcului 7-Sannicoara").snippet("4 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parcului7, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val vlahuta30_40 = LatLng(46.769950068812186, 23.558306680459335)
        mMap.addMarker(MarkerOptions().position(vlahuta30_40).title("Strada Vlahuta 30-40").snippet("3 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vlahuta30_40, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val piataUnirii = LatLng(44.46616699207833, 26.0749831778645)
        mMap.addMarker(MarkerOptions().position(piataUnirii).title("Piata Unirii").snippet("8 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(piataUnirii, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val plopilor71_67 = LatLng(46.762982001174365, 23.561061706203994)
        mMap.addMarker(MarkerOptions().position(plopilor71_67).title("Strada Plopilor 71-67").snippet("3 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plopilor71_67, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val uniriiChinteni  = LatLng(46.863942329280185, 23.536021542319787)
        mMap.addMarker(MarkerOptions().position(uniriiChinteni).title("Strada Unirii 12 Chinteni").snippet("2 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uniriiChinteni, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

        val piataMarasti  = LatLng(46.778751909106504, 23.614758227072144)
        mMap.addMarker(MarkerOptions().position(piataMarasti).title("Piata Marasti").snippet("3 cars available"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(piataMarasti, 9.0f))
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.setOnMarkerClickListener(this)

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

    override fun onMarkerClick(p0: Marker?)=false

}