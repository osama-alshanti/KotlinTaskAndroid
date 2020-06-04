package com.osamadev.kotlintask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.osamadev.kotlintask.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btnConfirm.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("flag",1)
            startActivity(intent)

        }
    }
}