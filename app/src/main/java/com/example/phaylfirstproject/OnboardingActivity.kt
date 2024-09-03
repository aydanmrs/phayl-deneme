package com.example.phaylfirstproject

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.phaylfirstproject.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var onboardingItems: List<OnboardingItem>
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onboardingItems = listOf(
            OnboardingItem(
                title = "Bütün dərslərinizi bir mərkəzdən izləyin",
                description = "Dərslərinizi asanlıqla idarə edin, elanlar və resurslarla aktual qalın.",
                button = "Növbəti"
            ),
            OnboardingItem(
                title = "Davamiyyət izləmənizi sadələşdirin",
                description = "İnteqrasiya edilmiş təqvimimizlə davamiyyətinizi asanlıqla izləyin və cədvəlinizdən xəbərdar olun.",
                button = "Növbəti"
            ),
            OnboardingItem(
                title = "Yeniliklərdən vaxtında xəbərdar olun",
                description = "Tapşırıqlar, testlər, elanlar və digər vacib yeniləmələr üçün dərhal bildirişlər alın.",
                button = "Başla"
            )
        )

        binding.skip.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        val adapter = OnboardingItemsAdapter(onboardingItems)
        binding.onBoardingViewPager.adapter = adapter

        setupIndicators()
        binding.onBoardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicators(position)
                binding.button.text = if (position == onboardingItems.size - 1) "Başla" else "Növbəti"
            }
        })

        binding.button.setOnClickListener {
            val nextItem = binding.onBoardingViewPager.currentItem + 1
            if (nextItem < onboardingItems.size) {
                binding.onBoardingViewPager.setCurrentItem(nextItem, true)
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun setupIndicators() {
        val indicators = Array(onboardingItems.size) { ImageView(this) }
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8, 0, 8, 0)
        }
        indicators.forEach { indicator ->
            indicator.layoutParams = layoutParams
            binding.indicatorsContainer.addView(indicator)
        }
        updateIndicators(0)
    }

    private fun updateIndicators(position: Int) {
        for (i in 0 until binding.indicatorsContainer.childCount) {
            val indicator = binding.indicatorsContainer.getChildAt(i) as ImageView
            val drawableRes = if (i == position) {
                R.drawable.indicator_active_background
            } else {
                R.drawable.indicator_inactive_background
            }
            indicator.setImageDrawable(ContextCompat.getDrawable(this, drawableRes))
        }
    }
}


