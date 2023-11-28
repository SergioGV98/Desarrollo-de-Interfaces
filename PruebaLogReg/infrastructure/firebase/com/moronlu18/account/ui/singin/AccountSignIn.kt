package com.moronlu18.account.ui.singin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.moronlu18.accountsignin.R
import com.moronlu18.accountsignin.databinding.FragmentAccountSignInBinding


class AccountSignIn : Fragment() {

    private var _binding: FragmentAccountSignInBinding? = null
    private val binding get() = _binding!!

    //Se inicializara posteriormente
    //private lateinit var viewModel: SignInViewModel
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAccountSignInBinding.inflate(inflater, container, false)

        //Pasamos a la interfaz la instancia del ViewModel para que actualice/recoja los valores
        //del email y password automaticamente y asociar el evento onClick del boton a una funcion
        binding.viewmodel = this.viewModel

        //IMPORTANTE: Hay que establecer el Fragment/Activity vinculado al binding para actualizar
        //los valores del Binding en base al ciclo de vida
        binding.lifecycleOwner = this
        binding.tietEmailSignIn.addTextChangedListener(GeneralTextWatcher(binding.tieEmailSignIn))
        binding.tietPassword.addTextChangedListener(GeneralTextWatcher(binding.tilPassword))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Este codigo ya no es necesario ya que se implementa mediante Data Binding
        /*binding.btSigIn.setOnClickListener{
          //findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }*/
        /*binding.btListUser.setOnClickListener{
            findNavController().navigate(com.moronlu18.invoice.R.id.action_accountSignInFragment_to_as_userListFragment) //Crear esta acción
        }*/
        viewModel.getState().observe(viewLifecycleOwner, Observer {
            when (it) {
                SignInState.EmailEmptyError -> setEmailEmptyError()
                SignInState.PasswordEmptyError -> setPasswordEmptyError()
                is SignInState.AuthencationError -> showMessage(it.message)
                is SignInState.Loading -> showProgressBar(it.value)
                else -> onSuccess()
            }
        })
    }

    /**
     * Mostrar un progessbar en el comienzo de una operacion alrga como
     * es una consulta a la base de datos, FireBase o bien ocultar
     * la operacion ha terminado.
     */
    private fun showProgressBar(value: Boolean) {
        if(value)
            findNavController().navigate(R.id.action_accountSignIn_to_fragmentProgressDialog)
        else
            findNavController().popBackStack()
    }

    /**
     * Funcion que muestra al usuario un mensaje
     */
    private fun showMessage(message: String) {
        //Toast.makeText(requireContext(), "Mi primer MVVM $message", Toast.LENGTH_SHORT).show()
        val action = AccountSignInDirections.actionAccountSignInToBaseFragmentDialog("Error", message)
        findNavController().navigate(action)
    }

    /**
     * Funcion que muestra el error de Email Empty
     */
    private fun setEmailEmptyError() {
        binding.tieEmailSignIn.error = getString(R.string.errEmailEmpty)
        //El cursor del foco se coloca en el til que tiene el error
        binding.tieEmailSignIn.requestFocus()
    }

    /**
     * Funcion que muestra el error de Password Empty
     */
    private fun setPasswordEmptyError() {
        binding.tilPassword.error = getString(R.string.errPasswordEmpty)
        //El cursor del foco se coloca en el til que tiene el error
        binding.tilPassword.requestFocus()
    }

    private fun onSuccess() {
        Toast.makeText(requireActivity(), "Caso de exito en el Login", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Creamos una clase interna para acceder a las propiedades y funciones de una clase interna
     */
    inner class GeneralTextWatcher(private val accountSignIn: TextInputLayout) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            when (accountSignIn.id) {
                R.id.tieEmailSignIn -> {
                    binding.tieEmailSignIn.error = null
                }

                R.id.tilPassword -> {
                    binding.tilPassword.error = null
                }
            }
        }
    }
}
