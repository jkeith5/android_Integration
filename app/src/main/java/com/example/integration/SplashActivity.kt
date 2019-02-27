package com.example.integration


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    //  when you create objects val= value(constant) var=variable  ref: datatype
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //relate animation to objects
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation) //R= resource directory
        splashLogoPic.startAnimation(logoAnimation)

        val textAnimation= AnimationUtils.loadAnimation(this, R.anim.splash_text_animation) //change this to a separate
        //animation xml file
        splashText.startAnimation(textAnimation)

        //add a timer thread to set a time to switch to the homescreen(main)
        val viewPager= Intent(this, PlantsActivity::class.java)//intent is to change to a certain class
        val splashTimer = object : Thread() {
            override fun run(){
                try{
                    Thread.sleep(3000)
                }
                catch(e: InterruptedException){
                    e.printStackTrace()
                }
                finally{
                    startActivity(viewPager)
                    finish()
                }
                super.run()
            }
        }
        splashTimer.start()
    }
}