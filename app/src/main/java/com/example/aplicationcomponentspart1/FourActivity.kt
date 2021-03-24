package com.example.aplicationcomponentspart1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicationcomponentspart1.databinding.ActivityFourBinding

class FourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFourBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.activity4)

        binding.goForwardBtn.setOnClickListener {
            val intent = Intent(this, OneActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        binding.goBackBtn.setOnClickListener {
            finish()
        }
    }
}