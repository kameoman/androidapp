package com.example.kame.batapikaever

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.os.Handler
import android.os.IBinder

class TorchOnService : Service(),SensorEventListener {
    private val threshold: Float = 10f
    private val oldValue: Float = 0f
    private lateinit var  cameraManager: CameraManager
    private val cameraID: String? = null
    private var lightOn: Boolean = false
    //private var tag = "Bata Pika!"


    override fun onBind(intent: Intent): IBinder {
        super.onCreate()
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        cameraManager.registerTorchCallback(object : cameraManager.TorchCallback() {
            override fun onTorchModeChanged(cameraId: String, enabled: Boolean) {
                super.onTorchModeChanged(cameraId, enabled)
                cameraID = cameraId
                lightOn = enabled
            }
        }, Handler())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometter, SensorManager.SENSOR_DELAY_NORMAL);
        return super.onStartCommand(intent, flags, startId)
    }
}