package com.example.aplicationcomponentspart1

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
                Log.i(activity.localClassName, "paused")
            }

            override fun onActivityStarted(activity: Activity) {
                Log.i(activity.localClassName, "started")

            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.i(activity.localClassName, "destroyed")

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Log.i(activity.localClassName, "savedInstance")

            }

            override fun onActivityStopped(activity: Activity) {
                Log.i(activity.localClassName, "stopped")

            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.i(activity.localClassName, "created")

            }

            override fun onActivityResumed(activity: Activity) {
                Log.i(activity.localClassName, "resumed")
            }
        })
    }
}