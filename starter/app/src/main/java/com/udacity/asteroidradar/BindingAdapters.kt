package com.udacity.asteroidradar

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.udacity.asteroidradar.api.ApiStatus

@BindingAdapter("iconAsteroid")
fun ImageView.setIconAsteroid(item: Asteroid?){
    this.setImageResource(
        if (item?.isPotentiallyHazardous == true) R.drawable.ic_status_potentially_hazardous
        else R.drawable.ic_status_normal
    )
}

@BindingAdapter("showProgressBar")
fun ProgressBar.showProgressBar(status: ApiStatus?){
    this.visibility = if (status == ApiStatus.DONE || status == ApiStatus.ERROR) GONE
    else VISIBLE
}

@BindingAdapter("showBrokenConnection")
fun ImageView.showBrokenConnection(status: ApiStatus?){
    this.visibility = if (status == ApiStatus.ERROR) VISIBLE
    else GONE
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}
