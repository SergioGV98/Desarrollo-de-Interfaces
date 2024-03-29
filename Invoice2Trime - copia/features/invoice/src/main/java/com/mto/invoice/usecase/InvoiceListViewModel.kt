package com.mto.invoice.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moronlu18.data.customer.Customer
import com.moronlu18.data.invoice.Invoice
import com.moronlu18.network.ResourceList
import com.moronlu18.repository.InvoiceProvider
import com.moronlu18.invoice.Locator
import com.moronlu18.repository.CustomerProvider
import kotlinx.coroutines.launch

//Tener una referencia al objeto eliminado por si se ejecuta un undo/control+z

class InvoiceListViewModel : ViewModel() {

    private var state = MutableLiveData<InvoiceListState>()
    private lateinit var invoiceDeleted: Invoice

    /**
     * Función que pide el listado con una pantalla de carga
     */
    fun getInvoiceList() {
        viewModelScope.launch {
            state.value = InvoiceListState.Loading(true)
            val result = InvoiceProvider.getInvoiceList()
            state.value = InvoiceListState.Loading(false)

            when (result) {
                is ResourceList.Success<*> -> {
                    val invoices = result.data as ArrayList<Invoice>

                    val sortPreference = Locator.settingsPreferencesRepository.getSortInvoice()
                    when(sortPreference){
                        "id" -> invoices.sortBy { it.id }
                        //Todo modificado parcialmente
                        //"name_asc" -> invoices.sortBy { it.customerId.name }
                        "name_asc" -> invoices.sortBy { getCustomer(it.customerId.value) }
                        //"name_desc" -> invoices.sortByDescending { it.customerId.name }
                        "name_desc" -> invoices.sortByDescending { getCustomer(it.customerId.value)}
                        "status" -> invoices.sortBy { it.status.toString() }
                    }
                    state.value = InvoiceListState.Success(invoices)
                }


                is ResourceList.Error -> state.value = InvoiceListState.NoDataSet
            }
        }
    }

    /**
     * Función que devuelve la lista sin carga llamada cuando se borra una factura
     */
    fun getListWithoutLoading() {
        viewModelScope.launch {

            when (val result = InvoiceProvider.getListWithoutLoading()) {
                is ResourceList.Success<*> -> {
                    val invoices = result.data as ArrayList<Invoice>
                    val sortPreference = Locator.settingsPreferencesRepository.getSortInvoice()

                    when(sortPreference){
                        "id" -> invoices.sortBy { it.id }
                        //Todo modificado parcialmente
                        //"name_asc" -> invoices.sortBy { it.customerId.name }
                        "name_asc" -> invoices.sortBy { getCustomer(it.customerId.value) }
                        //"name_desc" -> invoices.sortByDescending { it.customerId.name }
                        "name_desc" -> invoices.sortByDescending { getCustomer(it.customerId.value)}
                        "status" -> invoices.sortBy { it.status.toString() }
                    }
                    state.value = InvoiceListState.Success(invoices)
                }

                is ResourceList.Error -> state.value = InvoiceListState.NoDataSet
            }
        }
    }
    fun getPosByInvoice(invoice: Invoice): Int {
        return InvoiceProvider.getPosByInvoice(invoice)
    }

    /**
     * Función que devuelve la variable state
     */
    fun getState(): LiveData<InvoiceListState> {
        return state
    }


    //Todo añadido método para el tema del orden

    private fun getCustomer(customerId: Int): Customer {
        return CustomerProvider.getCustomerPos(customerId)
    }
}