package com.moronlu18.accountsignin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.moronlu18.accountsignin.adapter.UserAdapter
import com.moronlu18.accountsignin.data.model.User
import com.moronlu18.accountsignin.data.repository.UserRepository
import com.moronlu18.accountsignin.databinding.FragmentUserListBinding


class UserListFragment : Fragment(), UserAdapter.OnUserClick {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpUserRecycler()
    }

    /**
     * Función que inicializa el RecyclerView que el listado de la aplicación
     */
    private fun setUpUserRecycler() {

        var adapter: UserAdapter = UserAdapter(UserRepository.dataSet, requireContext(), this){
            Toast.makeText(requireContext(), "Usuario seleccionado mediante lambda $it", Toast.LENGTH_SHORT).show()
        }

        //1. ¿Cómo quiero que se muestren los elementos de la lista?
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    /**
     * Esta funcion se llama de forma asincrona cuando el usuario pulse sobre un elemento del RecyclerView
     */
    override fun userClick(user: User) {
        Toast.makeText(requireActivity(), "Pulsacion corta en el usuario $user", Toast.LENGTH_SHORT).show()
    }

    override fun userOnLongClick(user: User) {
        Toast.makeText(requireActivity(), "Pulsacion larga en el usuario $user", Toast.LENGTH_LONG).show()
    }
}