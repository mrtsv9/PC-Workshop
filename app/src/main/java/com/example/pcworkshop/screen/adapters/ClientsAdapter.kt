package com.example.pcworkshop.screen.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.clients.Client

class ClientsAdapter(
    private val clientsList: List<Client>
    ): RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder>() {

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
}