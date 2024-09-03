package com.example.phaylfirstproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.phaylfirstproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val animationDuration = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        startFadeInAnimation(binding.logo)
    }

    private fun startFadeInAnimation(view: View) {
        view.visibility = View.VISIBLE

        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = animationDuration
            fillAfter = true
        }

        view.startAnimation(fadeIn)

        Handler().postDelayed({
            startActivity(Intent(this, OnboardingActivity::class.java))
            finish()
        }, animationDuration)
    }
}


