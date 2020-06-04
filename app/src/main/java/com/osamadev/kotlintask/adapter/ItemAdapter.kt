package com.osamadev.kotlintask.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.osamadev.kotlintask.Interface.ItemClickListener
import com.osamadev.kotlintask.R
import com.osamadev.kotlintask.database.Item
import com.osamadev.kotlintask.ui.SecondActivity

class ItemAdapter(val list: List<Item>,private val context: Context): RecyclerView.Adapter<ItemViewHolder>() {

    private val inflater: LayoutInflater

    init{
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view:View = inflater.inflate(R.layout.list_item , parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val list: Item = list[position]
        holder.bind(list)

        holder.setItemClickListener(ItemClickListener{ View, position, isLongClick ->
            if(list.flag && list.id.toInt() == 0){
                val myIntent = Intent(holder.itemView.context, SecondActivity::class.java)
                myIntent.putExtra("id",list.id)
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                holder.itemView.context.startActivity(myIntent)
            }

        })

    }


    override fun getItemCount(): Int = list.size

}

class ItemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView),View.OnClickListener {

    var txtTitle: TextView? = null
    var txtDesc: TextView? = null
    var txtId: TextView? = null

    private var itemClickListener : ItemClickListener?=null

    init {
        txtTitle = itemView.findViewById(R.id.txt_title)
        txtDesc = itemView.findViewById(R.id.txt_desc)
        txtId = itemView.findViewById(R.id.txt_id)


        itemView.setOnClickListener(this)
    }

    fun setItemClickListener(itemClickListener : ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onClick(v: View?) {
        itemClickListener!!.onClick(v,adapterPosition,false)
    }

    fun bind(item: Item) {
        txtTitle?.text = item.title
        txtDesc?.text = item.description
        txtId?.text = item.id.toString()
    }

}

