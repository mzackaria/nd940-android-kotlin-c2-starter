package com.udacity.asteroidradar

fun getIconAsteroid(asteroid: Asteroid) : Int {
    return if (asteroid.isPotentiallyHazardous) R.drawable.ic_status_potentially_hazardous
        else R.drawable.ic_status_normal
}