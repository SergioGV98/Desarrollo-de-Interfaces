package com.examen.lotterykotlin.ui.usecase

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.examen.lotterykotlin.data.model.Lottery
import com.examen.lotterykotlin.data.model.TypeLottery
import com.examen.lotterykotlin.repository.LotteryRepository
import com.examen.lotterykotlin.ui.CreationLotteryState

class CreationLotteryViewModel : ViewModel() {

    private val state = MutableLiveData<CreationLotteryState>()
    val dateText = MutableLiveData<String>()
    val lotteryType = MutableLiveData<Int>()

    fun validateLottery(){
        when{
            TextUtils.isEmpty(dateText.value) -> state.value = CreationLotteryState.DateIsMandatory
            validateDate() -> state.value = CreationLotteryState.DateIsDuplicated
           validateTypeLottery() -> state.value = CreationLotteryState.TypeLotteryIsMandatory
            else -> {
                state.value = CreationLotteryState.OnSuccess
            }
        }
    }

    fun createLottery(lottery: Lottery){
        LotteryRepository.dataSet.add(lottery)
    }

    fun getLotteryId(): Int {
        for (lottery in LotteryRepository.dataSet) {
            if (lotteryType.value == 0) {
                var i = 0
                for (il in LotteryRepository.dataSet) {
                    if (il.type == TypeLottery.PRIMITIVA) {
                        i++
                    }
                }
                return i + 1
            }
            if (lotteryType.value == 1) {
                var i = 0
                for (il in LotteryRepository.dataSet) {
                    if (il.type == TypeLottery.BONOLOTO) {
                        i++
                    }
                }
                return i + 1
            }
            if (lotteryType.value == 2) {
                var i = 0
                for (il in LotteryRepository.dataSet) {
                    if (il.type == TypeLottery.EUROMILLION) {
                        i++
                    }
                }
                return i + 1
            }
        }

        return LotteryRepository.dataSet.size + 1
    }

    private fun validateTypeLottery(): Boolean {
        if(lotteryType.value!! < 0 || lotteryType.value!! > 3 ){
            return true
        }
        return false
    }

    private fun validateDate(): Boolean{
        for (dates in LotteryRepository.dataSet){
            if(dates.date.contains(dateText.value!!)){
                dateText.value = ""
                return true
            }
        }
        return false
    }

    fun getState(): MutableLiveData<CreationLotteryState> {
        return state
    }

}