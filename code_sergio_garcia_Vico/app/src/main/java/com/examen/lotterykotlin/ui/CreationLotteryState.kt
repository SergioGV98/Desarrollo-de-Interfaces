package com.examen.lotterykotlin.ui

sealed class CreationLotteryState {

    data object DateIsMandatory : CreationLotteryState()
    data object DateIsDuplicated : CreationLotteryState()
    data object TypeLotteryIsMandatory : CreationLotteryState()
    data object OnSuccess : CreationLotteryState()

}