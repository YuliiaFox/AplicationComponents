package com.example.aplicationcomponentspart1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicationcomponentspart1.databinding.ActivityOneBinding

class OneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.activity1)

        binding.goForwardBtn.setOnClickListener {
            val intent = Intent(this, TwoActivity::class.java)
            startActivity(intent)
        }
        binding.goBackBtn.setOnClickListener {
            //Runtime.getRuntime().exec("kill ${android.os.Process.myPid()}")
            finish()
        }
    }
}