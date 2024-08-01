package com.techinnovation.mainfragment

import android.app.Application
import android.content.res.Resources


open class App:Application() {
//    private val resources: Resources? = null


    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object{
        protected lateinit var application: Application

        fun getInstance(): Application {
            return application
        }
    }

}
