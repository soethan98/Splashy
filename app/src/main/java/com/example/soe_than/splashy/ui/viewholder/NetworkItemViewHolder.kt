package com.example.soe_than.splashy.ui.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.soe_than.splashy.databinding.NetworkItemBinding
import com.example.soe_than.splashy.databinding.NewPhotoItemBinding
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.utils.NetworkState
import com.example.soe_than.splashy.ui.utils.Status
import kotlinx.android.synthetic.main.photo_content.view.*

class NetworkItemViewHolder(itemView: View,binding: NetworkItemBinding ):RecyclerView.ViewHolder(itemView) {

    lateinit var networkItemBinding: NetworkItemBinding



    init {
        networkItemBinding = binding
    }

    fun bindTo(networkState: NetworkState) {
        if (networkState != null && networkState.string == Status.RUNNING) {
            networkItemBinding.progressBar.setVisibility(View.VISIBLE);
        } else {
            networkItemBinding.progressBar.setVisibility(View.GONE);
        }

        if (networkState != null && networkState.string == Status.FAILED) {
            networkItemBinding.errorMsg.setVisibility(View.VISIBLE);
            networkItemBinding.errorMsg.setText(networkState.msg);
        } else {
            networkItemBinding.errorMsg.setVisibility(View.GONE);
        }

    }
}