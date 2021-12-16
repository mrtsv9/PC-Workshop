package com.example.pcworkshop.screen.pc.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pcworkshop.R
import com.example.pcworkshop.models.accessories.Accessories
import com.example.pcworkshop.models.clients.Clients
import com.example.pcworkshop.models.employees.Employees
import com.example.pcworkshop.models.orders.Orders
import com.example.pcworkshop.models.pc.Pc
import com.example.pcworkshop.models.pc_accessories.PcAccessories
import com.example.pcworkshop.screen.accessories.adapters.AccessoriesAdapter

class PcAdapter(): RecyclerView.Adapter<PcAdapter.PcViewHolder>() {

    private var pcList = emptyList<Pc>()
    private var ordersList = emptyList<Orders>()
    private var clientsList = emptyList<Clients>()
    private var employeesList = emptyList<Employees>()
    private var pcAccessoriesList: MutableList<PcAccessories> = emptyList<PcAccessories>().toMutableList()
    private var accessoriesList: MutableList<Accessories> = emptyList<Accessories>().toMutableList()
    private var myAccessoriesList: MutableList<Accessories> = emptyList<Accessories>().toMutableList()

    private val viewPool = RecyclerView.RecycledViewPool()

    class PcViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvTitle: TextView = itemView.findViewById(R.id.tvPcTitle)
        private val tvClientName: TextView = itemView.findViewById(R.id.tvPcClientName)
        private val tvClientLastName: TextView = itemView.findViewById(R.id.tvPcClientLastName)
        private val tvOrderId: TextView = itemView.findViewById(R.id.tvPcOrderId)
        private val tvOrderType: TextView = itemView.findViewById(R.id.tvPcOrderType)
        private val tvEmployeeLastName: TextView = itemView.findViewById(R.id.tvPcEmployeeLastName)
        private val tvEmployeeName: TextView = itemView.findViewById(R.id.tvPcEmployeeName)
        private val tvEmployeeMiddleName: TextView = itemView.findViewById(R.id.tvPcEmployeeMiddleName)
        val rvAccessories: RecyclerView = itemView.findViewById(R.id.rvAccessories)

        lateinit var client: Clients
        lateinit var employee: Employees

        fun bind(computer: Pc,
                 clients: List<Clients>,
                 orders: List<Orders>,
                 employees: List<Employees>) {

            var tempClient: Clients? = null
//            client = clients[orders[computer.orderId - 1].clientId -1]
            orders.forEach { order ->
                if( order.orderId == computer.orderId) {
                    clients.forEach { client ->
                        if (client.clientId == order.clientId) {
                            tempClient = client
                        }
                    }
                }
            }
            var employee: Employees? = null
            employees.forEach {
                if (it.employeeId == computer.employeeId) {
                    employee = it
                }
            }
//            employee = employees[computer.employeeId - 1]
            tvTitle.text = computer.title
            tvClientName.text = tempClient?.firstName
            tvClientLastName.text = tempClient?.lastName
            tvOrderId.text = computer.orderId.toString()
            tvOrderType.text = computer.assembly.type
            tvEmployeeName.text = employee?.firstName
            tvEmployeeLastName.text = employee?.lastName
            tvEmployeeMiddleName.text = employee?.middleName

//            val childLayoutManager = LinearLayoutManager(rvAccessories.context, LinearLayoutManager.VERTICAL, false)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PcViewHolder {
        return PcViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pc, parent, false))
    }

    override fun onBindViewHolder(holder: PcViewHolder, position: Int) {
        holder.bind(pcList[position],
            clientsList,
            ordersList,
            employeesList)

        myAccessoriesList.clear()
        pcAccessoriesList.forEach {
            if (it.pcId == pcList[position].pcId) {
                myAccessoriesList.add(accessoriesList[it.accessoryId - 1])
//                accessoriesList.removeAt(it.accessoryId)
//                return
            }
        }


        val childLayoutManager = LinearLayoutManager(holder.rvAccessories.context, LinearLayoutManager.VERTICAL, false)
        holder.rvAccessories.apply {
            layoutManager = childLayoutManager
            adapter = AccessoriesAdapter(myAccessoriesList)
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        return pcList.size
    }

//    fun setRvAccessories(recyclerView: RecyclerView) {
//
//        val itemRvAdapter = AccessoriesAdapter(context)
//        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        recyclerView.adapter = itemRvAdapter
//    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(
        computers: List<Pc>?,
        clients: List<Clients>,
        orders: List<Orders>,
        employees: List<Employees>,
        pcAccessories: List<PcAccessories>,
        accessories: List<Accessories>) {
        if (computers != null) {
            this.pcList = computers
            this.clientsList = clients
            this.ordersList = orders
            this.employeesList = employees
            this.pcAccessoriesList = pcAccessories.toMutableList()
            this.accessoriesList = accessories.toMutableList()
        }
        notifyDataSetChanged()
    }

}