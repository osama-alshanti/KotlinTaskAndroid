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
    private var itemAdapter: ItemAdapter? = null
    private lateinit var viewModel:MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataSource = ItemDatabase.getInstance(getApplication()).itemDatabaseDao
        val viewModelFactory = ViewModelFactory(dataSource)

         viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainVM::class.java)

        val list: MutableList<Item> = ArrayList()

        item_list.layoutManager = LinearLayoutManager(applicationContext)
        itemAdapter = ItemAdapter(list)
        item_list.adapter = itemAdapter



        viewModel.getAllItems().observe(this, Observer { items ->

            if (items.isEmpty()) {
                insertAllItems()
            }

            for (item in items) {
                Log.i(TAG, "Id : ${item.id} Name : ${item.title} , Flag ${item.flag}")
            }
            list.addAll(items)
            itemAdapter!!.notifyDataSetChanged()
        })
        
    }

    private fun insertAllItems(){
        viewModel.insertItem(Item(0, "Osama Alshanti", "Software engineer", true))
        viewModel.insertItem(Item(0, "Ali ahmed ", "Freelancer", false))
        viewModel.insertItem(Item(0, "Mohammed Ahmed", "Software engineer", false))
        viewModel.insertItem(Item(0, "Osama Alshanti", "Freelancer", false))
        viewModel.insertItem(Item(0, "Mohammed Ahmed ", "Software engineer", false))
    }

}