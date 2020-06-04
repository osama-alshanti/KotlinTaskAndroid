package com.osamadev.kotlintask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.core.app.Person.fromBundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.devbyteviewer.viewmodels.MainVM
import com.example.android.trackmysleepquality.sleepdetail.ViewModelFactory
import com.osamadev.kotlintask.R
import com.osamadev.kotlintask.database.Item
import com.osamadev.kotlintask.database.ItemDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var flag:Int = -1
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = arrayOf("1st item"," 2nd item","3rd item","4th item","5th item")

        val dataSource = ItemDatabase.getInstance(getApplication()).itemDatabaseDao
        val viewModelFactory = ViewModelFactory(-1, dataSource)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainVM::class.java)



        val list: MutableList<Item> = ArrayList()

        viewModel.items.observe(this, Observer {
            list.addAll(it)
            Log.v(TAG,"item "+it.get(0).title)
        })


        val listAdapter: MutableList<String> = ArrayList()
        if(list.size > 0){
            for (i in 0..4){
                listAdapter.add(list.get(i).title)
            }
        }


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)

        if(intent.extras != null){
            flag = intent.getIntExtra("flag",-1)
        }

        if(flag == -1){
            flag = 0
        }

        list_view.adapter = adapter


        list_view.setOnItemClickListener {parent, view, position, id ->

            val element = list_view.getItemIdAtPosition(position)
            Log.v(TAG,""+element)

            if(element.toInt() == flag){
                    val intent = Intent(this, SecondActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"Locked , try again! ",Toast.LENGTH_SHORT)
                }

                viewModel.doneNavigating()

        }



    }

}