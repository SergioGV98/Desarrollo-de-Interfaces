package com.example.repaso.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.repaso.R
import com.example.repaso.databinding.ActivityResultImcactivityBinding

class ResultIMCActivity : AppCompatActivity() {

    private var _binding: ActivityResultImcactivityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityResultImcactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI(intent.extras?.getDouble("IMC_RESULT") ?: -1.0)
        Log.i("Resultado", (intent.extras?.getDouble("IMC_RESULT") ?: -1.0).toString())
        binding.btnReCalc.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initUI(result: Double){
        binding.txIMC.text = result.toString()
        when(result){
            in 0.00..18.50 -> { //Peso bajo
                binding.txResult.text = "Peso bajo"
                binding.txDescription.text = "Estas por debajo de lo optimo para tu peso y altura."
            }
            in 18.51..24.99 -> { //Peso normal
                binding.txResult.text = "Normal"
                binding.txDescription.text = "Estas en lo optimo para tu peso y altura."
            }
            in 25.00..29.99 -> { //Peso alto
                binding.txResult.text = "Pesto alto"
                binding.txDescription.text = "Estas por encima de lo optimo para tu peso y altura."
            }
            in 30.00..99.99 -> { //Obesidad
                binding.txResult.text = "Sobrepeso"
                binding.txDescription.text = "Estas MUY por encima de lo optimo para tu peso y altura."
            }

            else -> { //Error
                binding.txResult.text = "error"
                binding.txIMC.text = "error"
                binding.txDescription.text = "error"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}