package com.example.soe_than.splashy.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
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

       Glide.with(itemView.context).load(photo.urls.thumb).into(photoItemBinding.photoItem)


    }
}