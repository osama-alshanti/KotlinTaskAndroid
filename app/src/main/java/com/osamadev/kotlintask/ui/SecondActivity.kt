package com.osamadev.kotlintask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.android.devbyteviewer.viewmodels.MainVM
import com.example.android.trackmysleepquality.sleepdetail.ViewModelFactory
import com.osamadev.kotlintask.R
import com.osamadev.kotlintask.database.ItemDatabase
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private var id:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val dataSource = ItemDatabase.getInstance(getApplication()).itemDatabaseDao
        val viewModelFactory = ViewModelFactory(-1, dataSource)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainVM::class.java)

        if(intent.extras != null){
            id = intent.getIntExtra("id",-1);
        }
        
        viewModel.updateItem(1,true)


        btnConfirm.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}