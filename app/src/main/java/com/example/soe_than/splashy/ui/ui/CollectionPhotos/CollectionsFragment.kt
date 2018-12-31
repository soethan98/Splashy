package com.example.soe_than.splashy.ui.ui.CollectionPhotos


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.CollectionFragmentBinding
import com.example.soe_than.splashy.ui.adapter.CollectionListAdapter
import com.example.soe_than.splashy.ui.data.Vo.Collection
import com.example.soe_than.splashy.ui.delegate.CollectionDelegate
import com.example.soe_than.splashy.ui.delegate.PhotoDelegate
import com.example.soe_than.splashy.ui.ui.CustomCollection.CollectionPhoto
import com.example.soe_than.splashy.ui.ui.NewPhotos.NewViewModel
import com.example.soe_than.splashy.ui.ui.NewPhotos.NewViewModelFactory
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import kotlinx.android.synthetic.main.fragment_collections.view.*

class CollectionsFragment : Fragment(), CollectionDelegate {


    lateinit var viewModel: CollectionsViewModel
    lateinit var viewModelFactory: CollectionsViewModelFactory
    lateinit var binding: CollectionFragmentBinding
    lateinit var collectionListAdapter: CollectionListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate<CollectionFragmentBinding>(inflater, R.layout.fragment_collections, container, false);

        val view = binding.getRoot()
        viewModelFactory = CollectionsViewModelFactory.provideCollectionsViewModelFactory(activity!!)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CollectionsViewModel::class.java)

        var isConnected = ConstantsUtils.checkConnectivity(context!!)

        if (isConnected) {
            getConnected(view)
        } else {
            noConnection(view)

        }
        binding.collectionBtnRetry.setOnClickListener {

            viewModel.checkInternet().observe(activity!!, Observer { progress ->
                when (progress) {
                    true -> noConnection(view)
                    false -> getConnected(view)
                }
            })
        }
        return view

    }

    private fun setUpRecyclerView(view: View) {
        binding.collectionRecyclerview.layoutManager = LinearLayoutManager(activity)

        collectionListAdapter = CollectionListAdapter(context!!, this)

        viewModel.getListLiveData().observe(activity!!, Observer { collections ->
            collections!!.let {
                view.collectionProgress.visibility = View.GONE
                collectionListAdapter.submitList(collections)

            }
        })

        viewModel.getNetworkState().observe(activity!!, Observer { networkState -> collectionListAdapter.setNetworkState(networkState!!) })


        binding.collectionRecyclerview.adapter = collectionListAdapter
    }

    override fun onTap(collection: Collection) {
        var intent = Intent(activity, CollectionPhoto::class.java)
        intent.putExtra("ID", collection.id)
        intent.putExtra("TITLE", collection.title)

        startActivity(intent)
    }

    fun getConnected(view: View) {
        binding.collectionLayoutRetry.visibility = View.GONE
        binding.collectionRecyclerview.visibility = View.VISIBLE
        binding.collectionProgress.visibility = View.VISIBLE
        setUpRecyclerView(view)
    }

    fun noConnection(view: View) {
        binding.collectionRecyclerview.visibility = View.GONE
        binding.collectionLayoutRetry.visibility = View.VISIBLE
        binding.collectionProgress.visibility = View.GONE
    }


}
