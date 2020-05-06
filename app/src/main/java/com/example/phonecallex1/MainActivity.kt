package com.example.phonecallex1

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bt_phone.setOnClickListener {
            phoneCall()
        }

    }

    fun phoneCall() {

        if (et_phone.text.trim().isEmpty()) {
            Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_LONG).show()
        } else {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                call()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            }
        }


    }

    fun call() {
        var i = Intent()
        i.action = Intent.ACTION_DIAL
        i.data = Uri.parse("tel:" + et_phone.text.trim().toString())
        startActivity(i)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty()) {
                if (grantResults.get(0) == PackageManager.PERMISSION_GRANTED) {
                    call()
                }
            }
        }
    }

}

