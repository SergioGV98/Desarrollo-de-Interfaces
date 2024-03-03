package com.example.repasoexamenlourdes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.repasoexamenlourdes.data.Juego
import com.example.repasoexamenlourdes.data.notification.createNotificationChannel
import com.example.repasoexamenlourdes.data.notification.sendNotification
import com.example.repasoexamenlourdes.databinding.FragmentGameCreateBinding
import com.example.repasoexamenlourdes.repository.GameRepositoryDB
import com.example.repasoexamenlourdes.usecase.GameCreationViewModel
import com.google.android.material.snackbar.Snackbar

class GameCreation: Fragment() {

    private var _binding: FragmentGameCreateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GameCreationViewModel by viewModels()
    private val gameRepositoryDB = GameRepositoryDB()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameCreateBinding.inflate(inflater, container, false)
        binding.viewmodelgame = this.viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createNotificationChannel(CHANNEL, requireContext())

        viewModel.getState().observe(viewLifecycleOwner){
            when(it){
                GameCreationState.NameIsMandatory -> setGameNameEmptyError()
                else -> onSuccessCreate()
            }
        }

    }

    private fun onSuccessCreate() {
        var id = gameRepositoryDB.getNextGameId()
        val juego = Juego(id.toInt(), binding.tietNameGame.text.toString())
        viewModel.insert(juego)
        sendNotification(CHANNEL, requireContext(), "Juego creado", "Se ha creado el juego exitosamente")
        findNavController().popBackStack()
    }


    private fun setGameNameEmptyError() {
        Snackbar.make(requireView(), "El nombre no puede estar vacio", Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private const val CHANNEL = "GAME_CHANNEL"
    }
}