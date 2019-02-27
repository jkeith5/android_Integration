package com.example.integration

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_plants.*

class PlantsActivity : AppCompatActivity() {

    lateinit var planetAdapter: PlanetAdapter
    lateinit var indicators: Array<ImageView?>
    var numIndicators:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plants)
        setupViewPager()
        setupIndicators()
        setupSkipButton()
    }

    private fun setupSkipButton() {
        skipButton.setOnClickListener {
            skipToHome()
        }
    }

    private fun setupIndicators() {
        numIndicators = planetAdapter.getCount()
        indicators = arrayOfNulls<ImageView>(numIndicators)

        for (i in 0 until numIndicators) {
            indicators[i] = ImageView(this)
            indicators[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_indicator_white_24dp
                )
            )
            val params =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
            indicatorLayout.addView(indicators[i], params)
        }

        indicators[0]!!.setImageDrawable(
            ContextCompat.getDrawable(
                applicationContext,
                R.drawable.ic_indicator_black_24dp
            )
        )

        ImageSlider.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until numIndicators) {
                    indicators[i]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.ic_indicator_white_24dp
                        )
                    )
                }
                indicators[position]!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_indicator_black_24dp
                    )
                )
                indicatorLayout.setBackgroundColor(Color.TRANSPARENT)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun setupViewPager() {
        planetAdapter = PlanetAdapter(this)
        ImageSlider.setAdapter(planetAdapter)
    }

    private fun skipToHome() {
        val home = Intent(this, MainActivity::class.java)
        startActivity(home)
    }
}
