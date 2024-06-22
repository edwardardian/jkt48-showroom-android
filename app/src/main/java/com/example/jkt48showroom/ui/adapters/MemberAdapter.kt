package com.example.jkt48showroom.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jkt48showroom.R
import com.example.jkt48showroom.data.model.Member

class MemberAdapter(private var imageList: List<String>, private var nameList: List<String>,  private val listener: (String) -> Unit) : RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_member_list, parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val imageUrl = imageList[position]
        val name = nameList[position]

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.imgMemberList)

        holder.nameMemberList.text = name
        holder.itemView.setOnClickListener {
            listener(name)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun updateData(newImageList: List<String>, newNameList: List<String>) {
        imageList = newImageList
        nameList = newNameList
    }


    class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgMemberList: ImageView = itemView.findViewById(R.id.img_member_list)
        val nameMemberList: TextView = itemView.findViewById(R.id.tv_member_name)
    }
}

