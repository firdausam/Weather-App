package com.firdausam.mobile.myweather


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firdausam.mobile.myweather.data.models.IWeatherItem
import com.firdausam.mobile.myweather.databinding.FragmentWeatherBinding
import com.firdausam.mobile.myweather.viewmodels.WeatherFactory
import com.firdausam.mobile.myweather.viewmodels.WeatherViewModel


class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private var name: String? = null
    private var postalCode: Int = 0
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        name = arguments?.getString("name")?: "Anonim"
        postalCode = arguments?.getInt("postalCode") ?: 0

        viewModel = ViewModelProviders.of(this, WeatherFactory.newInstance())[WeatherViewModel::class.java]

        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvHello.text = getString(R.string.welcome_user, "Sore", name)
        binding.tvTestPostal.text = getString(R.string.postal_code_show, postalCode.toString())

        binding.rvNowWeather.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSoonWeather.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val dividerItemDecoration = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        )
        binding.rvSoonWeather.addItemDecoration(dividerItemDecoration)

        val dataNow = ArrayList<IWeatherItem>()
        val dataSoon = ArrayList<IWeatherItem>()

        val adapterNow= AdapterDataNow()
        adapterNow.data = dataNow

        val adapterSoon = AdapterDataSoon()
        adapterSoon.data = dataSoon

        binding.rvNowWeather.adapter = adapterNow
        binding.rvSoonWeather.adapter = adapterSoon

        viewModel.resultLiveDataNow.observe(viewLifecycleOwner, Observer {
            Log.d("test", "test: $it")
            dataNow.clear()
            dataNow.addAll(it)
            adapterNow.notifyDataSetChanged()
            binding.item = it[0]
        })

        viewModel.resultLiveDataSoon.observe(viewLifecycleOwner, Observer {
            Log.d("test", "test: $it")
            dataSoon.clear()
            dataSoon.addAll(it)
            adapterSoon.notifyDataSetChanged()
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Log.w("test", "error: $it")
        })

        viewModel.getData(postalCode)
    }


}
