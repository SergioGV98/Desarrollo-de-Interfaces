package com.moronlu18.accountsignup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.moronlu18.accountsignup.databinding.FragmentAccountSignUpBinding

class AccountSignUp : Fragment() {

    private var _binding: FragmentAccountSignUpBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAccountSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Se utiliza esta función para inicializar los componentes que se han creado ya en la función onCreateView
     * requiredContext() utiliza el de la clase base, this siempre en java.
     * la razón de context
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //1. Crear colección de datos
        val itemList = arrayListOf<String>("Private", "Public", "Empty")
        //2. Crear Adapter
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, itemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val listener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //val profile = parent?.adapter?.getItem(position) Ambos funcionan
                val profile = adapter.getItem(position)
                Toast.makeText(requireActivity(), "Elemento pulsado $profile", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        with(binding.spProfile) {
            this.adapter = adapter
            setSelection(2)
            onItemSelectedListener = listener
        }
    }
}