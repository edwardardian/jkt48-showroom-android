package com.example.jkt48showroom.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jkt48showroom.R
import com.example.jkt48showroom.data.model.Member
import com.example.jkt48showroom.ui.live.LiveActivity

class ShowroomLivesAdapter(private var imageList: List<Member>) : RecyclerView.Adapter<ShowroomLivesAdapter.ShowroomLivesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowroomLivesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_showroom_lives, parent, false)
        return ShowroomLivesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShowroomLivesViewHolder, position: Int) {
        val imageUrl = imageList[position]
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.imgLiveMember)

        holder.cvShowroomLives.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, LiveActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun updateData(newImageList: List<Member>) {
        imageList = newImageList
    }

    class ShowroomLivesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLiveMember: ImageView = itemView.findViewById(R.id.img_live_member_showroom)
        val cvShowroomLives: CardView = itemView.findViewById(R.id.cv_item_showroom_lives)
    }
}
