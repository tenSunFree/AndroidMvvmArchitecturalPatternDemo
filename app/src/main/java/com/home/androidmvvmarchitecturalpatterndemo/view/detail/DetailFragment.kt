package com.home.androidmvvmarchitecturalpatterndemo.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.home.androidmvvmarchitecturalpatterndemo.R
import com.home.androidmvvmarchitecturalpatterndemo.databinding.FragmentDetailBinding
import com.home.androidmvvmarchitecturalpatterndemo.viewmodel.MainViewModel
import com.home.androidmvvmarchitecturalpatterndemo.viewmodel.MainViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class DetailFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: MainViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        val viewModel: MainViewModel =
            ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
