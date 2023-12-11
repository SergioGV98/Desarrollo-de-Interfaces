package com.example.repaso.calculadoraimc

sealed class CalculadoraIMCState {

    data object WeightIsMandatory : CalculadoraIMCState()
    data object AgeIsMandatory : CalculadoraIMCState()

}