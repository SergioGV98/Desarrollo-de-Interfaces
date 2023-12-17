package com.sergiogv98.loginSimple.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.pruebas.R
import com.example.pruebas.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.sergiogv98.loginSimple.usecase.LoginViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner){
            when(it){
                LoginState.EmailIsEmpty -> setEmailIsEmpty()
                LoginState.PasswordIsEmpty -> setPasswordIsEmpty()
                else -> {onSuccess()}
            }
        }
    }

    private fun setEmailIsEmpty(){
        Snackbar.make(requireView(), getText(R.string.error_email), Snackbar.LENGTH_LONG).show()
    }

    private fun setPasswordIsEmpty(){
        Snackbar.make(requireView(), getText(R.string.error_password), Snackbar.LENGTH_LONG).show()
    }

    private fun onSuccess(){
        Snackbar.make(requireView(), "EXITO", Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}