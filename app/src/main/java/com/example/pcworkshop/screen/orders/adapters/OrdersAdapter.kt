package com.example.pcworkshop.screen.orders.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.clients.Client
import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.screen.clients.ClientsFragment
import com.example.pcworkshop.screen.clients.view_models.ClientsViewModel

class OrdersAdapter(): RecyclerView.Adapter<OrdersAdapter.ClientsViewHolder>() {

    private var ordersList = emptyList<Orders>()
    private var clientsList = emptyList<Client>()

    class ClientsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvAddress: TextView = itemView.findViewById(R.id.tvOrderAddress)
        private val tvClientName: TextView = itemView.findViewById(R.id.tvOrderClientName)
        private val tvClientLastName: TextView = itemView.findViewById(R.id.tvOrderClientLastName)
        private val tvClientEmail: TextView = itemView.findViewById(R.id.tvOrderClientEmail)
        private val tvClientNumber: TextView = itemView.findViewById(R.id.tvOrderClientNumber)
        private val tvDelivery: TextView = itemView.findViewById(R.id.tvOrderDelivery)
        private val tvPayment: TextView = itemView.findViewById(R.id.tvOrderPayment)

        fun bind(order: Orders, clients: List<Client>) {
//            ClientsFragment().getClient(order.clientId)
//            val client = ClientsViewModel().clientLiveData
            val client = clients[order.clientId]
            tvAddress.text = order.address
            tvClientName.text = client.firstName
            tvClientLastName.text = client.lastName
            tvClientEmail.text = client.email
            tvClientNumber.text = client.phoneNumber
            tvDelivery.text= order.delivery.deliveryType
            tvPayment.text= order.payment.paymentType
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder {
        return ClientsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false))
    }

    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int) {
        holder.bind(ordersList[position], clientsList)
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    fun setData(orders: List<Orders>?, clients: List<Client>) {
        if (orders != null) {
            this.ordersList = orders
            this.clientsList = clients
        }
        notifyDataSetChanged()
    }

}