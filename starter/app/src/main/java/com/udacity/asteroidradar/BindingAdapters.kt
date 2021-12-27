package com.udacity.asteroidradar

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("iconAsteroid")
fun ImageView.setIconAsteroid(item: Asteroid?){
    this.setImageResource(
        if (item?.isPotentiallyHazardous == true) R.drawable.ic_status_potentially_hazardous
        else R.drawable.ic_status_normal
    )
}

@BindingAdapter("picasso")
fun ImageView.picasso(url: String?){
    url?.let {
        Picasso.with(this.context)
            .load(url)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .centerCrop()
            .into(this)
    }
}

@BindingAdapter("showProgressBar")
fun ProgressBar.showProgressBar(isLoadingAndEmpty: Boolean?){
    isLoadingAndEmpty?.let {
        this.visibility = if (isLoadingAndEmpty) VISIBLE else GONE
    }
}

@BindingAdapter("showBrokenConnection")
fun ImageView.showBrokenConnection(isErrorAndEmpty: Boolean?){
    isErrorAndEmpty?.let {
        this.visibility = if (isErrorAndEmpty) VISIBLE  else GONE
    }
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
