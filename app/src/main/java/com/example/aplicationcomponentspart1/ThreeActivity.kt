package com.example.aplicationcomponentspart1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicationcomponentspart1.databinding.ActivityThreeBinding

class ThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityThreeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.activity3)

        binding.goForwardBtn.setOnClickListener {
            val intent = Intent(this, FourActivity::class.java)
            startActivity(intent)
        }

        binding.goBackBtn.setOnClickListener {
            finish()
        }
    }
}