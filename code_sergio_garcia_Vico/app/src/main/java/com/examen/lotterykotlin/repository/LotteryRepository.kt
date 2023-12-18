package com.examen.lotterykotlin.repository

import com.examen.lotterykotlin.data.model.Lottery
import com.examen.lotterykotlin.data.model.TypeLottery
import com.examen.lotterykotlin.utils.NumeroAleatorio

class LotteryRepository {

    companion object{

        var dataSet = mutableListOf<Lottery>()

        init {
            initDataSet()
        }

        private fun initDataSet(){
            //dataSet.add(Lottery(1, "(14/01/2018)", NumeroAleatorio.getListaNumerosAleatorio(1, 49, 6)!!, NumeroAleatorio.numeroAleatorio(1, 49), NumeroAleatorio.numeroAleatorio(0, 9), TypeLottery.BONOLOTO))
        }

    }
}