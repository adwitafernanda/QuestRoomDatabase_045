package com.example.pertemuan10

import android.app.Application
import com.example.pertemuan10.dependeciesinjection.ContainerApp

class KrsApp : Application() {
    lateinit var  containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}