package com.example.aplicationcomponentspart1

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicationcomponentspart1.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {

    var myService: CounterService? = null
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.ServiceActivity)
        val intent = Intent(this, CounterService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        binding.counterBtn.setOnClickListener {
            val count = myService?.CustomBinder()?.getHowManyTimesWasAccessed()
            Toast.makeText(this, "Was requested $count times", Toast.LENGTH_SHORT)
                .show()

        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as CounterService.CustomBinder
            myService = binder.getService()
            isBound = true
        }
    }
}