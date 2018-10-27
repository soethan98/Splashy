package com.example.soe_than.splashy.ui.ui.NewPhotos


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.NewFragmentBinding
import com.example.soe_than.splashy.ui.adapter.NewPhotoListAdapter


class NewFragment : Fragment() {

    private lateinit var viewModel: NewViewModel
    lateinit var binding: NewFragmentBinding
    lateinit var newAdapter: NewPhotoListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_new, container, false)

        binding = DataBindingUtil.inflate<NewFragmentBinding>(inflater, R.layout.fragment_new, container, false);

        val view = binding.getRoot()
        viewModel = ViewModelProviders.of(this).get(NewViewModel::class.java)

        setUpRecyclerView()



        return view
    }

    private fun setUpRecyclerView() {
        binding.favouriteRecyclerview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        newAdapter = NewPhotoListAdapter()

        viewModel.getListLiveData().observe(activity!!, Observer { photos ->
            photos!!.let {
                Log.i("NewFrag", "${photos.size}")
                newAdapter.submitList(photos)

            }
        })

        viewModel.getNetworkState().observe(activity!!, Observer { networkState -> newAdapter.setNetworkState(networkState!!) })


        binding.favouriteRecyclerview.adapter = newAdapter



    }


}
