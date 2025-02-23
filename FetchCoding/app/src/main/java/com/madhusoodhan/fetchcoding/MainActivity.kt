package com.madhusoodhan.fetchcoding

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(android.R.id.text1)
    private val subtitle: TextView = view.findViewById(android.R.id.text2)

    fun bind(item: Item, position: Int) {
        title.text = "List ID: ${item.listId}"
        subtitle.text = "${item.name}"

        if (position % 2 == 0) {
            itemView.setBackgroundColor(Color.parseColor("#ffffff"))
        } else {
            itemView.setBackgroundColor(Color.parseColor("#D8BFD8"))
        }
    }

}


class ItemAdapter(private var items: List<Item>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_2, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }
}



class MainActivity : ComponentActivity() {
    private val viewModel: FetchViewModel by viewModels()
    private lateinit var adapter: ItemAdapter
    private var isAscending = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val CircleButton: Button = findViewById(R.id.circleButton)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.items.observe(this) { items ->
            adapter = ItemAdapter(items.sortedBy { it.listId })
            recyclerView.adapter = adapter

        }

        CircleButton.setOnClickListener{
            isAscending = !isAscending
            val sortedItems = if (isAscending) {
                viewModel.items.value?.sortedBy { it.listId }
            } else {
                viewModel.items.value?.sortedByDescending { it.listId }
            }
            sortedItems?.let { adapter.updateItems(it) }
        }
    }
}

