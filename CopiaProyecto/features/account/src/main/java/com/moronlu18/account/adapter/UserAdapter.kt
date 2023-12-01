package com.moronlu18.account.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moronlu18.accounts.entity.User
import com.moronlu18.accountsignin.databinding.FragmentLayoutUserItemBinding

//Metemos como parametro a private val listener:OnUserClick. Que será mi listener
class UserAdapter(
    private val listener: OnUserClick,
    private val onItemClick: (user: User) -> Unit //no devuelve nada.
    //private val onItemClick: (eventData:EventData)->Unit
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    //Se crea la coleccion de datos del adapter sin datos
    private var dataset = arrayListOf<User>()

    /**
     * Esta interfaz es el contrato entre el Adapter y el fragmento que lo contiene.
     */
    //Recuerda el interface es un contrato.
    interface OnUserClick { //Este elemento es público
        fun userClick(user: User) //Pulsación corta
        fun userOnLongClick(user: User) //Larga
        //fun deleteClick(user:User) //Eliminar usuario.
//El fragment queremos que se encargue de las funciones.
    }

    /*
        data class UserDataEvent{
            Event event ;
            fun userClick(user: User) //Pulsación corta
            fun userOnLongClick(user: User) //Larga
            fun deleteClick(user:User) //Eliminar usuario.
        }
      */
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
     * Funcion que actualiza los datos del adapter y le dice a la vista que se invalide
     * y vuelva a redibujarse
     */
    fun update(newDataSet: ArrayList<User>) {
        //Actualizar mi dataset y notificar a la vista el cambio
        notifyDataSetChanged()
        //dataset = newDataSet
    }

    /**
     * La clase viewHolde contiene todos los elementos de view o del layout XML que se ha inflado.
     */
    //Hay que poner inner para acceder al listener, es decir hacerlo interna(?)
    inner class UserViewHolder(private val binding: FragmentLayoutUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //root

        fun bind(item: User) {

            with(binding) {
                imgUser.text = item.name.substring(0, 1).uppercase()
                tvName.text = item.name
                tvEmail.text = item.email
                //Manejamos el evento Event Click
                //ViewHolder recibe el evento onClick
                root.setOnClickListener { _ ->
                    //llamaré a un método de la interfaz declarada dentro del adapter
                    //Como no utilizo el parámetro de entrada tipo View, Kotlin me recomienda usar _

                    //Lo desactivamos de la interfaz
                    //listener.userClick(item)
                    onItemClick(item)
                }
                //Manejar la pulsación larga EventlongClick
                root.setOnLongClickListener { _ ->
                    listener.userOnLongClick(item)
                    //Se debe indicar al framework/android que se consume el evento.
                    true
                    //Otra manera.
                    //return@setOnLongClickListener true
                }

            }

        }

    }
}