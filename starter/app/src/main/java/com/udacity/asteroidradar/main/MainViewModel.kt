package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.api.getAsteroids
import kotlinx.coroutines.launch

enum class NasaApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids : LiveData<List<Asteroid>>
        get() = _asteroids

    val _clickOnAsteroid = MutableLiveData<Asteroid>()
    val clickOnAsteroid : LiveData<Asteroid>
        get() = _clickOnAsteroid

    private val _status = MutableLiveData<NasaApiStatus>()
    val status: LiveData<NasaApiStatus>
        get() = _status

    init {
        //MOCKING API
        val a = Asteroid(
            123,
            "Codename",
            "closeApproachDate",
            2.2,
            3.2,
            4.8,
            5.0,
            true
        )
        val b = Asteroid(
            456,
            "Codename 2",
            "closeApproachDate 2",
            6.2,
            7.2,
            8.8,
            9.0,
            false
        )
        Log.i("zakou", "INIT VIEW MODEL")
        val c = ArrayList<Asteroid>()
        c.add(a)
        c.add(b)
        //_asteroids.value = c
        getAsteroidsProperties()
    }

    fun clickOnItemAsteroid(asteroid : Asteroid) {
        _clickOnAsteroid.value = asteroid
    }

    fun endClickOnItemAsteroid() {
        _clickOnAsteroid.value = null
    }

    private fun getAsteroidsProperties() {
        viewModelScope.launch {
            _status.value = NasaApiStatus.LOADING
            try {
                _asteroids.value = getAsteroids()
                _status.value = NasaApiStatus.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = NasaApiStatus.ERROR
                _asteroids.value = ArrayList()
            }
        }
    }
}