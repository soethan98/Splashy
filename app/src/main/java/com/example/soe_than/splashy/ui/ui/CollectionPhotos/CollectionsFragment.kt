package com.example.soe_than.splashy.ui.ui.CollectionPhotos


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.CollectionFragmentBinding
import com.example.soe_than.splashy.databinding.NewFragmentBinding
import com.example.soe_than.splashy.ui.adapter.CollectionListAdapter
import com.example.soe_than.splashy.ui.adapter.NewPhotoListAdapter
import com.example.soe_than.splashy.ui.ui.NewPhotos.NewViewModel
import kotlinx.android.synthetic.main.fragment_collections.view.*
import kotlinx.android.synthetic.main.fragment_new.view.*

class CollectionsFragment : Fragment() {

    private lateinit var viewModel: CollectionsViewModel
    lateinit var binding: CollectionFragmentBinding
    lateinit var collectionListAdapter: CollectionListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate<CollectionFragmentBinding>(inflater, R.layout.fragment_collections, container, false);

        val view = binding.getRoot()
        viewModel = ViewModelProviders.of(this).get(CollectionsViewModel::class.java)
        setUpRecyclerView(view)

        return view


    }

    private fun setUpRecyclerView(view: View) {
        binding.collectionRecyclerview.layoutManager = LinearLayoutManager(activity)

        collectionListAdapter = CollectionListAdapter()

        viewModel.getListLiveData().observe(activity!!, Observer { collections ->
            collections!!.let {
                view.collectionProgress.visibility = View.GONE
                collectionListAdapter.submitList(collections)

            }
        })

        viewModel.getNetworkState().observe(activity!!, Observer { networkState -> collectionListAdapter.setNetworkState(networkState!!) })


        binding.collectionRecyclerview.adapter = collectionListAdapter



    }


}
