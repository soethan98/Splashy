package com.example.soe_than.splashy.ui.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.request.RequestOptions
import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.R.id.photo_item
import com.example.soe_than.splashy.databinding.NewPhotoItemBinding
import com.example.soe_than.splashy.ui.data.Vo.Photo
import kotlinx.android.synthetic.main.photo_content.view.*

class PhotoItemViewHolder(itemView: View, binding: NewPhotoItemBinding) : RecyclerView.ViewHolder(itemView) {

     var photoItemBinding: NewPhotoItemBinding




    init {
        photoItemBinding = binding
    }

    fun bindTo(photo: Photo) {

        val requestOptions = RequestOptions()
        requestOptions.placeholder( ColorDrawable(Color.DKGRAY))
        requestOptions.error(R.drawable.ic_error_triangle).fitCenter()

       Glide.with(itemView.context).setDefaultRequestOptions(requestOptions).load(photo.urls.small).into(photoItemBinding.photoItem)


    }
}