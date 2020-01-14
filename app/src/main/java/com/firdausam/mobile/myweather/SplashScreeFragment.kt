package com.firdausam.mobile.myweather

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.firdausam.mobile.myweather.databinding.FragmentSplashScreenBinding

class SplashScreeFragment: Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setDelay()
    }

    private fun setDelay() {
        Handler().postDelayed({
            context?.let {
                gotoStart()
            }
        }, 2500)
    }

    private fun gotoStart() {
        findNavController().navigate(SplashScreeFragmentDirections.actionSplashScreeFragmentToHomeFragment())
    }
}