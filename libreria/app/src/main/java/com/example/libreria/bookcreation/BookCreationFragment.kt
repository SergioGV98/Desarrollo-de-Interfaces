package com.example.libreria.bookcreation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.libreria.R
import com.example.libreria.bookcreation.viewmodel.BookCreationViewModel
import com.example.libreria.data.Book
import com.example.libreria.databinding.FragmentBookCreationBinding
import com.example.libreria.enums.CategoryBook
import com.google.android.material.snackbar.Snackbar

class BookCreationFragment : Fragment() {

    private var _binding: FragmentBookCreationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookCreationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookCreationBinding.inflate(inflater, container, false)
        binding.viewmodel = this.viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getState().observe(viewLifecycleOwner) {
            when (it) {
                BookCreationState.BookNameIsMandatory -> setBookNameError()
                BookCreationState.BookPriceIsMandatory -> setBookPriceError()
                BookCreationState.BookPriceNotValid -> setBookPriceError2()
                else -> {
                    onSuccess()
                }
            }
        }

    }

    private fun setBookNameError() {
        binding.tietName.error = getString(R.string.name_error)
        binding.tietName.requestFocus()
    }

    private fun setBookPriceError() {
        binding.tietBookPrice.error = getString(R.string.price_error)
        binding.tietBookPrice.requestFocus()
    }

    private fun setBookPriceError2() {
        binding.tietBookPrice.error = getString(R.string.price2_error)
        binding.tietBookPrice.requestFocus()
    }

    private fun onSuccess(){
        val nameBook = binding.tietName.text.toString()
        val nameAuthor = binding.tietNameAuthor.text.toString()
        val mainCategory = category(binding.spCategory)
        val secondaryCategory = category(binding.spCategorySecundary)
        val bookPrice = binding.tietBookPrice.text.toString().toDouble()

        viewModel.addBook(Book(viewModel.idBook(), nameBook, nameAuthor, mainCategory, secondaryCategory, bookPrice))
        Snackbar.make(requireView(), getString(R.string.book_creation_success), Snackbar.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_bookCreationFragment_to_listBookFragment)
    }

    private fun category(sp : Spinner): CategoryBook{
        return when(sp.selectedItem){
            0 -> CategoryBook.FICCION
            1 -> CategoryBook.NO_FICCION
            2 -> CategoryBook.CIENCIA_FICCION
            3 -> CategoryBook.MISTERIO
            4 -> CategoryBook.AVENTURA
            5 -> CategoryBook.ROMANCE
            6 -> CategoryBook.FANTASIA
            7 -> CategoryBook.BIOGRAFIA
            else -> {CategoryBook.FANTASIA}
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}