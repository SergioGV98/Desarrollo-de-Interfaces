package com.moronlu18.invoice.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moronlu18.invoice.R
import com.moronlu18.invoice.data.model.User

class UserAdapter (private val dataset: MutableList<User>, private val context: Context): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(layoutInflater.inflate(R.layout.item_user, parent, false))
    }

    /**
     * Funcion que devuelve el numero de elementos y por tanto se llamara al metodo onCreateViewHolder tantas
     * veces como item se visualicen en el RecyclerView
     */
    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = dataset[position]
        holder.bind(item, context)
    }

    /**
     * La clase ViewHolder contiene todos los elementos de View o del Layout XML que se
     * ha inflado
     */
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val tvName = view.findViewById(R.id.user_name) as TextView
        val tvEmail = view.findViewById(R.id.user_email) as TextView
        val imgUser = view.findViewById(R.id.imgUser) as ImageView

        fun bind(item: User, context: Context) {
            tvName.text = item.name
            tvEmail.text = item.email
        }
    }
}