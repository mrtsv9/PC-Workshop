package com.example.pcworkshop.screen.clients.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.clients.Clients

class ClientsAdapter(
    private val clickListener: (Clients) -> Unit
): RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder>() {

    private var clientsList = emptyList<Clients>()

    class ClientsViewHolder(itemView: View,
    private val clickListener: (Clients) -> Unit): RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView = itemView.findViewById(R.id.tvClientName)
        private val tvLastName: TextView = itemView.findViewById(R.id.tvClientLastName)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvClientEmail)
        private val tvPhoneNumber: TextView = itemView.findViewById(R.id.tvClientNumber)

        fun bind(item: Clients) {
            itemView.setOnClickListener { clickListener(item) }
            tvName.text = item.firstName
            tvLastName.text = item.lastName
            tvEmail.text = item.email
            tvPhoneNumber.text = item.phoneNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder {
        return ClientsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_client, parent, false), clickListener)
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