package com.example.pcworkshop.screen.clients.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.clients.Client
import com.example.pcworkshop.screen.clients.ClientsFragment

class ClientsAdapter(): RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder>() {

    private var clientsList = emptyList<Client>()

    class ClientsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvClientName)
        private val tvLastName: TextView = itemView.findViewById(R.id.tvClientLastName)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvClientEmail)
        private val tvPhoneNumber: TextView = itemView.findViewById(R.id.tvClientNumber)

        fun bind(item: Client) {
            tvName.text = item.firstName
            tvLastName.text = item.lastName
            tvEmail.text = item.email
            tvPhoneNumber.text = item.phoneNumber
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

    fun setData(clients: List<Client>?) {
        if (clients != null) {
            this.clientsList = clients
        }
        notifyDataSetChanged()
    }

}