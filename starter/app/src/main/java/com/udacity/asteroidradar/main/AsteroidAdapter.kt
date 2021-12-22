package com.udacity.asteroidradar.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidItemBinding

class AsteroidAdapter(private val listener: AsteroidItemListener) :  ListAdapter<Asteroid, RecyclerView.ViewHolder>(SleepNightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.i("zakou", "onCreateViewHolder")
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i("zakou", "onBindViewHolder")
        when (holder) {
            is ViewHolder -> {
                val asteroid = getItem(position)
                holder.bind(listener, asteroid)
            }
        }
    }


    class ViewHolder private constructor(val binding: AsteroidItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: AsteroidItemListener, item: Asteroid) {
            binding.asteroid = item
            binding.listener = listener
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

class AsteroidItemListener(val onItemClick : (asteroid: Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) {
        Log.i("click", "asteroid = ${asteroid.id}")
        onItemClick(asteroid)
    }
}
