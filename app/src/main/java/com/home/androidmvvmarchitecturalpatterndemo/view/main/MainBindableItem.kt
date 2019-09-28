package com.home.androidmvvmarchitecturalpatterndemo.view.main

import android.view.View
import com.home.androidmvvmarchitecturalpatterndemo.R
import com.home.androidmvvmarchitecturalpatterndemo.databinding.FragmentMainItemBinding
import com.home.androidmvvmarchitecturalpatterndemo.model.network.responses.MainCulturalAssetsResponse
import com.xwray.groupie.databinding.BindableItem

class MainBindableItem(val results: MainCulturalAssetsResponse.Result.Results) :
    BindableItem<FragmentMainItemBinding>() {

    override fun getLayout() = R.layout.fragment_main_item

    override fun bind(viewBinding: FragmentMainItemBinding, position: Int) {
        viewBinding.results = results
        val first = 0
        if (position == first) {
            viewBinding.viewFirst.visibility = View.VISIBLE
        } else {
            viewBinding.viewFirst.visibility = View.GONE
        }
    }
}