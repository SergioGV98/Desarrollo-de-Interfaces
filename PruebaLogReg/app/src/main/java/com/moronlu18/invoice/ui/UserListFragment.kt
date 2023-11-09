package com.moronlu18.invoice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.moronlu18.invoice.R
import com.moronlu18.invoice.data.model.User
import com.moronlu18.invoice.databinding.FragmentUserListBinding
import com.moronlu18.invoice.ui.adapter.UserAdapter

class UserListFragment : Fragment() {

    private var _binding : FragmentUserListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUserRecycler()
    }

    private fun setUpUserAdapter() {

    }

    /**
     * Funcion que inicializa el RecyclerView que muestra el listado de usuarios de la aplicacion
     */

    private fun setUpUserRecycler() {
        //Crear el Adapter con los valores en el constructor primario
        var adapter:UserAdapter = UserAdapter (setUpDataSetUser(), requireContext())


        //1. ¿Como quiero que se muestren los elementos de la lista?
        with(binding.rvUser){
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    private fun setUpDataSetUser(): MutableList<User> {
        var dataSet: MutableList<User> = ArrayList()
        dataSet.add(User("Sergio", "Garcia", "sergio@iesportada.org"))
        dataSet.add(User("Paco", "Ruiz", "Paco@iesportada.org"))
        dataSet.add(User("Antonio", "Paca", "Antonio@iesportada.org"))
        dataSet.add(User("Juan", "antonio", "Juan@iesportada.org"))
        dataSet.add(User("Miguel", "jesus", "Miguel@iesportada.org"))
        dataSet.add(User("Juanfran", "ruiz", "Juanfran@iesportada.org"))
        return dataSet
    }
}