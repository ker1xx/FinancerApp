package com.example.kursovaya2024.Utility

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kursovaya2024.Models.DateNameCardModel
import com.example.kursovaya2024.databinding.DateItemCardBinding

interface ItemClickListener {
 fun onClick(position: Int)
}

class DateRecyclerAdapter(private val dataList: ArrayList<DateNameCardModel>,
                          private val listener: ItemClickListener) :
RecyclerView.Adapter<DateRecyclerAdapter.ViewHolderClass>()  {


   lateinit var binding: DateItemCardBinding
    class ViewHolderClass(var view: DateItemCardBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolderClass {
     binding = DateItemCardBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
     return ViewHolderClass(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
     holder.view.dateCardTV.text = dataList[position].getDateName()

     holder.view.root.setOnClickListener{listener.onClick(position)}
    }

    override fun getItemCount() = dataList.size


}