package com.udacity.asteroidradar.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.Asteroid

class MainViewModel : ViewModel() {

    val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids : LiveData<List<Asteroid>>
        get() = _asteroids

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
        _asteroids.value = c
    }
}