package com.example.pcworkshop.screen.types_of_accessories

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.positions.Position
import com.example.pcworkshop.models.types_of_accessories.TypesOfAccessories
import com.example.pcworkshop.screen.positions.adapters.PositionsAdapter

class TypesAdapter(): RecyclerView.Adapter<TypesAdapter.TypesViewHolder>() {

    private var typesList = emptyList<TypesOfAccessories>()

    class TypesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val typeName: TextView = itemView.findViewById(R.id.tvTypeName)

        fun bind(type: TypesOfAccessories) {
            typeName.text = type.type
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypesViewHolder {
        return TypesViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_type, parent, false))
    }

    override fun onBindViewHolder(holder: TypesViewHolder, position: Int) {
        holder.bind(typesList[position])
    }

    override fun getItemCount(): Int {
        return typesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(types: List<TypesOfAccessories>?) {
        if (types != null) {
            this.typesList = types
        }
        notifyDataSetChanged()
    }

}