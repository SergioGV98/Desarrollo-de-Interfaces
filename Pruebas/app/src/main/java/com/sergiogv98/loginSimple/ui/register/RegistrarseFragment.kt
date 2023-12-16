package com.sergiogv98.loginSimple.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pruebas.R
import com.example.pruebas.databinding.FragmentRegistrarseBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.sergiogv98.loginSimple.usecase.RegisterViewModel

class RegistrarseFragment : Fragment() {

    private var _binding: FragmentRegistrarseBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrarseBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tietName.addTextChangedListener(GeneralTextWatcher(binding.tilName))
        binding.tietEmail.addTextChangedListener(GeneralTextWatcher(binding.tilEmail))
        binding.tietPassword.addTextChangedListener(GeneralTextWatcher(binding.tilPassword))
        binding.tietPasswordRepeat.addTextChangedListener(GeneralTextWatcher(binding.tilPasswordRepeat))

        viewModel.getState().observe(viewLifecycleOwner){
            when(it){
                RegisterState.NameIsMandatory -> setNameEmptyError()
                RegisterState.EmailIsMandatory -> setEmailEmptyError()
                RegisterState.PasswordIsMandatory -> setPasswordEmptyError(binding.tilPassword)
                RegisterState.PasswordRepeatIsMandatory -> setPasswordEmptyError(binding.tilPasswordRepeat)
                RegisterState.PasswordMustMatch -> passwordMustMatch()
                else -> onSuccess()
            }
        }
    }

    private fun setNameEmptyError(){
        binding.tilName.error = getString(R.string.error_name)
        binding.tilName.requestFocus()
    }

    private fun setEmailEmptyError(){
        binding.tilEmail.error = getString(R.string.error_email)
        binding.tilEmail.requestFocus()
    }

    private fun setPasswordEmptyError(til: TextInputLayout){
        til.error = getString(R.string.error_password)
        til.requestFocus()
    }

    private fun passwordMustMatch(){
        Snackbar.make(requireView(), getString(R.string.error_password_match), Snackbar.LENGTH_LONG).show()
    }

    private fun onSuccess(){
        Snackbar.make(requireView(), getString(R.string.register_success), Snackbar.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_registrarseFragment_to_menuFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class GeneralTextWatcher(private val til: TextInputLayout) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            til.error = null
        }

    }
}