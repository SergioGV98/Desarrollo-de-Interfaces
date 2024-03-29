package com.mto.invoice.adapter.list

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.moronlu18.data.invoice.Invoice
import com.moronlu18.data.invoice.InvoiceStatus

import com.moronlu18.invoicelist.R

import com.moronlu18.invoicelist.databinding.ItemFacturaBinding
import com.moronlu18.repository.CustomerProvider

class FacturaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemFacturaBinding.bind(view)

    fun render(
        invoiceModel: Invoice,
        onClickListener: (Invoice) -> Unit,

        ) {

        //TODO cambio de de CBO que cambia un customerId por un customer.
        val customer = CustomerProvider.getCustomerbyID(invoiceModel.customerId.value)

        binding.itemFacturaIvtNumber.text = invoiceModel.number
        binding.itemFacturaTvId.text = invoiceModel.id.value.toString()
        //binding.itemFacturaTvCliente.text = invoiceModel.customerId.name
        binding.itemFacturaTvCliente.text = customer?.name
        with(binding.itemFacturaEstado) {
            text = giveStatusText(invoiceModel.status)
            setTextColor(setColorEstado(invoiceModel.status))

        }

        //Todo Cambiado por el tema de la foto.
        //Todo (28/01/2024) V2 de lo de la foto después de UniqueId

        if(customer?.phototrial != null) {
            binding.itemFacturaIvKiwi.setImageResource(customer.phototrial!!)
        } else {
            binding.itemFacturaIvKiwi.setImageBitmap(customer?.photo)
        }

        /*if (invoiceModel.customerId?.phototrial != null) {
            binding.itemFacturaIvKiwi.setImageResource(invoiceModel.customerId.phototrial!!)
        } else {
            binding.itemFacturaIvKiwi.setImageBitmap(invoiceModel.customerId?.photo)
        }*/
        //binding.itemFacturaIvKiwi.setImageResource(CustomerProvider.getPhoto(facturaModel.customerId))

        itemView.setOnClickListener { onClickListener?.invoke(invoiceModel) }


    }

    /**
     * Funcion que devuelve un texto en funcion del tipo de factura
     */
    fun giveStatusText(tipo: InvoiceStatus): String {
        return if (tipo == InvoiceStatus.PENDIENTE) {
            itemView.context.getString(R.string.invoiceStatusPendiente)
        } else if (tipo == InvoiceStatus.PAGADA) {
            itemView.context.getString(R.string.invoiceStatusPagada)
        } else {
            itemView.context.getString(R.string.invoiceStatusVencida)
        }

    }

    /**
     * Función que devuelve un entero que representa un color,
     * en función del estado de la factura
     */
    fun setColorEstado(status: InvoiceStatus): Int {
        return if (status.equals(InvoiceStatus.PENDIENTE)) {
            Color.parseColor("#FF1100")
        } else if (status.equals(InvoiceStatus.PAGADA)) {
            Color.parseColor("#217C00")
        } else {
            Color.parseColor("#978303")
        }

    }
}