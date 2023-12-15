package com.moronlu18.account.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.moronlu18.account.adapter.UserAdapter
import com.moronlu18.account.usecase.UserListState
import com.moronlu18.account.usecase.UserListViewModel
import com.moronlu18.accounts.entity.User
import com.moronlu18.accountsignin.R
import com.moronlu18.accountsignin.databinding.FragmentUserListBinding
import com.moronlu18.invoice.ui.MainActivity


class UserListFragment : Fragment(), UserAdapter.OnUserClick, MenuProvider {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    private val viewmodel:UserListViewModel by viewModels()
    private lateinit var userAdapter:UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Funcion que personaliza el boton flotante
        setUpFab()
        //Funcion que personaliza el menu de la Toolbar
        setUpToolbar()
        setUpUserRecycler()


        viewmodel.getState().observe(viewLifecycleOwner, Observer {
            when(it){
                is UserListState.Loading -> showProgressBar(it.value)

                UserListState.NoDataError -> showNoDataError()
                is UserListState.Success -> onSuccess(it.dataset)
            }
        })
    }

    /**
     * Esta funcion personaliza el comportamiento de la Toolbar de la
     * Activity
     */
    private fun setUpToolbar() {
        //Lo mismo que el setUpFab()
        (requireActivity() as MainActivity).toolbar.apply {
            visibility = View.VISIBLE

        }
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    /**
     * Se añade las opciones del menu definidas en R.menu.menu_list_user al
     * menu principal.
     */
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_list_user, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId){
            R.id.action_refresh -> {
                viewmodel.getUserList()
                return true
            }
            R.id.action_sort -> {
                userAdapter.sort()
                return true
            }
            else -> false
        }
    }

    /**
     * Esta funcion personaliza el comportamiento del boton flotante de la
     * Activity
     */
    private fun setUpFab() {
        //Modismo Apply de Kotlin
        //RequireActivity me da la informacion de la actividad de la cual parte el
        //Fragment ya que cada fragment tiene una actividad

        /*val fab = (requireActivity() as MainActivity).fab
        fab.visibility = View.VISIBLE
        fab.setOnClickListener{view -> Snackbar.make(view, "Soy el Fragment", Snackbar.LENGTH_LONG).show()}*/

        //Apply simplifica la inicializacion del objeto fab
        (requireActivity() as MainActivity).fab.apply {
            visibility = View.VISIBLE
            setOnClickListener {view ->
                Snackbar.make(view, "Soy el Fragment", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    /**
     * Cuando el faragment se inicia debe pedir el lsitado de usarios al viewmodel. infreaestructura
     */


    override fun onStart() {
        super.onStart()
        viewmodel.getUserList()
    }

    /**
     * Función que contiene el listado de usuarios
     */

    private fun onSuccess(dataset:ArrayList<User>){
        hideNoDataError()
        userAdapter.update(dataset)
    }

    private fun hideNoDataError() {
        binding.animationView.visibility = View.GONE
        binding.rvUser.visibility=View.VISIBLE
    }


    /**
     * Función que muestra el error de que no hay datos
     */
    private fun showNoDataError() {
        binding.animationView.visibility = View.VISIBLE
        binding.rvUser.visibility=View.GONE
    }



    /**
     * Mostar el progressbar en la vista
     */
    private fun showProgressBar(value: Boolean) {
        println("showProgressBar")
    }


    /**
     * Función que inicializa el RecyclerView que muestra el listado de usuarios de la aplicación
     */

    //lateinit val al adapter porque después se reasigna los datos.
    private fun setUpUserRecycler() {

        /*Añadimos el listener
        var adapter = UserAdapter(UserRepository.dataSet, requireContext(),this)

        El abstracto, recoge un usuario y puedo usar el view.
        Otra manera --> Abramos llaves

        todo ¿Esto está mal? Ya que pregunta al repositorio  var adapter = UserAdapter(null, requireContext(),this)
        todo Ya que el que le facilita es el usecase. Así se inicia a null
        var adapter = UserAdapter(null, requireContext(),this){*/


        //Crear el constructor primerario
        userAdapter = UserAdapter(this){
            //when(event){}

            Toast.makeText(requireContext(),"Usuario Seleccionado mediante lambda $it", Toast.LENGTH_LONG).show()
        }

        /*adapter = UserAdapter(UserRepository.dataSet, requireContext(),this){
            //when(event){}

            Toast.makeText(requireContext(),"Usuario Seleccionado mediante lambda $it", Toast.LENGTH_LONG).show()
        }*/

        //1. ¿Cómo quiero que se muestren los elementos de la lista?
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            this.adapter =  userAdapter
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