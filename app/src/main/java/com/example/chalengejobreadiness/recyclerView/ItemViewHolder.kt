package com.example.chalengejobreadiness.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.chalengejobreadiness.databinding.ItemListBinding
import com.example.chalengejobreadiness.responses.ItemResponse.Body
import com.example.chalengejobreadiness.responses.ItemResponse.ResponseItem
import com.squareup.picasso.Picasso

class ItemViewHolder (view: View): RecyclerView.ViewHolder(view){
    val binding = ItemListBinding.bind(view)

    fun bind (item : Body){
        with(binding){
            itemNameTV.text = item.title
            itemPriceTV.text = "$ " + item.price.toString()
            itemInformationTV.text = item.condition.toString()
            Picasso.get().load(item.picture).into(binding.itemImageIV)
        }
    }
}