package com.firdausam.mobile.myweather


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.firdausam.mobile.myweather.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnShowWeather.setOnClickListener {
            onShowWeather()
        }
    }

    private fun onShowWeather() {
        val name = binding.etName.text
        val postalCode = binding.etPostalCode.text

        if (name.isNullOrEmpty()) {
            binding.etName.error = getString(R.string.name_empty)
            return
        }

        if (postalCode.isNullOrEmpty()) {
            binding.etPostalCode.error = getString(R.string.postal_code_empty)
            return
        }

        if (postalCode.length != 5) {
            binding.etPostalCode.error = getString(R.string.postal_code_must_five_digits)
            return
        }

        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToWeatherFragment(
                name.toString(),
                postalCode.toString().toInt()
            )
        )
    }

}
