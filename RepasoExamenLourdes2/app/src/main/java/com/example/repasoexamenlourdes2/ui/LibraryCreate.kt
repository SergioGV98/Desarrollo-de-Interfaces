package com.example.repasoexamenlourdes2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.repasoexamenlourdes2.Locator
import com.example.repasoexamenlourdes2.data.Book
import com.example.repasoexamenlourdes2.data.Genre
import com.example.repasoexamenlourdes2.databinding.FragmentLibraryCreateBinding
import com.example.repasoexamenlourdes2.usecase.LibraryCreateViewModel

class LibraryCreate : Fragment() {

    private var _binding: FragmentLibraryCreateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LibraryCreateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryCreateBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner){
            when(it){
                LibraryCreateState.NameIsMandatory -> setBookError()
                else -> {
                    onSuccessCreate()
                }
            }
        }

    }

    private fun onSuccessCreate() {
        val book = Book((viewModel.lastId()+1).toInt(), binding.tietBookName.text.toString(), Genre.FANTASIA)
        viewModel.insertBook(book)
        Locator.userPreferencesRepository.putSettingValue("Book", book.toString())
        findNavController().popBackStack()
    }

    private fun setBookError() {
        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
    }
}