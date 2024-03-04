package com.example.repasoexamenlourdes2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repasoexamenlourdes2.R
import com.example.repasoexamenlourdes2.adapter.BookAdapter
import com.example.repasoexamenlourdes2.data.Book
import com.example.repasoexamenlourdes2.data.Genre
import com.example.repasoexamenlourdes2.databinding.FragmentListLibraryBinding
import com.example.repasoexamenlourdes2.repository.LibraryRepositoryDB
import com.example.repasoexamenlourdes2.usecase.LibraryListViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LibraryList : Fragment() {

    private var _binding: FragmentListLibraryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LibraryListViewModel by viewModels()
    private lateinit var bookAdapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListLibraryBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        viewModel.allBooks.observe(viewLifecycleOwner){bookList ->
            bookAdapter.submitList(bookList.toMutableList())
        }

        binding.ftbAddBook.setOnClickListener {
            findNavController().navigate(R.id.action_libraryList_to_libraryCreate)
        }
    }

    private fun initRecyclerView(){
        bookAdapter = BookAdapter()
        binding.ListRecyclerBook.layoutManager = LinearLayoutManager(requireContext())
        binding.ListRecyclerBook.adapter = bookAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}