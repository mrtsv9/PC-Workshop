package com.example.pcworkshop.screen.clients.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.screen.orders.OrdersAddingFragment
import com.example.pcworkshop.screen.orders.OrdersAddingFragment.Companion.selectedClientId

class SelectableClientsAdapter(): RecyclerView.Adapter<SelectableClientsAdapter.ClientsViewHolder>() {

    private var clientsList = emptyList<Clients>()
    private var checkedClient: Int = 0

    class ClientsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvClientName)
        private val tvLastName: TextView = itemView.findViewById(R.id.tvClientLastName)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvClientEmail)
        private val tvPhoneNumber: TextView = itemView.findViewById(R.id.tvClientNumber)
        private val ivCheckmark: ImageView = itemView.findViewById(R.id.ivCheckClients)
//        private val alreadySelectedClientId: Int? = null
//        var checkedClientId: Int = 0
//        var checker = OrdersAddingFragment.checker

        fun bind(item: Clients) {

            if (selectedClientId == item.clientId) {
                ivCheckmark.visibility = View.VISIBLE
                itemView.isSelected = true
            }

            tvName.text = item.firstName
            tvLastName.text = item.lastName
            tvEmail.text = item.email
            tvPhoneNumber.text = item.phoneNumber

            itemView.setOnClickListener {

                if (!itemView.isSelected && !OrdersAddingFragment.checker) {
                        itemView.isSelected = true
                        ivCheckmark.visibility = View.VISIBLE
                    selectedClientId = item.clientId
                    OrdersAddingFragment.checker = true
                }
                else if(itemView.isSelected) {
                    itemView.isSelected = false
                    ivCheckmark.visibility = View.GONE
                    selectedClientId = 0
                    OrdersAddingFragment.checker = false
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder {
        return ClientsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_client, parent, false))
    }

    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int) {
        holder.bind(clientsList[position])
    }

    override fun getItemCount(): Int {
        return clientsList.size
    }



    fun setData(clients: List<Clients>?) {
        if (clients != null) {
            this.clientsList = clients
        }
        notifyDataSetChanged()
    }

}