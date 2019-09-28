package com.home.androidmvvmarchitecturalpatterndemo.view.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.home.androidmvvmarchitecturalpatterndemo.R
import com.home.androidmvvmarchitecturalpatterndemo.databinding.FragmentMainBinding
import com.home.androidmvvmarchitecturalpatterndemo.model.network.responses.MainCulturalAssetsResponse
import com.home.androidmvvmarchitecturalpatterndemo.utils.hide
import com.home.androidmvvmarchitecturalpatterndemo.utils.show
import com.home.androidmvvmarchitecturalpatterndemo.utils.snackbar
import com.home.androidmvvmarchitecturalpatterndemo.viewmodel.MainViewModel
import com.home.androidmvvmarchitecturalpatterndemo.viewmodel.MainViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class MainFragment : Fragment(), KodeinAware,
    MainListener {

    override val kodein by kodein()
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private val factory: MainViewModelFactory by instance()
    private var isAttach: Boolean = false

    /**
     * 使用Navigation時點擊返回鍵後會重新執行onCreateView, 但是onAttach onCreate不會
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_main,
            null,
            false
        )
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.mainListener = this
        binding.lifecycleOwner = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 只有第一次初始化時, 才會請求資料
        if (!isAttach) {
            isAttach = true
            val limit = 10
            viewModel.getMainCulturalAssetsResponse(limit)
        }
    }

    private fun initializeRecyclerView(mainBindableItem: List<MainBindableItem>) {
        val mAdapter =
            GroupAdapter<ViewHolder>().apply {
                setOnItemClickListener { item, _ ->
                    viewModel.repository.currentPosition.postValue(getAdapterPosition(item))
                    toDetailFragment()
                }
                addAll(mainBindableItem)
            }
        recycler_view.apply {
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<MainCulturalAssetsResponse.Result.Results>.toMainBindableItem():
            List<MainBindableItem> {
        return this.map { MainBindableItem(it) }
    }

    private fun toDetailFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.fragment_detail)
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(results: List<MainCulturalAssetsResponse.Result.Results>) {
        progress_bar.hide()
        initializeRecyclerView(results.toMainBindableItem())
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        frame_layout_root.snackbar(message)
    }
}
