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





    private val _navigateToSecondActivity =  MutableLiveData<Boolean?>()
    val navigateToSecondActivity: LiveData<Boolean?> get() = _navigateToSecondActivity


    val items = database.getAllItems()



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
            insert(Item(0, "Osama Alshanti","Software engineer",false))
            insert(Item(0, "Ali ahmed ","Freelancer",false))
            insert(Item(0, "Mohammed Ahmed ","Software engineer",false))
            insert(Item(0, "Osama Alshanti","Freelancer",false))
            insert(Item(0, "Mohammed Ahmed","Software engineer",false))
        }
    }

    fun updateItem(id: Long,flag:Boolean){
    uiScope.launch {
        updateById(id,flag)
    }

    }



    private suspend fun insert(item: Item){
        withContext(Dispatchers.IO) {
            database.insert(item)
        }
    }

    private suspend fun updateById(id: Long,flag:Boolean){
        withContext(Dispatchers.IO) {
            database.updateItem(id,flag)
        }
    }

    private suspend fun update(item: Item) {
        withContext(Dispatchers.IO) {
            database.update(item)
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
