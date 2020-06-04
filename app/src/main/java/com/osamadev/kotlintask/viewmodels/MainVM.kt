/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.devbyteviewer.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.osamadev.kotlintask.database.Item
import com.osamadev.kotlintask.database.ItemDatabaseDao
import kotlinx.coroutines.*


class MainVM(private val id: Long = 0L, val database: ItemDatabaseDao) : ViewModel() {

    private val TAG = "MainVM"
    private var viewModelJob = Job()
    private val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main +  viewModelJob)


    val items = database.getAllItems()


    /*
       val item1 = Item(-1,"1st item","one")
        val item2 = Item(-1,"2nd item","two")
        val item3 = Item(-1,"3rd item","three")
        val item4 = Item(-1,"4th item","four")
        val item5 = Item(-1,"5th item","five")

        viewModel.insert(item1)
        viewModel.insert(item2)
        viewModel.insert(item3)
        viewModel.insert(item4)
        viewModel.insert(item5)
*/

    private val _navigateToSecondActivity =  MutableLiveData<Boolean?>()
    val navigateToSecondActivity: LiveData<Boolean?> get() = _navigateToSecondActivity

    fun doneNavigating() {
        _navigateToSecondActivity.value = null
    }

    fun onClose() {
        _navigateToSecondActivity.value = true
    }

    init {
        insertItem()
        Log.v(TAG,"start VM ")

    }

    fun insertItem() {
        uiScope.launch {
            for (item in 1..5){
                val item = Item()
                insert(item)
            }
        }
    }

    private suspend fun insert(item: Item){
        withContext(Dispatchers.IO) {
            database.insert(item)
        }
    }


    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }






    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()

    }


}
