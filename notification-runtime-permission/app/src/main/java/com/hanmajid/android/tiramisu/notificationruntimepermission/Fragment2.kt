package com.hanmajid.android.tiramisu.notificationruntimepermission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hanmajid.android.tiramisu.notificationruntimepermission.databinding.Fragment2Binding


class Fragment2 : Fragment() {

    private var _binding: Fragment2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ftbNextFragment.setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment3)
        }

    }


}