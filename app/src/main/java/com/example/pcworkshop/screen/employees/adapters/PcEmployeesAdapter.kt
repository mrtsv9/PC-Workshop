package com.example.pcworkshop.screen.employees.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.employees.Employees
import com.example.pcworkshop.screen.pc.PcAddingFragment.Companion.employeeChecker
import com.example.pcworkshop.screen.pc.PcAddingFragment.Companion.tempEmployeeEmail

class PcEmployeesAdapter(): RecyclerView.Adapter<PcEmployeesAdapter.EmployeesViewHolder>() {

    private var employeesList = emptyList<Employees>()

    class EmployeesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvName: TextView = itemView.findViewById(R.id.tvPcAddingEmployeeName)
        private val tvLastName: TextView = itemView.findViewById(R.id.tvPcAddingEmployeeLastName)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvPcAddingEmployeeEmail)
        private val ivCheckmark: ImageView = itemView.findViewById(R.id.imageView3)

        fun bind(employee: Employees) {
            tvName.text = employee.firstName
            tvLastName.text = employee.lastName
            tvEmail.text = employee.email


            itemView.setOnClickListener {
                if(!itemView.isSelected && !employeeChecker) {
                    itemView.isSelected = true
                    ivCheckmark.visibility = View.VISIBLE
                    tempEmployeeEmail = employee.email
                    employeeChecker = true
                }
                else if (itemView.isSelected) {
                    itemView.isSelected = false
                    ivCheckmark.visibility = View.GONE
                    tempEmployeeEmail = ""
                    employeeChecker = false
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        return EmployeesViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_employee_for_pc, parent, false))
    }

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
        holder.bind(employeesList[position])
    }

    override fun getItemCount(): Int {
        return employeesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(employees: List<Employees>?) {
        if (employees != null) {
            this.employeesList = employees
        }
        notifyDataSetChanged()
    }

}