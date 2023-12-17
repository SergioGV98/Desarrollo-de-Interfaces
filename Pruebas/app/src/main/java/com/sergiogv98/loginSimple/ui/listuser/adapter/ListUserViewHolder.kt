package com.sergiogv98.loginSimple.ui.listuser.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebas.databinding.ItemUserBinding
import com.sergiogv98.loginSimple.data.Usuario

class ListUserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemUserBinding.bind(view)

    fun render(user: Usuario){
        binding.txtItemName.text = user.name
        binding.txtItemEmail.text = user.email
        binding.txtItemTypeUser.text = user.typeUser.toString()
    }

}