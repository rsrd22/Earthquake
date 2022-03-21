package com.example.earthquaken_monitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquaken_monitor.databinding.EqListItemBinding

class EqAdapter : ListAdapter<Earthquake, EqAdapter.EqViewHolder>(DiffCallback){

    companion object DiffCallback: DiffUtil.ItemCallback<Earthquake>(){
        override fun areItemsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Earthquake, newItem: Earthquake): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EqAdapter.EqViewHolder {
        val binding = EqListItemBinding.inflate(LayoutInflater.from(parent.context))
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.eq_list_item, parent, false)

        return EqViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EqAdapter.EqViewHolder, position: Int) {
        val earthquake = getItem(position)
//        holder.magnitudeText.text = earthquake.magnitude.toString()
//        holder.placeText.text = earthquake.place
        holder.bind(earthquake)
    }

    inner class EqViewHolder(val binding: EqListItemBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(earthquake: Earthquake){
            binding.eqMagnitudeText.text = earthquake.magnitude.toString()
            binding.eqPlaceText.text = earthquake.place
        }

    }

}