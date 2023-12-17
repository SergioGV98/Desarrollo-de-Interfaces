package com.sergiogv98.loginSimple.ui.listuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebas.R
import com.sergiogv98.loginSimple.data.Usuario

class ListUserAdapter(private val listUser: List<Usuario>) : RecyclerView.Adapter<ListUserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListUserViewHolder(layoutInflater.inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val item = listUser[position]
        holder.render(item)
    }
}