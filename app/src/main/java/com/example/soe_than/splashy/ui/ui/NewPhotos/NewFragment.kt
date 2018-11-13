package com.example.soe_than.splashy.ui.ui.NewPhotos


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.NewFragmentBinding
import com.example.soe_than.splashy.ui.adapter.PhotoListAdapter
import com.example.soe_than.splashy.ui.delegate.PhotoDelegate
import com.example.soe_than.splashy.ui.ui.CollectionPhotos.CollectionsViewModel
import com.example.soe_than.splashy.ui.ui.CustomCollection.CollectionPhoto
import com.example.soe_than.splashy.ui.ui.PhotoPreview
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import kotlinx.android.synthetic.main.fragment_new.view.*


class NewFragment : Fragment(), PhotoDelegate {
    override fun onTap(photoUrl: String) {
        var intent = Intent(activity, PhotoPreview::class.java)
        intent.putExtra("URL", photoUrl)

        startActivity(intent)
    }

    lateinit var binding: NewFragmentBinding
    lateinit var newAdapter: PhotoListAdapter

    private val viewModel: NewViewModel by lazy { ViewModelProviders.of(this).get(NewViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate<NewFragmentBinding>(inflater, R.layout.fragment_new, container, false);

        val view = binding.getRoot()


        var isConnected = ConstantsUtils.checkConnectivity(context!!)

        if (isConnected) {
            getConnected(view)
        } else {
            noConnection(view)

        }
        binding.btnRetry.setOnClickListener({
            recheckInternet()
        })



        return view
    }

    fun getConnected(view: View) {


        binding.layoutRetry.visibility = View.GONE
        binding.favouriteRecyclerview.visibility = View.VISIBLE
        binding.photoProgress.visibility = View.VISIBLE



        setUpRecyclerView(view)

    }

    fun noConnection(view: View) {
        binding.favouriteRecyclerview.visibility = View.GONE
        binding.layoutRetry.visibility = View.VISIBLE
        binding.photoProgress.visibility = View.GONE

    }

    private fun setUpRecyclerView(view: View) {
        binding.favouriteRecyclerview.layoutManager = StaggeredGridLayoutManager(2, 1)

        newAdapter = PhotoListAdapter(activity!!, this)

        viewModel.getListLiveData().observe(activity!!, Observer { photos ->
            photos!!.let {
                view.photoProgress.visibility = View.GONE
                newAdapter.submitList(photos)

            }
        })

        viewModel.getNetworkState().observe(activity!!, Observer { networkState -> newAdapter.setNetworkState(networkState!!) })


        binding.favouriteRecyclerview.adapter = newAdapter


    }


    fun recheckInternet() {
        if (ConstantsUtils.checkConnectivity(context!!)) {
            getConnected(view!!)
        } else {
            noConnection(view!!)
        }


    }


}
