package com.example.pcworkshop.screen.employees.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.orders.Orders

class EmployeesAdapter(): RecyclerView.Adapter<EmployeesAdapter.ClientsViewHolder>() {

    private var ordersList = emptyList<Orders>()

    class ClientsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        fun bind(order: com.example.pcworkshop.models.orders.Orders) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder {
        return ClientsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false))
    }

    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int) {
        holder.bind(ordersList[position])
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(orders: List<com.example.pcworkshop.models.orders.Orders>?) {
        if (orders != null) {
            this.ordersList = orders
        }
        notifyDataSetChanged()
    }

}