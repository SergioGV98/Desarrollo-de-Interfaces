package com.moronlu18.account.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.moronlu18.account.usecase.SignInState
import com.moronlu18.account.usecase.SignUpState
import com.moronlu18.account.usecase.SignUpViewModel
import com.moronlu18.accountsignin.R
import com.moronlu18.accountsignin.databinding.FragmentAccountSignUpBinding

class SignUpFragment : Fragment() {

    private var _binding: FragmentAccountSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountSignUpBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner) {
            when (it) {
                SignUpState.NameIsEmpty -> showNameEmptyError()
                SignUpState.EmailIsEmpty -> showEmailEmptyError()

                else -> {onSuccess()}
            }
        }

    }

    private fun onSuccess(){

    }

    private fun showNameEmptyError() {
        Snackbar.make(
            binding.SignUpFragment, R.string.name_error, Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showEmailEmptyError() {
        Snackbar.make(
            binding.SignUpFragment, R.string.email_error, Snackbar.LENGTH_SHORT
        ).show()
    }

    /*private fun showEmailCannotFindError() {
        Snackbar.make(
            binding.SignUpFragment, R.string.email_find_error, Snackbar.LENGTH_SHORT
        ).show()
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
