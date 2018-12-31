package com.example.soe_than.splashy.ui.ui.CustomCollection

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.support.v7.preference.PreferenceManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.ActivityCollectionBinding
import com.example.soe_than.splashy.ui.adapter.PhotoListAdapter
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.delegate.PhotoDelegate
import com.example.soe_than.splashy.ui.ui.activity.photopreview.PhotoPreview
import com.example.soe_than.splashy.ui.utils.ConstantsUtils
import org.jetbrains.anko.startActivity

class CollectionPhoto : AppCompatActivity(), PhotoDelegate {
    override fun onTap(photoUrl: String?, photoId: String?) {
       startActivity<PhotoPreview>(
               "PHOTO_URL" to photoUrl,
               "PHOTO_ID" to photoId
       )
    }


    var collectionId: String? = null;
    var collectionTitle: String? = null
    lateinit var binding: ActivityCollectionBinding
    private lateinit var viewModel: CustomCollectionViewModel
    private lateinit var viewModelFactory: CustomCollectionViewModelFactory
    lateinit var newAdapter: PhotoListAdapter
    var loadQual: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_collection_photo)
        Log.i("HIThe", AppCompatDelegate.getDefaultNightMode().toString());


        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        collectionId = intent.getStringExtra("ID")
        collectionTitle = intent.getStringExtra("TITLE")
        setTitle(collectionTitle)

        viewModelFactory = CustomCollectionViewModelFactory.provideCollectionViewModelFactory(collectionId!!)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CustomCollectionViewModel::class.java)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        loadQual = sharedPreferences.getString(getString(R.string.key_load_quality), "")

        var isConnected = ConstantsUtils.checkConnectivity(this)

        if (isConnected) {
            getConnected()
        } else {
            noConnection()

        }
        binding.cBtnRetry.setOnClickListener({
            recheckInternet()
        })


    }

    private fun setUpRecyclerView() {
        binding.cRecyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        newAdapter = PhotoListAdapter(this, this, loadQual)

        viewModel.getListLiveData().observe(this, Observer { photos ->
            photos!!.let {
                binding.cProgress.visibility = View.GONE
                Log.i("CollectionPhoto", "${photos.size}")
                newAdapter.submitList(photos)

            }
        })
        binding.cRecyclerview.adapter = newAdapter


    }

    fun recheckInternet() {
        if (ConstantsUtils.checkConnectivity(this)) {
            getConnected()
        } else {
            noConnection()
        }
    }

    fun getConnected() {
        binding.cLayoutRetry.visibility = View.GONE
        binding.cRecyclerview.visibility = View.VISIBLE
        binding.cProgress.visibility = View.VISIBLE
        setUpRecyclerView()

    }

    fun noConnection() {
        binding.cRecyclerview.visibility = View.GONE
        binding.cLayoutRetry.visibility = View.VISIBLE
        binding.cProgress.visibility = View.GONE

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun restartActivity() {

        val mIntent = intent
        finish()
        startActivity(mIntent)
    }

}
