package com.alpesh.carsharingapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alpesh.carsharingapp.data.model.Car
import com.alpesh.carsharingapp.databinding.CarListItemBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query

open class CarListAdapter(query: Query?, val onCarSelectedListener: OnCarSelectedListener) :
    FireStoreAdapter<CarListAdapter.ViewHolder>(query) {
    interface OnCarSelectedListener {
        fun onCarSelected(car: DocumentSnapshot)
    }

    class ViewHolder(val binding: CarListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(snapshot: DocumentSnapshot, onCarSelectedListener: OnCarSelectedListener) {
            val carHashMap = snapshot.data ?: return

            val car = Car.toObject(carHashMap)
            binding.car = car;

            binding.root.setOnClickListener {
                onCarSelectedListener.onCarSelected(snapshot)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CarListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), onCarSelectedListener)
    }
}