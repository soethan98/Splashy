package com.example.soe_than.splashy.ui.ui.PhotoDetail


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.DetailFragmentBinding
import com.example.soe_than.splashy.databinding.NewFragmentBinding
import com.example.soe_than.splashy.ui.data.Vo.PhotoDetail
import com.example.soe_than.splashy.ui.ui.NewPhotos.NewViewModel
import com.example.soe_than.splashy.ui.ui.NewPhotos.NewViewModelFactory


class DetailFragment : BottomSheetDialogFragment() {

    private var photoId: String = ""
    lateinit var viewModelFactory: DetailViewModelFactory

    lateinit var viewModel: DetailViewModel
    lateinit var binding: DetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<DetailFragmentBinding>(inflater, R.layout.fragment_detail, container, false)

        val view = binding.getRoot()

        val bundle = this.arguments
        if (bundle != null) {
            photoId = bundle.getString("photo_id")
        }

        viewModelFactory = DetailViewModelFactory.provideDetailViewModelFactory(activity!!, photoId)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        viewModel.getPhotoDetails().observe(activity!!, Observer {
            bindPhotoDetails(it!!,view)
        })

        return view
    }

    private fun bindPhotoDetails(photoDetail: PhotoDetail,view:View) {
        binding.txtColor.setText(photoDetail.color)
        binding.txtAperture.setText(photoDetail.exif.aperture)
        binding.txtDownloads.setText("${photoDetail.downloads}")
        binding.txtExposure.setText(photoDetail.exif.exposure_time)
        binding.txtFocal.setText(photoDetail.exif.focal_length)
        binding.txtIso.setText("${photoDetail.exif.iso}")
        binding.txtLikes.setText("${photoDetail.likes}")
        binding.txtMake.setText(photoDetail.exif.make)
        binding.txtModel.setText(photoDetail.exif.model)
        binding.profileName.setText(photoDetail.user.name)
     Glide.with(view).load(photoDetail.user.profile_image.medium).apply(RequestOptions().circleCrop()).into(binding.profileImgThumb)

    }


}
