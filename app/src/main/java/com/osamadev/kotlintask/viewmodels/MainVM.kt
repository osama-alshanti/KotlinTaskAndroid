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


class MainVM(val database: ItemDatabaseDao) : ViewModel() {

    private val TAG = "MainVM"
    private var viewModelJob = Job()
    private val uiScope: CoroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private var getAllItems: LiveData<List<Item>>? = null

    private val _navigateToFirstActivity = MutableLiveData<Boolean?>()
    val navigateToFirstActivity: LiveData<Boolean?> get() = _navigateToFirstActivity

    init {
        Log.v(TAG, "start VM ..")
        getAllItems = database.getAllItems()
    }


    fun getAllItems(): LiveData<List<Item>> {
        return getAllItems!!
    }

    fun doneNavigating() {
        _navigateToFirstActivity.value = null
    }

    fun onClose() {
        _navigateToFirstActivity.value = true
    }


    fun insertItem(item: Item) {
        uiScope.launch {
            insert(item)
        }
    }

    fun updateFlag(item: Item) {
        uiScope.launch {
            update(item)
        }
    }


    private suspend fun getItem(id: Long) {
        withContext(Dispatchers.IO) {
            database.get(id)
        }
    }

    private suspend fun insert(item: Item) {
        withContext(Dispatchers.IO) {
            database.insert(item)
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
