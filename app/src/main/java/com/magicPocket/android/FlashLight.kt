package com.magicPocket.android

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Camera
import android.hardware.camera2.CameraManager
import android.os.Build

object FlashLight {
    private lateinit var manager: CameraManager
    private lateinit var mCamera: Camera
    private lateinit var mContext: Context
    //记录手电筒状态
    private var status = false
    fun init(context: Context) {
        mContext = context
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            manager = mContext.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        }
    }

    //打开手电筒
    //如果已经是打开状态，不需要打开
    fun open() {
        if (status) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                manager.setTorchMode("0", true)
            } catch (e: Exception) {
                println("您的手机不支持闪光灯！")
            }
        } else {
            val packageManager = mContext.packageManager
            val features = packageManager.systemAvailableFeatures
            for (featureInfo in features) {
                // 判断设备是否支持闪光灯
                if (PackageManager.FEATURE_CAMERA_FLASH == featureInfo.name) {
                    val parameters = mCamera.parameters
                    parameters.flashMode = Camera.Parameters.FLASH_MODE_TORCH
                    mCamera.parameters = parameters
                    mCamera.startPreview()
                }
            }
        }
        //记录手电筒状态为打开
        status = true
    }

    //关闭手电筒
    fun close() {
        //如果已经是关闭状态，不需要打开
        if (!status) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                manager.setTorchMode("0", false)
            } catch (e: java.lang.Exception) {
                println("失败！")
            }
        } else {
            mCamera.stopPreview()
            mCamera.release()
        }
        //记录手电筒状态为关闭
        status = false
    }
}