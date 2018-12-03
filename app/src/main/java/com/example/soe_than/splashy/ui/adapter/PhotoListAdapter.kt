package com.example.soe_than.splashy.ui.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.soe_than.splashy.databinding.NetworkItemBinding
import com.example.soe_than.splashy.databinding.NewPhotoItemBinding
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.delegate.CollectionDelegate
import com.example.soe_than.splashy.ui.delegate.PhotoDelegate
import com.example.soe_than.splashy.ui.utils.NetworkState
import com.example.soe_than.splashy.ui.viewholder.NetworkItemViewHolder
import com.example.soe_than.splashy.ui.viewholder.PhotoItemViewHolder





class PhotoListAdapter(val context: Context?, val photoDelegate: PhotoDelegate): PagedListAdapter<Photo, RecyclerView.ViewHolder>(PhotoDiffCallback) {


    val TYPE_PROGRESS = 0
     val TYPE_ITEM = 1
    internal var networkState:NetworkState? =null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        if(viewType == TYPE_PROGRESS){
            var headerBinding = NetworkItemBinding.inflate(layoutInflater,parent,false)
           var viewHolder =  NetworkItemViewHolder(headerBinding.root,headerBinding);
            return viewHolder
        }else {
            var itemBinding = NewPhotoItemBinding.inflate(layoutInflater,parent,false)
            var viewHolder =  PhotoItemViewHolder(itemBinding.root,itemBinding,photoDelegate)
            return viewHolder
        }



    }


    private fun hasExtraRow(): Boolean {
        return if (networkState != null && networkState !== NetworkState.LOADED) {
            true
        } else {
            false
        }
    }

//    override fun getItemViewType(position: Int): Int {
//        if (hasExtraRow() && position == getItemCount() - 1) {
//            return TYPE_PROGRESS;
//        } else {
//            return TYPE_ITEM;
//        }
//    }dark
    override fun getItemViewType(position: Int):Int = if(hasExtraRow() && position ==  getItemCount() - 1) TYPE_PROGRESS else TYPE_ITEM




    fun setNetworkState(newNetworkState: NetworkState) {
        var previousState = this.networkState
        val previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState !== newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoItemViewHolder) {
            (holder as PhotoItemViewHolder).bindTo(getItem(position)!!)
        } else {
            (holder as NetworkItemViewHolder).bindTo(networkState!!)
        }
    }



    companion object {
        val PhotoDiffCallback = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem == newItem
            }
        }
    }


}