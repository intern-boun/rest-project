package com.example.restproject.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restproject.network.Api
import com.example.restproject.network.Property
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _properties = MutableLiveData<List<Property>>()

    val properties: LiveData<List<Property>>
        get() = _properties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getAllProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getAllProperties() {

        coroutineScope.launch {
            var getPropertiesDeferred = Api.retrofitService.getProperties()
            try {

                val listResult =  getPropertiesDeferred.await()
                _properties.value = listResult

            } catch (e: Exception) {

                _properties.value = ArrayList()

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}