package com.examen.lotterykotlin.ui.usecase


import androidx.lifecycle.ViewModel
import com.examen.lotterykotlin.repository.LotteryRepository

/**
 * Clase  ViewModel
 *
 * @author Sergio Garcia Vico | 26524624N
 */
class ListLotteryViewModel: ViewModel() {

    fun getRepository(): Int {
        return LotteryRepository.dataSet.size
    }

}