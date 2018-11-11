package com.example.soe_than.splashy.ui.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.databinding.CollectionItemBinding
import com.example.soe_than.splashy.databinding.NewPhotoItemBinding
import com.example.soe_than.splashy.ui.data.Vo.Collection
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.delegate.CollectionDelegate

class CollectionItemViewHolder(itemView: View, binding: CollectionItemBinding,val mCollectionDelegate: CollectionDelegate) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
    override fun onClick(view: View?) {
        mCollectionDelegate.onTap(mCollection!!)
    }

    var collectionItemBinding: CollectionItemBinding
    var mCollection :Collection? =null
    init {
        collectionItemBinding = binding
    }

    fun bindTo(collection: Collection) {
        mCollection = collection

        val requestOptions = RequestOptions()
        requestOptions.placeholder( ColorDrawable(Color.DKGRAY))
        requestOptions.error(R.drawable.ic_error_triangle)

        Glide.with(itemView.context).setDefaultRequestOptions(requestOptions).load(collection.cover_photo.urls.small).into(collectionItemBinding.collectionImgView)
        collectionItemBinding.collectionTxtView.setText(collection.title)

        collectionItemBinding.collectionImgView.setOnClickListener(this)


    }
}