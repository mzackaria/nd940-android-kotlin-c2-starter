package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment(){

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val adapter = AsteroidAdapter(AsteroidItemListener {
            viewModel.clickOnItemAsteroid(it)
        })

        viewModel.clickOnAsteroid.observe(viewLifecycleOwner, Observer{
            it?.let{
                this.findNavController().navigate(
                    MainFragmentDirections.actionShowDetail(it)
                )
                viewModel.endClickOnItemAsteroid()
            }
        })
        binding.asteroidRecycler.adapter = adapter

        binding.viewModel = viewModel

        viewModel.asteroids.observe(viewLifecycleOwner, Observer{
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}
