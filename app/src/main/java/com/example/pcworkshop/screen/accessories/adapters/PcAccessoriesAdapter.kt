package com.example.pcworkshop.screen.accessories.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.accessories.Accessories
import com.example.pcworkshop.models.accessories.TypeOfAccessories
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.employees.Employees
import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.models.pc.Pc
import com.example.pcworkshop.models.pc_accessories.PcAccessories
import com.example.pcworkshop.models.types_of_accessories.TypesOfAccessories
import com.example.pcworkshop.screen.pc.PcAddingFragment.Companion.myAccessory
import com.example.pcworkshop.screen.pc.PcAddingFragment.Companion.tempAccessoriesList
import com.example.pcworkshop.screen.pc.PcAddingFragment.Companion.types
import com.example.pcworkshop.screen.pc.adapters.PcAdapter

class PcAccessoriesAdapter() : RecyclerView.Adapter<PcAccessoriesAdapter.AccessoriesViewHolder>() {

    private var accessoriesList = emptyList<Accessories>()

    class AccessoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvType: TextView = itemView.findViewById(R.id.tvAccessoryType)
        private val tvModel: TextView = itemView.findViewById(R.id.tvAccessoryModel)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvAccessoryPrice)
        private val tvProperties: TextView = itemView.findViewById(R.id.tvAccessoryProperties)
        private val ivCheckmark: ImageView = itemView.findViewById(R.id.ivAccessoryCheckmark)

        fun bind(accessory: Accessories) {
            tvType.text = accessory.typeOfAccessories.type
            tvModel.text = accessory.name
            tvPrice.text = accessory.price.toString()
            tvProperties.text = accessory.properties

            itemView.setOnClickListener {
                if(!itemView.isSelected) {
                    itemView.isSelected = true
                    ivCheckmark.visibility = View.VISIBLE
                    var type =  ""
                    types.map {
                        if (it.typeOfAccessoryId == accessory.typeOfAccessoriesId) {
                            type = it.type
                        }
                    }
                     myAccessory = Accessories(accessory.accessoriesId, accessory.name, accessory.properties,
                        accessory.price.toString().toInt(), accessory.typeOfAccessoriesId,
                        TypeOfAccessories(accessory.typeOfAccessoriesId, type)
                    )
                    tempAccessoriesList.add(myAccessory!!)
                }
                else if(itemView.isSelected) {
                    itemView.isSelected = false
                    ivCheckmark.visibility = View.GONE
                    tempAccessoriesList.remove(myAccessory)
                }
            }
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