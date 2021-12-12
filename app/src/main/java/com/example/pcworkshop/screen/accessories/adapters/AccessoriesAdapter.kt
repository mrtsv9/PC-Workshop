package com.example.pcworkshop.screen.accessories.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.accessories.Accessories
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.employees.Employees
import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.models.pc.Pc
import com.example.pcworkshop.models.pc_accessories.PcAccessories
import com.example.pcworkshop.screen.pc.adapters.PcAdapter

class AccessoriesAdapter(private var accessoriesList: List<Accessories>) : RecyclerView.Adapter<AccessoriesAdapter.AccessoriesViewHolder>() {

//    private var accessoriesList = emptyList<Accessories>()

    class AccessoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvType: TextView = itemView.findViewById(R.id.tvAccessoryType)
        private val tvModel: TextView = itemView.findViewById(R.id.tvAccessoryModel)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvAccessoryPrice)
        private val tvProperties: TextView = itemView.findViewById(R.id.tvAccessoryProperties)


        fun bind(accessory: Accessories) {
            tvType.text = accessory.typeOfAccessories.type
            tvModel.text = accessory.name
            tvPrice.text = accessory.price.toString()
            tvProperties.text = accessory.properties
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccessoriesViewHolder {
        return AccessoriesViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_accessory, parent, false))
    }

    override fun onBindViewHolder(holder: AccessoriesViewHolder, position: Int) {
        holder.bind(accessoriesList[position])
    }

    override fun getItemCount(): Int {
        return accessoriesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(accessories: List<Accessories>?) {
        if (accessories != null) {
            this.accessoriesList = accessories
        }
        notifyDataSetChanged()
    }

}