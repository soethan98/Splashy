package com.example.soe_than.splashy.ui.ui.NewPhotos


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.NewFragmentBinding
import com.example.soe_than.splashy.ui.adapter.PhotoListAdapter
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.delegate.PhotoDelegate
import com.example.soe_than.splashy.ui.ui.activity.photopreview.PhotoPreview
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import kotlinx.android.synthetic.main.fragment_new.view.*
import org.jetbrains.anko.support.v4.startActivity


class NewFragment : Fragment(), PhotoDelegate {
    override fun onTap(photoUrl: String?, photoId: String?) {
        startActivity<PhotoPreview>("PHOTO_URL" to photoUrl,
                "PHOTO_ID" to photoId)

    }

    lateinit var binding: NewFragmentBinding
    lateinit var newAdapter: PhotoListAdapter
    lateinit var viewModelFactory: NewViewModelFactory

    lateinit var viewModel: NewViewModel
    var loadQual: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate<NewFragmentBinding>(inflater, R.layout.fragment_new, container, false);

        val view = binding.getRoot()


        viewModelFactory = NewViewModelFactory.provideNewViewModelFactory(activity!!)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewViewModel::class.java)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        loadQual = sharedPreferences.getString(getString(R.string.key_load_quality), "")


        var isConnected = ConstantsUtils.checkConnectivity(context!!)

        if (isConnected) {
            getConnected(view)
        } else {
            noConnection(view)

        }
        binding.btnRetry.setOnClickListener {

            viewModel.checkInternet().observe(activity!!, Observer { progress ->
                when (progress) {
                    true -> noConnection(view)
                    false -> getConnected(view)
                }
            })
        }



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

        newAdapter = PhotoListAdapter(activity!!, this, loadQual)

        viewModel.getListLiveData().observe(activity!!, Observer { photos ->
            photos!!.let {
                view.photoProgress.visibility = View.GONE
                newAdapter.submitList(photos)

            }
        })

        viewModel.getNetworkState().observe(activity!!, Observer { networkState -> newAdapter.setNetworkState(networkState!!) })


        binding.favouriteRecyclerview.adapter = newAdapter


    }


}
