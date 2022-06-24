package com.example.chalengejobreadiness.recyclerView

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.chalengejobreadiness.ItemResults
import com.example.chalengejobreadiness.R
import com.example.chalengejobreadiness.responses.ItemResponse.Body

class ItemAdapter (val items : List<Body>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
        holder.binding.itemSelected.setOnClickListener{
            val intent = Intent(holder.binding.root.context,ItemResults::class.java)
            intent.putExtra("information",currentItem)
            holder.binding.root.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}