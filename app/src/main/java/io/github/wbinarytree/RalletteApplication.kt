package io.github.wbinarytree

import android.app.Application

class RalletteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @JvmStatic
        lateinit var instance: Application
    }
}