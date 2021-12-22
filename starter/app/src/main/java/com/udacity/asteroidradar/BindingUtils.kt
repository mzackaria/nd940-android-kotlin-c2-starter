package com.udacity.asteroidradar

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("iconAsteroid")
fun ImageView.setIconAsteroid(item: Asteroid?){
    this.setImageResource(
        if (item?.isPotentiallyHazardous == true) R.drawable.ic_status_potentially_hazardous
        else R.drawable.ic_status_normal
    )
}