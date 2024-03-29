package com.moronlu18.account.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moronlu18.accounts.entity.User
import com.moronlu18.accountsignin.databinding.FragmentLayoutUserItemBinding

class UserAdapter(
    private val listener: OnUserClick,
    private val onItemClick: (user: User)->Unit
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    private var dataset = arrayListOf<User>()

    /**
     * Esta interfaz es el contrato entre el Adapter y el fragmento que lo contiene.
     */
    interface OnUserClick {
        fun userClick(user: User)
        fun userOnLongClick(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context) //Esto es un manager.

        return UserViewHolder(FragmentLayoutUserItemBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = dataset.get(position)
        holder.bind(item)
    }

    /**
     * Función que devuelve el número de elementos y por tanto se llamará al método onCreateViewHolder,
     * tantas veces como item se visualicen en el RecyclerView
     * Item se visualen en el RecyclerView
     */
    override fun getItemCount(): Int {
        return dataset.size
    }

    /**
     * Función que actualiza los datos del adapter le dice a la vista que se invalide
     * y vuelve a redibujarse
     */
    fun update(newDataSet:ArrayList<User>){
        dataset = newDataSet
        notifyDataSetChanged()
    }

    /**
     * Funcion que ordena el dataset en base a una propiedad personalizado
     */
    fun sort() {
        //Orden personalizado se establece mediante una propiedad
        dataset.sortBy { it.email }
        notifyDataSetChanged()
    }

    /**
     * La clase viewHolde contiene todos los elementos de view o del layout XML que se ha inflado.
     */
    inner class UserViewHolder(private val binding: FragmentLayoutUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {

            with(binding) {
                imgUser.text = item.name.substring(0, 1).uppercase()
                tvName.text = item.name
                tvEmail.text = item.email
                root.setOnClickListener { _ ->
                    onItemClick(item)
                }
                root.setOnLongClickListener {_ ->
                    listener.userOnLongClick(item)
                    true
                }

            }

        }

    }
}