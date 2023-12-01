package com.moronlu18.account.ui.userlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.moronlu18.account.adapter.UserAdapter
import com.moronlu18.accounts.entity.User
import com.moronlu18.accountsignin.R
import com.moronlu18.accountsignin.databinding.FragmentUserListBinding


class UserListFragment : Fragment(), UserAdapter.OnUserClick {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: UserListViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

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
        viewmodel.getState().observe(viewLifecycleOwner, Observer{
            when(it){
                is UserListState.Loading -> showProgressBar(it.value)
                UserListState.NoDataError -> showNoDataError()
                is UserListState.Success -> onSuccess(it.dataSet)
            }
        })
    }

    /**
     * Cuando el fragment se inicia debe pedir el listado de usuarios al ViewModel - infraestrucura
     * (Firebase, Json, Room..)
     */

    override fun onStart(){
        super.onStart()
        viewmodel.getUserList()
    }

    /**
     * Funcion que contiene el listado de usuarios
     */
    private fun onSuccess(dataset: ArrayList<User>) {
        //Desactivar la animacion y visualizar el recyclerview
        hideNoDataError()
        userAdapter.update(dataset)
    }

    private fun hideNoDataError() {
        binding.animationView.visibility = View.GONE
        binding.rvUser.visibility = View.VISIBLE
    }

    /**
     * Funcion que muestra el error en caso de no recibir datos
     */
    private fun showNoDataError() {
        binding.animationView.visibility = View.VISIBLE
        binding.rvUser.visibility = View.GONE
    }

    /**
     * Mostrar el progressbar en la vista
     */
    private fun showProgressBar(value: Boolean) {
        if(value)
            findNavController().navigate(R.id.action_accountSignIn_to_fragmentProgressDialog)
        else
            findNavController().popBackStack()
    }

    /**
     * Función que inicializa el RecyclerView que muestra el listado de usuarios de la aplicación
     */
    private fun setUpUserRecycler() {

        /*Añadimos el listener
        var adapter = UserAdapter(UserRepository.dataSet, requireContext(),this)

        El abstracto, recoge un usuario y puedo usar el view.
        Otra manera --> Abramos llaves

        todo ¿Esto está mal? Ya que pregunta al repositorio  var adapter = UserAdapter(null, requireContext(),this)
        todo Ya que el que le facilita es el usecase. Así se inicia a null
        var adapter = UserAdapter(null, requireContext(),this){*/
        userAdapter = UserAdapter(this){
            //when(event){}

            Toast.makeText(requireContext(),"Usuario Seleccionado mediante lambda $it", Toast.LENGTH_LONG).show()
        }

        //1. ¿Cómo quiero que se muestren los elementos de la lista?
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            this.adapter = userAdapter
        }
    }

    /**
     * Esta función se llama de forma asíncrona cuando el usuario sobre un elemento del recycleview.
     */

    override fun userClick(user: com.moronlu18.accounts.entity.User) {
        Toast.makeText(requireActivity(),"Pulsación corta en el usuario $user", Toast.LENGTH_LONG).show()
    }

    override fun userOnLongClick(user: com.moronlu18.accounts.entity.User) {
        Toast.makeText(requireActivity(),"Pulsación larga en el usuario $user", Toast.LENGTH_LONG).show()
    }
}