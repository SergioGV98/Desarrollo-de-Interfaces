package com.jcasrui.item.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jcasrui.item.adapter.ItemAdapter
import com.moronlu18.accounts.entity.Item
import com.moronlu18.accounts.repository.ItemProvider
import com.moronlu18.itemcreation.R
import com.moronlu18.itemcreation.databinding.FragmentItemListBinding

class ItemList : Fragment() {

    private var _binding: FragmentItemListBinding? = null
    private val binding get() = _binding!!

    private var itemMutableList: MutableList<Item> = ItemProvider.dataSetItem
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_itemList_to_itemCreation)
        } // Esto hace que al pulsar un articulo en la lista se muestre la interfaz creation
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initReciclerViewItem()

        //binding.itemListRvItems.setOnClickListener{
        //    findNavController().navigate(com.moronlu18.invoice.R.id.action_ItemListFragment_to_ItemCreationFragment)
        //}
    }

    private fun initReciclerViewItem() {
        val manager = LinearLayoutManager(requireContext())

        adapter = ItemAdapter(
            itemList = itemMutableList,
            onClickListener = { item -> onItemSelected(item) }
        )
        binding.itemListRvItems.layoutManager = manager
        binding.itemListRvItems.adapter = ItemAdapter(ItemProvider.dataSetItem) { item ->
            onItemSelected(
                item
            )
        }
    }

    private fun onItemSelected(item: Item) {
        //findNavController().navigate(R.id.action_itemList_to_itemDetail)
        findNavController().navigate(ItemListDirections.actionItemListToItemDetail(item))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}