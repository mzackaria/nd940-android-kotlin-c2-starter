package com.udacity.asteroidradar.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidItemBinding
import com.udacity.asteroidradar.getIconAsteroid

class AsteroidAdapter :  ListAdapter<Asteroid, RecyclerView.ViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.i("zakou", "onCreateViewHolder")
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i("zakou", "onBindViewHolder")
        when (holder) {
            is ViewHolder -> {
                val asteroid = getItem(position)
                holder.bind(asteroid)
            }
        }
    }


    class ViewHolder private constructor(val binding: AsteroidItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Asteroid) {
            binding.asteroid = item
            binding.asteroidIcon.setImageResource(getIconAsteroid(item))
            //binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AsteroidItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class SleepNightDiffCallback : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem

    }
}
