package com.example.aplicationcomponentspart1

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class CounterService : Service() {
    private val binder = CustomBinder()
    private var counter = 0

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    inner class CustomBinder : Binder() {
        fun getService(): CounterService {
            return this@CounterService
        }

        fun getHowManyTimesWasAccessed(): Int {
            counter = counter.inc()
            return counter
        }
    }
}