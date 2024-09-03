package com.example.phaylfirstproject

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.StyleSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.phaylfirstproject.databinding.ActivityOtpBinding

class OtpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBarsInsets.left, systemBarsInsets.top, systemBarsInsets.right, systemBarsInsets.bottom)
            insets
        }

        val fullText = "Lomarova@std.beu.edu.az e-poçt ünvanınıza göndərilən təsdiq kodunu daxil edərək hesabınızı təsdiqləyin. Lomarova@std.beu.edu.az"
        val spannableString = SpannableString(fullText)
        val boldText = "Lomarova@std.beu.edu.az"
        val start = fullText.indexOf(boldText)
        val end = start + boldText.length
        spannableString.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.txtView.text = spannableString


        binding.pin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updatePinViewColors()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.arrow.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, NewPasswordActivity::class.java))
        }

        updatePinViewColors()
        startCountdown()

    }

    private fun startCountdown() {
        val timerTextView = binding.second

        val countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                timerTextView.text = String.format("00:%02d", secondsRemaining)
            }

            override fun onFinish() {
                timerTextView.text = "00:00"
            }
        }

        countDownTimer.start()
    }

    private fun updatePinViewColors() {
        val pinView = binding.pin
        val filledColor = Color.parseColor("#7962FA")
        val emptyColor = Color.parseColor("#DEDFE1")

        val textLength = pinView.text.toString().length
        for (i in 0 until pinView.itemCount) {
            val lineColor = if (i < textLength) filledColor else emptyColor
            pinView.setLineColor(lineColor)
        }
        pinView.invalidate()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
        finish()
    }
}


