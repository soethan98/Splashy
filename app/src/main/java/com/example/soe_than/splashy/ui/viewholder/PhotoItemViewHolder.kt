package com.example.soe_than.splashy.ui.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.constraint.ConstraintSet
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
import com.example.soe_than.splashy.ui.delegate.PhotoDelegate
import kotlinx.android.synthetic.main.photo_content.view.*

class PhotoItemViewHolder(itemView: View, binding: NewPhotoItemBinding,val photoUrl:PhotoDelegate) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
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

       Glide.with(itemView.context).setDefaultRequestOptions(requestOptions).load(photo.urls.small).into(photoItemBinding.photoItem)

        photoItemBinding.photoItem.setOnClickListener(this)




    }
}