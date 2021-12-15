package com.example.pcworkshop.screen.orders.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.orders.Orders

class OrdersAdapter(
    private val clickListener: (Orders) -> Unit
): RecyclerView.Adapter<OrdersAdapter.ClientsViewHolder>() {

    private var ordersList = emptyList<Orders>()
    private var clientsList = emptyList<Clients>()

    class ClientsViewHolder(itemView: View,
    private val clickListener: (Orders) -> Unit): RecyclerView.ViewHolder(itemView){
        private val tvOrderId: TextView = itemView.findViewById(R.id.tvOrderId)
        private val tvAddress: TextView = itemView.findViewById(R.id.tvOrderAddress)
        private val tvClientName: TextView = itemView.findViewById(R.id.tvOrderClientName)
        private val tvClientLastName: TextView = itemView.findViewById(R.id.tvOrderClientLastName)
        private val tvClientEmail: TextView = itemView.findViewById(R.id.tvOrderClientEmail)
        private val tvClientNumber: TextView = itemView.findViewById(R.id.tvOrderClientNumber)
        private val tvDelivery: TextView = itemView.findViewById(R.id.tvOrderDelivery)
        private val tvPayment: TextView = itemView.findViewById(R.id.tvOrderPayment)

        fun bind(order: Orders, clients: List<Clients>) {

            itemView.setOnClickListener { clickListener(order) }

            var client: Clients? = null
            clients.forEach {
                if (it.clientId == order.clientId) {
                    client = it
                }
            }
            tvOrderId.text = order.orderId.toString()
            tvAddress.text = order.address
            tvClientName.text = client?.firstName
            tvClientLastName.text = client?.lastName
            tvClientEmail.text = client?.email
            tvClientNumber.text = client?.phoneNumber
            tvDelivery.text= order.delivery.deliveryType
            tvPayment.text= order.payment.paymentType
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder {
        return ClientsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int) {
        holder.bind(ordersList[position], clientsList)
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(orders: List<Orders>?, clients: List<Clients>) {
        if (orders != null) {
            this.ordersList = orders
            this.clientsList = clients
        }
        notifyDataSetChanged()
    }

}