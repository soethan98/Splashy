package com.example.soe_than.splashy.ui.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.soe_than.splashy.databinding.CollectionItemBinding
import com.example.soe_than.splashy.databinding.NetworkItemBinding
import com.example.soe_than.splashy.databinding.NewPhotoItemBinding
import com.example.soe_than.splashy.ui.data.Vo.Collection
import com.example.soe_than.splashy.ui.data.Vo.Photo
import com.example.soe_than.splashy.ui.utils.NetworkState
import com.example.soe_than.splashy.ui.viewholder.CollectionItemViewHolder
import com.example.soe_than.splashy.ui.viewholder.NetworkItemViewHolder
import com.example.soe_than.splashy.ui.viewholder.PhotoItemViewHolder

class CollectionListAdapter: PagedListAdapter<Collection, RecyclerView.ViewHolder>(CollectionDiffCallback) {


    val TYPE_PROGRESS = 0
    val TYPE_ITEM = 1
    internal var networkState: NetworkState? =null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        if(viewType == TYPE_PROGRESS){
            var headerBinding = NetworkItemBinding.inflate(layoutInflater,parent,false)
            var viewHolder =  NetworkItemViewHolder(headerBinding.root,headerBinding);
            return viewHolder
        }else {
            var itemBinding = CollectionItemBinding.inflate(layoutInflater,parent,false)
            var viewHolder =  CollectionItemViewHolder(itemBinding.root,itemBinding)
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

    override fun getItemViewType(position: Int): Int {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

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
        if (holder is CollectionItemViewHolder) {
            (holder as CollectionItemViewHolder).bindTo(getItem(position)!!)
        } else {
            (holder as NetworkItemViewHolder).bindTo(networkState!!)
        }
    }



    companion object {
        val CollectionDiffCallback = object : DiffUtil.ItemCallback<Collection>() {
            override fun areItemsTheSame(oldItem: Collection, newItem: Collection): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Collection, newItem: Collection): Boolean {
                return oldItem == newItem
            }
        }
    }

}