package com.example.aplicationcomponentspart1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicationcomponentspart1.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.MenuActivity)

        binding.firstPartBtn.setOnClickListener {
            val intent = Intent(this, OneActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.secondPartBtn.setOnClickListener {
            val intent = Intent(this, ServiceActivity::class.java)
            startActivity(intent)
        }
    }
}