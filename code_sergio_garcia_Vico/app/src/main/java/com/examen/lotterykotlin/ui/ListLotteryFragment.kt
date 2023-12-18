package com.examen.lotterykotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.examen.lotterykotlin.R
import com.examen.lotterykotlin.databinding.FragmentListLotteryBinding
import com.examen.lotterykotlin.ui.usecase.ListLotteryViewModel

/**
 * Clase ListLotteryFragment
 *
 * @author Sergio Garcia Vico | 26524624N
 */

class ListLotteryFragment : Fragment(), MenuProvider {

    private var _binding: FragmentListLotteryBinding? = null
    private val binding
        get() = _binding!!
    private val viewmodel: ListLotteryViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListLotteryBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Se inicializa el menú
        val menuhost: MenuHost = requireActivity()
        menuhost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        binding.ftbAdd.setOnClickListener{findNavController().navigate(R.id.action_listLotteryFragment_to_addLotteryFragment)}
        initRecyclerView()
        noData()
    }

    private fun initRecyclerView(){
        binding.rvLottery.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLottery.adapter = LotteryAdapter()
    }

    /**
     * Método que añade las opciones del menú definidas en R.menu.menu_list_user al menú
     * principal
     */
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_list_lottery, menu)
    }

    private fun noData(){
        if(viewmodel.getRepository() == 0){
            binding.rvLottery.visibility = View.GONE
            binding.animationView.visibility = View.VISIBLE
        } else {
            binding.rvLottery.visibility = View.VISIBLE
            binding.animationView.visibility = View.GONE
        }
    }


    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            else -> false
        }
    }


    companion object {
        private const val TAG = "ListLotteryFragment"
    }
}