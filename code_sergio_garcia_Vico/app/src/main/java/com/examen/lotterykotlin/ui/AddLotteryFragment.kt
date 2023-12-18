package com.examen.lotterykotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.examen.lotterykotlin.R
import com.examen.lotterykotlin.data.model.Lottery
import com.examen.lotterykotlin.data.model.TypeLottery
import com.examen.lotterykotlin.databinding.FragmentAddLotteryBinding
import com.examen.lotterykotlin.ui.usecase.CreationLotteryViewModel
import com.examen.lotterykotlin.utils.NumeroAleatorio
import com.google.android.material.snackbar.Snackbar

/**
 * Clase AddLotteryFragment
 *
 * @author Sergio Garcia Vico | 26524624N
 */
class AddLotteryFragment : Fragment() {

    private var _binding: FragmentAddLotteryBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: CreationLotteryViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddLotteryBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner){
            when(it){
                CreationLotteryState.DateIsMandatory -> setDateIsMandatoryError()
                CreationLotteryState.TypeLotteryIsMandatory -> setLotteryTypeIsMandatoryError()
                CreationLotteryState.DateIsDuplicated -> setDateDuplicatedError()
                else -> {
                    onSuccess()
                }
            }
        }

    }

    private fun onSuccess(){
        val date = binding.etDate.text.toString()
        when (selectedTypeLottery()) {
            TypeLottery.PRIMITIVA -> {
                val lottery = Lottery(viewModel.getLotteryId(), date,
                    NumeroAleatorio.getListaNumerosAleatorio(1, 49, 6)!!, NumeroAleatorio.numeroAleatorio(1, 49), NumeroAleatorio.numeroAleatorio(0, 9), TypeLottery.PRIMITIVA)
                viewModel.createLottery(lottery)
            }
            TypeLottery.EUROMILLION -> {
                val lottery = Lottery(viewModel.getLotteryId(), date,
                    NumeroAleatorio.getListaNumerosAleatorio(1, 50, 5)!!, NumeroAleatorio.numeroAleatorio(1, 12), NumeroAleatorio.numeroAleatorio(1, 12), TypeLottery.EUROMILLION)
                viewModel.createLottery(lottery)
            }
            else -> {
                val lottery = Lottery(viewModel.getLotteryId(), date,
                    NumeroAleatorio.getListaNumerosAleatorio(1, 49, 6)!!, NumeroAleatorio.numeroAleatorio(1, 49), NumeroAleatorio.numeroAleatorio(0, 9), TypeLottery.BONOLOTO)
                viewModel.createLottery(lottery)
            }
        }
        Snackbar.make(requireView(), getString(R.string.lottery_create_success), Snackbar.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_addLotteryFragment_to_listLotteryFragment)

    }

    private fun selectedTypeLottery(): TypeLottery{
        return when(binding.spType.selectedItemId){
            0L -> TypeLottery.PRIMITIVA
            1L -> TypeLottery.BONOLOTO
            2L -> TypeLottery.EUROMILLION
            else -> {
                return TypeLottery.PRIMITIVA
            }
        }
    }

    private fun setDateIsMandatoryError(){
        Snackbar.make(requireView(), getString(R.string.date_error), Snackbar.LENGTH_LONG).show()
    }

    private fun setLotteryTypeIsMandatoryError(){
        Snackbar.make(requireView(), getString(R.string.type_error), Snackbar.LENGTH_LONG).show()
    }

    private fun setDateDuplicatedError(){
        Snackbar.make(requireView(), getString(R.string.date_duplicated_error), Snackbar.LENGTH_LONG).show()
    }


}