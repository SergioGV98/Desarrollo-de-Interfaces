package com.example.repasobd

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repasobd.adapter.BookAdapter
import com.example.repasobd.base.Book
import com.example.repasobd.base.CategoryBook
import com.example.repasobd.databinding.FragmentBookListBinding
import com.example.repasobd.repository.BookProviderDB

class BookList : Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBookListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        bookAdapter = BookAdapter()
        binding.bookListRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.bookListRecycler.adapter = bookAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}