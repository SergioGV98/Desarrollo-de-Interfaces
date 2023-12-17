package com.sergiogv98.loginSimple.ui.listuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebas.R
import com.example.pruebas.databinding.FragmentListUserBinding
import com.sergiogv98.loginSimple.repository.UserRepository
import com.sergiogv98.loginSimple.ui.listuser.adapter.ListUserAdapter

class ListFragment : Fragment() {

    private var _binding: FragmentListUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.recyclerListUser.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerListUser.adapter = ListUserAdapter(UserRepository.dataSet)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}