package com.example.cuprkv3

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.database.*
import io.radar.sdk.Radar
import io.radar.sdk.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    lateinit var lotList: MutableList<Lot>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lotList = mutableListOf()
        mDatabase = FirebaseDatabase.getInstance().getReference("lots")
        listView = findViewById(R.id.listView)

        // request permissions
        if (Build.VERSION.SDK_INT >= 23) {
            val requestCode = 0
            if (Build.VERSION.SDK_INT >= 29) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION), requestCode)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestCode)
            }
        }

        // listen for db changes, update screen when changes occur
        mDatabase.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                lotList.clear()
                if(p0.exists()) {
                    for (l in p0.children) {
                        val lot = l.getValue(Lot::class.java)
                        lotList.add(lot!!)
                    }

                    val adapter = LotAdapter (applicationContext, R.layout.lots, lotList)
                    listView.adapter = adapter
                }
            }
        })

        // TODO - Start tracking when user is driving and confirm found spot through notif. Stop when opposite

        Radar.initialize(this, "prj_test_pk_32ddbd2feb7c1266ab29d53d83cf635c6f04d477")

        button1.setOnClickListener {
            Radar.startTracking(RadarTrackingOptions.CONTINUOUS)
        }

        button2.setOnClickListener {
            Radar.stopTracking()
        }
    }
}
