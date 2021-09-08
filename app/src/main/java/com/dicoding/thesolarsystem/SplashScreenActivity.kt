package com.dicoding.thesolarsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.R.menu
import android.content.Intent
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.HandlerCompat.postDelayed

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            this@SplashScreenActivity.startActivity(mainIntent)
            this@SplashScreenActivity.finish()
        }, 8000)

        //source: http://www.codeplayon.com/2019/06/how-to-make-gif-splash-screen-in-android-studio-animated/

    }
}
