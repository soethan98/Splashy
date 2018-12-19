package com.example.soe_than.splashy.ui.ui.FeaturedPhotos


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.FeaturedFragmentBinding
import com.example.soe_than.splashy.ui.adapter.PhotoListAdapter
import com.example.soe_than.splashy.ui.delegate.PhotoDelegate
import com.example.soe_than.splashy.ui.ui.PhotoPreview
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import kotlinx.android.synthetic.main.fragment_featured.view.*

class FeaturedFragment : Fragment(),PhotoDelegate {
    override fun onTap(photoUrl: String) {
        var intent = Intent(activity, PhotoPreview::class.java)
        intent.putExtra("URL", photoUrl)

        startActivity(intent)
    }

     lateinit var viewModel: FeaturedViewModel
    lateinit var viewModelFactory:FeaturedViewModelFactory
    lateinit var binding: FeaturedFragmentBinding
    lateinit var newAdapter: PhotoListAdapter
    var loadQual:String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<FeaturedFragmentBinding>(inflater, R.layout.fragment_featured, container, false);

        val view = binding.getRoot()

        viewModelFactory = FeaturedViewModelFactory.provideFeatureViewModelFactory(activity!!)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FeaturedViewModel::class.java)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        loadQual = sharedPreferences.getString(getString(R.string.key_load_quality),"")

        var isConnected = ConstantsUtils.checkConnectivity(context!!)

        if (isConnected) {
            getConnected(view)
        } else {
            noConnection(view)

        }

        binding.featureBtnRetry.setOnClickListener {

            viewModel.checkInternet().observe(activity!!, Observer {progress->
                when(progress){
                    true->noConnection(view)
                    false -> getConnected(view)
                }
            })
        }

        return view
    }

    private fun setUpRecyclerView(view: View) {
        binding.featuredRecyclerview.layoutManager = StaggeredGridLayoutManager(2, 1)

        newAdapter = PhotoListAdapter(activity!!, this, loadQual)

        viewModel.getListLiveData().observe(activity!!, Observer { photos ->
            photos!!.let {
                view.featuredProgress.visibility = View.GONE
                newAdapter.submitList(photos)

            }
        })

        viewModel.getNetworkState().observe(activity!!, Observer { networkState -> newAdapter.setNetworkState(networkState!!) })
        binding.featuredRecyclerview.adapter = newAdapter


    }


    fun getConnected(view: View) {


        binding.featureLayoutRetry.visibility = View.GONE
        binding.featuredRecyclerview.visibility = View.VISIBLE
        binding.featuredProgress.visibility = View.VISIBLE



        setUpRecyclerView(view)

    }

    fun noConnection(view: View) {
        binding.featuredRecyclerview.visibility = View.GONE
        binding.featureLayoutRetry.visibility = View.VISIBLE
        binding.featuredProgress.visibility = View.GONE

    }



}
