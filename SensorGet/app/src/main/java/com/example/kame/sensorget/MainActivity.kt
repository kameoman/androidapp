package com.example.kame.sensorget

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var buffer = StringBuilder()

        var sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sList: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        for (sensor: Sensor in sList) {
            //Log.v(tag, sensor.type.toString())
            //Log.v(tag, "${sensor.type.toString()},${sensor.name},${sensor.vendor}")
            //buffer.append("${sensor.type},${sensor.name},${sensor.vendor}\n")
            //Log.v(tag, "${sensor.stringType},${sensor.name},${sensor.vendor}")         // API Level >=  20
            buffer.append("${sensor.stringType},${sensor.name},${sensor.vendor}\n")
        }
        txt01.text = buffer.toString()


    }
}