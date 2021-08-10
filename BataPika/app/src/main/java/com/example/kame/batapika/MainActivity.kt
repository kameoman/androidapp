package com.example.kame.batapika

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity(), SensorEventListener {
    private val threshold: Float = 10f
    private var oldValue: Float = 0f
    private lateinit var cameraManager: CameraManager
    private var cameraID: String? = null
    private var lightOn:Boolean = false

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null)return
        if(event.sensor.type == Sensor.TYPE_ACCELEROMETER){
            val zDiff = Math.abs(event.values[2] - oldValue)
            if(zDiff > threshold){
                torchOn()
            }
            oldValue = event.values[2]
        }
    }



    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraManager.registerTorchCallback(@RequiresApi(Build.VERSION_CODES.M)
        object : CameraManager.TorchCallback() {
            override fun onTorchModeChanged(cameraId: String, enabled: Boolean) {
                super.onTorchModeChanged(cameraId, enabled)
                cameraID = cameraId
                lightOn = enabled
            }
        },  Handler())
    }

    override fun onResume() {
        super.onResume()
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onPause() {
        super.onPause()
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)

        if (cameraID != null) {
            try {
                if (lightOn) {
                    cameraManager.setTorchMode(cameraID!!, false)
                }
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        }

    }



    @RequiresApi(Build.VERSION_CODES.M)
    private fun torchOn() {
        if(cameraID != null){
            try{
                if (!lightOn){
                    cameraManager.setTorchMode(cameraID!!,true)
                } else {
                    cameraManager.setTorchMode(cameraID!!,false)
                }
            }catch (e: CameraAccessException){
                e.printStackTrace()
            }
        }

    }



}