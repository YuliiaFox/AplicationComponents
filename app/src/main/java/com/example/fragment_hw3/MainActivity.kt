package com.example.fragment_hw3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fragment_hw3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: DataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(binding.fragment1.id, FirstFragment(), FirstFragment::class.java.simpleName)
            .add(binding.fragment2.id, SecondFragment(), SecondFragment::class.java.simpleName)
            .add(binding.fragment3.id, ThirdFragment(), ThirdFragment::class.java.simpleName)
            .commit()

        viewModel.switchFragments().observe(this) {
            if (it) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(binding.fragment2.id, ThirdFragment())
                    .replace(binding.fragment3.id, SecondFragment())
                    .commit()
            } else {
                supportFragmentManager
                    .beginTransaction()
                    .replace(binding.fragment2.id, SecondFragment())
                    .replace(binding.fragment3.id, ThirdFragment())
                    .commit()
            }
        }
    }
}