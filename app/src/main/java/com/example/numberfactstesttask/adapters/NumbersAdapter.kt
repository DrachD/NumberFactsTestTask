package com.example.numberfactstesttask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.model.NumberFactsData
import com.example.numberfactstesttask.databinding.ItemListFactsAboutNumberBinding

class NumbersAdapter(
    private val callback: (Int) -> Unit
) : RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>() {

    class NumberViewHolder(val binding: ItemListFactsAboutNumberBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<NumberFactsData>() {
        override fun areItemsTheSame(
            oldItem: NumberFactsData,
            newItem: NumberFactsData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NumberFactsData,
            newItem: NumberFactsData
        ): Boolean {
            return oldItem == newItem
        }
    }

    val diffAsync = AsyncListDiffer<NumberFactsData>(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        return NumberViewHolder(
            ItemListFactsAboutNumberBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val item = diffAsync.currentList[position]

        holder.binding.apply {
            factNumberTextView.text = item.number.toString()
            factTextView.text = item.fact
            constraintLayout.setOnClickListener {
                callback.invoke(item.number)
            }
        }
    }

    override fun getItemCount(): Int = diffAsync.currentList.size
}