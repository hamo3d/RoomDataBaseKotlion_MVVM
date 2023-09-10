package com.example.roomdatabasekotlion.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasekotlion.databinding.CusomRowItemBinding
import com.example.roomdatabasekotlion.interfaces.UpdateClickListener
import com.example.roomdatabasekotlion.model.User

class ListAdapter(
    private val users: ArrayList<User>,
    private val updateClick: UpdateClickListener
) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    class MyViewHolder(private val binding: CusomRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(user: User, updateClick: UpdateClickListener) {
            binding.idTextView.text = "${adapterPosition+1}"
            binding.nameTextView.text = user.firstName + " " + user.lastName
            binding.ageTextView.text = "(${user.age})"
            binding.roots.setOnClickListener { updateClick.update(user) }
            binding.delete.setOnClickListener {
                updateClick.delete(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            CusomRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setData(users[position], updateClick = updateClick)
    }
}


