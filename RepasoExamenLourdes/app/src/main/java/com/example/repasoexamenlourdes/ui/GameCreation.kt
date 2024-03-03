package com.example.repasoexamenlourdes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.repasoexamenlourdes.Locator
import com.example.repasoexamenlourdes.data.Genero
import com.example.repasoexamenlourdes.data.Juego
import com.example.repasoexamenlourdes.data.notification.createNotificationChannel
import com.example.repasoexamenlourdes.data.notification.sendNotification
import com.example.repasoexamenlourdes.databinding.FragmentGameCreateBinding
import com.example.repasoexamenlourdes.repository.GameRepositoryDB
import com.example.repasoexamenlourdes.usecase.GameCreationViewModel
import com.google.android.material.snackbar.Snackbar
import com.moronlu18.invoice.ui.preferences.DataStorePreferencesRepository

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
                GameCreationState.NameIsToLong -> setGameNameEmptyError()
                else -> onSuccessCreate()
            }
        }

    }

    private fun onSuccessCreate() {
        val id = gameRepositoryDB.getNextGameId()
        val juego = Juego(id.toInt(), binding.tietNameGame.text.toString(), spinnerGenreChoose())
        viewModel.insert(juego)
        Locator.settingsPreferencesRepository.putSettingValue("NameGame", binding.tietNameGame.text.toString())
        sendNotification(CHANNEL, requireContext(), "Juego creado", "Se ha creado el juego exitosamente")
        Toast.makeText(requireContext(), "Nombre del juego guardado: " +
                Locator.settingsPreferencesRepository.getSettingValue("NameGame", ""), Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }


    private fun setGameNameEmptyError() {
        Snackbar.make(requireView(), "El nombre no puede estar vacio", Snackbar.LENGTH_LONG).show()
    }

    private fun spinnerGenreChoose(): Genero{
        return when(binding.spinnerGameGenre.selectedItemId){
            0L -> Genero.FANTASIA
            1L -> Genero.SHOOTER
            2L -> Genero.HORROR
            else -> {
                Genero.FANTASIA
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private const val CHANNEL = "GAME_CHANNEL"
    }
}