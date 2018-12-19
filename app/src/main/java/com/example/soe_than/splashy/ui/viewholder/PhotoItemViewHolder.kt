package com.example.soe_than.splashy.ui.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.NewPhotoItemBinding
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.delegate.PhotoDelegate

class PhotoItemViewHolder(itemView: View, binding: NewPhotoItemBinding, val photoUrl: PhotoDelegate, val loadQuality:String? ) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
    override fun onClick(p0: View?) {
        photoUrl.onTap(p!!.urls.regular)
    }

    var photoItemBinding: NewPhotoItemBinding
    var p:Photo? = null



    init {
        photoItemBinding = binding
    }

    fun bindTo(photo: Photo) {

        p = photo
        val requestOptions = RequestOptions()
        requestOptions.placeholder( ColorDrawable(Color.DKGRAY))
        requestOptions.error(R.drawable.ic_error_triangle).fitCenter()

        Log.i("PhotoViewHolder",loadQuality)

        when(loadQuality){
            "0"->Glide.with(itemView.context).setDefaultRequestOptions(requestOptions).load(photo.urls.raw).into(photoItemBinding.photoItem)
            "1"->Glide.with(itemView.context).setDefaultRequestOptions(requestOptions).load(photo.urls.thumb).into(photoItemBinding.photoItem)
            "2"->Glide.with(itemView.context).setDefaultRequestOptions(requestOptions).load(photo.urls.small).into(photoItemBinding.photoItem)
            "3"->Glide.with(itemView.context).setDefaultRequestOptions(requestOptions).load(photo.urls.regular).into(photoItemBinding.photoItem)
            "4"->Glide.with(itemView.context).setDefaultRequestOptions(requestOptions).load(photo.urls.full).into(photoItemBinding.photoItem)
        }

       Glide.with(itemView.context).setDefaultRequestOptions(requestOptions).load(photo.urls.small).into(photoItemBinding.photoItem)

        photoItemBinding.photoItem.setOnClickListener(this)




    }
}