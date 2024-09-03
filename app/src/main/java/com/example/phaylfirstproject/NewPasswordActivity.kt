package com.example.phaylfirstproject

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.phaylfirstproject.databinding.ActivityNewPasswordBinding

class NewPasswordActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityNewPasswordBinding
    private var isPasswordVisible: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fun updateBackground(editText: EditText) {
            val backgroundRes = if (editText.hasFocus() || editText.text.isNotEmpty()) {
                R.drawable.edittext_underline
            } else {
                R.drawable.edittext_simple
            }
            editText.setBackgroundResource(backgroundRes)
        }

        binding.newPasswordEditTxt.apply {
            setOnFocusChangeListener { _, _ -> updateBackground(this) }
            addTextChangedListener { updateBackground(this) }
        }

        binding.passwordEditTxt.apply {
            setOnFocusChangeListener { _, _ -> updateBackground(this) }
            addTextChangedListener { updateBackground(this) }
        }

        binding.arrow.setOnClickListener {
            val intent = Intent(this, OtpActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        binding.eyeIcon1.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            updatePasswordVisibility1()
        }

        binding.eyeIcon2.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            updatePasswordVisibility2()
        }
    }


    private fun updatePasswordVisibility1() {
        if (isPasswordVisible) {
            binding.passwordEditTxt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.eyeIcon1.setImageResource(R.drawable.eye)
        } else {
            binding.passwordEditTxt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.eyeIcon1.setImageResource(R.drawable.closed_eye)
        }
        binding.passwordEditTxt.typeface = binding.passwordEditTxt.typeface
        binding.passwordEditTxt.setSelection(binding.passwordEditTxt.text.length)
    }

    private fun updatePasswordVisibility2() {
        if (isPasswordVisible) {
            binding.newPasswordEditTxt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.eyeIcon2.setImageResource(R.drawable.eye)
        } else {
            binding.newPasswordEditTxt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.eyeIcon2.setImageResource(R.drawable.closed_eye)
        }
        binding.newPasswordEditTxt.typeface = binding.newPasswordEditTxt.typeface
        binding.newPasswordEditTxt.setSelection(binding.newPasswordEditTxt.text.length)
    }

}


