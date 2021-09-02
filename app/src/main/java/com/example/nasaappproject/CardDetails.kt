package com.example.nasaappproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_card_details.*
import kotlinx.android.synthetic.main.photo_cell.view.*

class CardDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var intent = intent

        val aDate = intent.getStringExtra("Date")
        val aCamera = intent.getStringExtra("Camera")
        val aRover = intent.getStringExtra("Rover")
        val aStatus = intent.getStringExtra("Status")
        val aLaunch = intent.getStringExtra("Launch")
        val aLanding = intent.getStringExtra("Landing")
        val imgUrl = intent.getStringExtra("URL")


        actionBar.setTitle("Photo Details")
        textDate.text = aDate
        textCamera.text = aCamera
        textRover.text = aRover
        textStatus.text = aStatus
        textLaunch.text = aLaunch
        textLanding.text = aLanding

        Glide.with(this).load(imgUrl).into(imageView2)



    }
}