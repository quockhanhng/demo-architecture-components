package com.khanhnq.demo_architecture_components.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.khanhnq.demo_architecture_components.R
import com.khanhnq.demo_architecture_components.model.LaunchItem
import kotlinx.android.synthetic.main.item_launch.view.*

class LaunchesAdapter : ListAdapter<LaunchItem, LaunchesAdapter.ViewHolder>(
    LaunchDiffCallback()
) {
    var onItemClick: (String) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_launch, parent, false)
        return ViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ViewHolder(itemView: View, onItemClick: (String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private var itemData: LaunchItem? = null

        init {
            itemView.setOnClickListener {
                itemData?.let { onItemClick(it.id) }
            }
        }

        fun bindData(launch: LaunchItem) {
            itemData = launch
            itemView.run {
                textName.text = launch.name
                textDetails.text = launch.details
            }
        }
    }

    class LaunchDiffCallback : DiffUtil.ItemCallback<LaunchItem>() {
        override fun areItemsTheSame(oldItem: LaunchItem, newItem: LaunchItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LaunchItem, newItem: LaunchItem) =
            oldItem == newItem
    }
}
