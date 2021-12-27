package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.ApiStatus
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer


class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)

    val asteroids = asteroidRepository.asteroids

    private val _clickOnAsteroid = MutableLiveData<Asteroid>()
    val clickOnAsteroid : LiveData<Asteroid>
        get() = _clickOnAsteroid

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    val isLoadingAndEmpty: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>()
    val isErrorAndEmpty: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>()

    init {
        setObserversForMediators()
        viewModelScope.launch {
            getAsteroidsProperties()
        }
    }

    private fun setObserversForMediators() {

        //changing the boolean isLoadingAndEmpty value if  status or list of asteroids changes
        isLoadingAndEmpty.addSource(status, Observer<ApiStatus> { value ->
            isLoadingAndEmpty.setValue(
                asteroids.value.isNullOrEmpty() && value == ApiStatus.LOADING
            )
        })
        isLoadingAndEmpty.addSource(asteroids, Observer<List<Asteroid>> { value ->
            isLoadingAndEmpty.setValue(
                value.isNullOrEmpty() && status.value == ApiStatus.LOADING
            )
        })

        //changing the boolean isErrorAndEmpty value if  status or list of asteroids changes
        isErrorAndEmpty.addSource(status, Observer<ApiStatus> { value ->
            isErrorAndEmpty.setValue(
                asteroids.value.isNullOrEmpty() && value == ApiStatus.ERROR
            )
        })
        isErrorAndEmpty.addSource(asteroids, Observer<List<Asteroid>> { value ->
            isErrorAndEmpty.setValue(
                value.isNullOrEmpty() && status.value == ApiStatus.ERROR
            )
        })
    }

    fun clickOnItemAsteroid(asteroid : Asteroid) {
        _clickOnAsteroid.value = asteroid
    }

    fun endClickOnItemAsteroid() {
        _clickOnAsteroid.value = null
    }

    private suspend fun getAsteroidsProperties() {
        _status.value = ApiStatus.LOADING
        try {
            asteroidRepository.refreshAsteroids()
            _status.value = ApiStatus.DONE
        } catch (e: Exception) {
            e.printStackTrace()
            _status.value = ApiStatus.ERROR
        }
    }

}