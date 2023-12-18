package com.examen.lotterykotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examen.lotterykotlin.R
import com.examen.lotterykotlin.repository.LotteryRepository

class LotteryAdapter : RecyclerView.Adapter<LotteryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LotteryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return LotteryViewHolder(layoutInflater.inflate(R.layout.item_sorteo,  parent,false))
    }

    override fun getItemCount(): Int {
        return LotteryRepository.dataSet.size
    }

    override fun onBindViewHolder(holder: LotteryViewHolder, position: Int) {
        holder.render(LotteryRepository.dataSet[position])
    }

}