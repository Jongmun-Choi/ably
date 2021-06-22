package com.test.ably.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.ably.R
import com.test.ably.databinding.ItemGoodsBinding
import com.test.ably.model.Goods

class GoodsAdapter(val onClickListener : View.OnClickListener) : ListAdapter<Goods, GoodsAdapter.GoodsViewHolder>(Companion) {

    class GoodsViewHolder(val binding : ItemGoodsBinding) : RecyclerView.ViewHolder(binding.root)

    companion object: DiffUtil.ItemCallback<Goods>() {
        override fun areItemsTheSame(oldItem: Goods, newItem: Goods): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Goods, newItem: Goods): Boolean {
            return oldItem.equals(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGoodsBinding.inflate(layoutInflater)

        return GoodsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoodsViewHolder, position: Int) {
        val currentGoods = getItem(position)
        holder.binding.item = currentGoods
        holder.binding.ivLike.setOnClickListener(onClickListener)
        holder.binding.executePendingBindings()
    }

    fun replaceList(goodsList : List<Goods>) {
        submitList(goodsList)
    }

}