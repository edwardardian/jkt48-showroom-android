package com.example.jkt48showroom.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jkt48showroom.R
import com.example.jkt48showroom.data.model.Member

class RecentLivesAdapter(private var imageList: List<Member>) : RecyclerView.Adapter<RecentLivesAdapter.RecentLivesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentLivesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recent_lives, parent, false)
        return RecentLivesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentLivesViewHolder, position: Int) {
        val imageUrl = imageList[position]
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.imgLiveMember)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun updateData(newImageList: List<Member>) {
        imageList = newImageList
        notifyDataSetChanged()
    }

    class RecentLivesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLiveMember: ImageView = itemView.findViewById(R.id.img_live_member_recent)
    }
}
