package com.mto.invoicecreation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moronlu18.invoicecreation.R
import com.mto.invoicecreation.data.Item

class ItemAdapter(private val itemList: List<Item>, private val onClickListener: (Item) -> Unit): RecyclerView.Adapter<ItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(layoutInflater.inflate(R.layout.item_item, parent, false))
    }
    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.render(item, onClickListener)
    }

}