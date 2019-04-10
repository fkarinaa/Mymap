package com.example.myapplication

import android.annotation.SuppressLint
import android.location.Location
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

import com.google.android.gms.maps.model.MarkerOptions


class RouteActivity : FragmentActivity(), GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, OnMapReadyCallback {

    private var mMap: GoogleMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.route_activity)

        //Инициализируем карту
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment!!.getMapAsync(this)

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        mMap = map

        // TODO: Before enabling the My Location layer, you must request
        // location permission from the user. This sample does not include
        // a request for location permission.
        mMap!!.isMyLocationEnabled = true
        mMap!!.setOnMyLocationButtonClickListener(this)
        mMap!!.setOnMyLocationClickListener(this)

        mMap!!.setOnMapClickListener { latLng ->
            val markerOptions = MarkerOptions()
            markerOptions.position(latLng)
//            markerOptions.title(latLng.latitude + " : " + latLng.longitude)
            // mMap.clear(); //Когда пользователь коснется другой позиции карты Google, он очистит ранее затронутую позицию.
            mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
            mMap!!.addMarker(markerOptions)
          //  Toast.makeText(this@RouteActivity, "Координаты вашего маркера:\n" + "ширина " + latLng.latitude + " : \n" + "долгота " +
            //        latLng.longitude, Toast.LENGTH_LONG).show()
        }
    }


    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Current location:\n$location", Toast.LENGTH_LONG).show()
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Моё месторасположение", Toast.LENGTH_SHORT).show()
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false
    }

}
