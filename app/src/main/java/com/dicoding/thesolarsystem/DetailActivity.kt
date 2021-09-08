package com.dicoding.thesolarsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DETAIL = "extra_detail"
        const val EXTRA_PHOTO = "extra_photo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val bigImage: ImageView = findViewById(R.id.image_detail_big)
        val tvNameOnBig: TextView = findViewById(R.id.tv_name_on_image_detail_big)
        val tvPlanetName: TextView = findViewById(R.id.tv_planet_name)
        val tvPlanetDetail: TextView = findViewById(R.id.tv_planet_detail)

        val name = intent.getStringExtra(EXTRA_NAME)
        val detail = intent.getStringExtra(EXTRA_DETAIL)
        val photo = intent.getIntExtra(EXTRA_PHOTO, 0)

        bigImage.setImageResource(photo)
        tvNameOnBig.text = name
        tvPlanetName.text = name
        tvPlanetDetail.text = detail
    }
}
