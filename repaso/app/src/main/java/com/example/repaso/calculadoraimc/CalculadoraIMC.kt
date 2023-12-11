package com.example.repaso.calculadoraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.repaso.R
import com.example.repaso.calculadoraimc.usecase.CalculadoraIMCModel
import com.example.repaso.databinding.ActivityCalculadoraImcBinding
import java.text.DecimalFormat

class CalculadoraIMC : AppCompatActivity() {

    private var _binding: ActivityCalculadoraImcBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CalculadoraIMCModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityCalculadoraImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeGender()
        changeHeight()
        binding.btnCalc.setOnClickListener {
            val intent = Intent(this, ResultIMCActivity::class.java)
            intent.putExtra("IMC_RESULT", calculateIMC())
            startActivity(intent)
        }
    }

    private fun changeGender() {
        binding.viewMale.setOnClickListener {
            if (!binding.viewMale.isSelected) {
                binding.viewMale.setCardBackgroundColor(getColor(R.color.background_component_selected))
                binding.viewFemale.setCardBackgroundColor(getColor(R.color.background_component))
                binding.viewMale.isSelected = true
                binding.viewFemale.isSelected = false
            }
        }

        binding.viewFemale.setOnClickListener {
            if (!binding.viewFemale.isSelected) {
                binding.viewFemale.setCardBackgroundColor(getColor(R.color.background_component_selected))
                binding.viewMale.setCardBackgroundColor(getColor(R.color.background_component))
                binding.viewFemale.isSelected = true
                binding.viewMale.isSelected = false
            }
        }
    }

    private fun changeHeight() {
        binding.rsHeight.addOnChangeListener { _, value, _ ->
            binding.tvHeight.text = "${value.toInt()} cm"
        }
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val weight = binding.etWeight.text.toString().toDouble()
        val height = binding.tvHeight.text.toString().substring(0, binding.tvHeight.text.toString().indexOf(" ")).toDouble()
        val imc = weight / (height * height)
        return df.format(imc).toDouble()
    }


    private fun setWeightEmptyError() {
        binding.errorMessage.text = "El campo del peso no puede estar vacio"
        binding.errorMessage.requestFocus()
    }

    private fun setAgeEmptyError() {
        binding.errorMessage.text = "El campo de la edad no puede estar vacio"
        binding.errorMessage.requestFocus()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}