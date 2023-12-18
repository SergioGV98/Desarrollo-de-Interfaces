package com.examen.lotterykotlin.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.examen.lotterykotlin.R
import com.examen.lotterykotlin.data.model.Lottery
import com.examen.lotterykotlin.data.model.TypeLottery
import com.examen.lotterykotlin.databinding.ItemSorteoBinding

class LotteryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemSorteoBinding.bind(itemView)

    fun render(lottery: Lottery) {
        setImage(lottery)
        binding.tvDate.text = lottery.date
        binding.tvLottery.text = "Sorteo ${lottery.idLottery}"
        binding.tvCombination.text = lottery.combination.toString()
        binding.tvComplementary.text = "[${lottery.complementary}]"
        binding.tvReinteger.text = "[${lottery.reinteger}]"

    }

    private fun setImage(lottery: Lottery) {
        return when (lottery.type) {
            TypeLottery.PRIMITIVA -> {
                binding.imgLottery.setImageResource(R.drawable.ic_primitiva)
            }

            TypeLottery.EUROMILLION -> {
                binding.imgLottery.setImageResource(R.drawable.ic_euromillon)
            }

            else -> {
                binding.imgLottery.setImageResource(R.drawable.ic_bonoloto)
            }
        }
    }

}