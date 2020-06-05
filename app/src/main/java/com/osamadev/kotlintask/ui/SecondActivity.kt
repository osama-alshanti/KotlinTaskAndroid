package com.osamadev.kotlintask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.devbyteviewer.viewmodels.MainVM
import com.example.android.trackmysleepquality.sleepdetail.ViewModelFactory
import com.osamadev.kotlintask.R
import com.osamadev.kotlintask.database.Item
import com.osamadev.kotlintask.database.ItemDatabase
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    private val TAG = "SecondActivity"
    private var id:Int = -1
    private var title:String?= null
    private var desc:String?= null
    private var flag:Boolean?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val dataSource = ItemDatabase.getInstance(getApplication()).itemDatabaseDao
        val viewModelFactory = ViewModelFactory(dataSource)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainVM::class.java)



        if(intent.extras != null){
            id = intent.getIntExtra("id",-1)
            title = intent.getStringExtra("title")
            desc = intent.getStringExtra("desc")
            flag = intent.getBooleanExtra("flag",false)
        }

        Log.i(TAG,"${title} , ${id} , ${desc}")

        viewModel.navigateToFirstActivity.observe(this, Observer {
            if(it == true){
                if (id == 2 && !flag!!){
                    viewModel.updateFlag(Item(id.toLong(),title!!,desc!!,true))
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                viewModel.doneNavigating()
            }
        }
        )

        btnConfirm.setOnClickListener {
            viewModel.onClose()
        }
    }
}