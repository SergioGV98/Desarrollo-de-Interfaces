package com.example.libreria.booklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libreria.R
import com.example.libreria.databinding.FragmentListBookBinding
import com.example.libreria.repository.BookRepository

class ListBookFragment : Fragment() {

    private var _binding: FragmentListBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBookBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.ftbAddBook.setOnClickListener { findNavController().navigate(R.id.action_listBookFragment_to_bookCreationFragment) }
    }

    private fun initRecyclerView() {
        binding.booklistreclycer.layoutManager = LinearLayoutManager(requireContext())
        binding.booklistreclycer.adapter = BookAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}