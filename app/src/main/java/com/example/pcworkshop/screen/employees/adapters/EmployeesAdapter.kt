package com.example.pcworkshop.screen.employees.adapters

import android.annotation.SuppressLint
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.employees.Employees

class EmployeesAdapter(): RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder>() {

    private var employeesList = emptyList<Employees>()

    class EmployeesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvName: TextView = itemView.findViewById(R.id.tvEmployeeName)
        private val tvLastName: TextView = itemView.findViewById(R.id.tvEmployeeLastName)
        private val tvMiddleName: TextView = itemView.findViewById(R.id.tvEmployeeMiddleName)
        private val tvAddress: TextView = itemView.findViewById(R.id.tvEmployeeAddress)
        private val tvNumber: TextView = itemView.findViewById(R.id.tvEmployeeNumber)
        private val tvEmail: TextView = itemView.findViewById(R.id.tvEmployeeEmail)
        private val tvPassword: TextView = itemView.findViewById(R.id.tvEmployeePassword)
        private val tvPosition: TextView = itemView.findViewById(R.id.tvEmployeePosition)


        fun bind(employee: Employees) {
            tvName.text = employee.firstName
            tvLastName.text = employee.lastName
            tvMiddleName.text = employee.middleName
            tvAddress.text = employee.address
            tvNumber.text = employee.phoneNumber
            tvEmail.text = employee.email
            tvPassword.text = employee.password
            tvPosition.text = employee.position.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        return EmployeesViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_employee, parent, false))
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