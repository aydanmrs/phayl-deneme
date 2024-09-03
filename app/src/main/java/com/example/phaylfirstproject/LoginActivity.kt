package com.example.phaylfirstproject

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.phaylfirstproject.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.forgetPassword.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

        fun updateBackground(editText: EditText) {
            val backgroundRes = if (editText.hasFocus() || editText.text.isNotEmpty()) {
                R.drawable.edittext_underline
            } else {
                R.drawable.edittext_simple
            }
            editText.setBackgroundResource(backgroundRes)
        }

        binding.emailEditTxt.apply {
            setOnFocusChangeListener { _, _ -> updateBackground(this) }
            addTextChangedListener { updateBackground(this) }
        }

        binding.passwordEditTxt.apply {
            setOnFocusChangeListener { _, _ -> updateBackground(this) }
            addTextChangedListener { updateBackground(this) }
        }

        binding.eyeIcon.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            updatePasswordVisibility()
        }
    }

    private fun updatePasswordVisibility() {
        if (isPasswordVisible) {
            binding.passwordEditTxt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.eyeIcon.setImageResource(R.drawable.eye)
        } else {
            binding.passwordEditTxt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.eyeIcon.setImageResource(R.drawable.closed_eye)
        }
        binding.passwordEditTxt.setSelection(binding.passwordEditTxt.text.length)
    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}


