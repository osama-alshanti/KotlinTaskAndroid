package com.osamadev.kotlintask.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.devbyteviewer.viewmodels.MainVM
import com.example.android.trackmysleepquality.sleepdetail.ViewModelFactory
import com.osamadev.kotlintask.R
import com.osamadev.kotlintask.adapter.ItemAdapter
import com.osamadev.kotlintask.database.Item
import com.osamadev.kotlintask.database.ItemDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataSource = ItemDatabase.getInstance(getApplication()).itemDatabaseDao
        val viewModelFactory = ViewModelFactory(-1, dataSource)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainVM::class.java)


        val list: MutableList<Item> = ArrayList()

        viewModel.items.observe(this, Observer {items ->

            for (item in items){
                Log.i("TaskFragment","Id : ${item.id} Name : ${item.title} , Phone ${item.description}")
            }
            list.addAll(items)

        })

        viewModel.updateItem(0,true)


        item_list.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = ItemAdapter(list,applicationContext)
        }







    }

}