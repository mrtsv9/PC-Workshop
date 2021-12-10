package com.example.pcworkshop.screen.orders.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.screen.employees.adapters.EmployeesAdapter

class OrdersAdapter(): RecyclerView.Adapter<EmployeesAdapter.ClientsViewHolder>() {

    private var ordersList = emptyList<com.example.pcworkshop.models.orders.Orders>()
    private var clientsList = emptyList<Employees>()

    class ClientsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvAddress: TextView = itemView.findViewById(R.id.tvOrderAddress)
        private val tvClientName: TextView = itemView.findViewById(R.id.tvOrderClientName)
        private val tvClientLastName: TextView = itemView.findViewById(R.id.tvOrderClientLastName)
        private val tvClientEmail: TextView = itemView.findViewById(R.id.tvOrderClientEmail)
        private val tvClientNumber: TextView = itemView.findViewById(R.id.tvOrderClientNumber)
        private val tvDelivery: TextView = itemView.findViewById(R.id.tvOrderDelivery)
        private val tvPayment: TextView = itemView.findViewById(R.id.tvOrderPayment)

        fun bind(order: com.example.pcworkshop.models.orders.Orders, clients: List<Employees>) {
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

    @SuppressLint("NotifyDataSetChanged")
    fun setData(orders: List<com.example.pcworkshop.models.orders.Orders>?, clients: List<Employees>) {
        if (orders != null) {
            this.ordersList = orders
            this.clientsList = clients
        }
        notifyDataSetChanged()
    }

}