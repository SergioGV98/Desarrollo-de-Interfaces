package com.example.repasoexamenlourdes.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repasoexamenlourdes.R
import com.example.repasoexamenlourdes.adapter.GameAdapter
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.repasoexamenlourdes.MainActivity
import com.example.repasoexamenlourdes.data.Juego
import com.example.repasoexamenlourdes.databinding.FragmentGameListBinding
import com.example.repasoexamenlourdes.repository.GameRepositoryDB
import com.example.repasoexamenlourdes.usecase.GameListViewModel
import kotlinx.coroutines.launch

class GameList : Fragment(), GameAdapter.OnGameClick {

    private var _binding: FragmentGameListBinding? = null
    private val binding get() = _binding!!
    private lateinit var gameAdapter: GameAdapter
    private val viewModel: GameListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameListBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        viewModel.allGame.observe(viewLifecycleOwner) { gameList ->
            gameAdapter.submitList(gameList.toMutableList())
        }

        binding.ftbAddGame.setOnClickListener {
            findNavController().navigate(R.id.action_gameList_to_gameCreation)
        }
    }
    private fun initRecyclerView(){
        gameAdapter = GameAdapter(this)
        binding.ListRecyclerGame.layoutManager = LinearLayoutManager(requireContext())
        binding.ListRecyclerGame.adapter = gameAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onGameLongClick(view: View, juego: Juego) {
        viewModel.deleteGame(juego)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_order -> {
                gameAdapter.toggleSortOrder()
                true
            }
            else -> false
        }
    }

}


/* Borrar con ventana emergente
private fun onDeletedItem(task: Task) {

        findNavController().navigate(
            TaskListDirections.actionTaskListToBaseFragmentDialog2(
                getString(R.string.delete_task_info_general),
                getString(R.string.delete_task_info)
            )
        )

        parentFragmentManager.setFragmentResultListener(
            BaseFragmentDialog.request,
            viewLifecycleOwner
        ) { _, result ->
            val success = result.getBoolean(BaseFragmentDialog.result, false)
            if (success) {
                viewModel.deleteTask(task)
            }
        }
    }
 */