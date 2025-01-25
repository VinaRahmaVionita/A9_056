package com.example.uas

import android.app.Application
import com.example.uas.DI.AppContainer
import com.example.uas.DI.BioskopContainer

class BioskopApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= BioskopContainer()
    }
}
