package com.example.pcworkshop.screen.positions.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.employees.Employees
import com.example.pcworkshop.models.positions.Position
import com.example.pcworkshop.screen.employees.adapters.EmployeesAdapter

class PositionsAdapter(): RecyclerView.Adapter<PositionsAdapter.PositionsViewHolder>() {

    private var positionsList = emptyList<Position>()

    class PositionsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val positionName: TextView = itemView.findViewById(R.id.tvPositionName)

        fun bind(position: Position) {
            positionName.text = position.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PositionsViewHolder {
        return PositionsViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_position, parent, false))
    }

    override fun onBindViewHolder(holder: PositionsViewHolder, position: Int) {
        holder.bind(positionsList[position])
    }

    override fun getItemCount(): Int {
        return positionsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(positions: List<Position>?) {
        if (positions != null) {
            this.positionsList = positions
        }
        notifyDataSetChanged()
    }

}