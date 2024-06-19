package com.example.jkt48showroom.ui.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jkt48showroom.R
import com.example.jkt48showroom.data.model.Member
import com.example.jkt48showroom.ui.member_detail.MemberDetailActivity

class MemberDetailAdapter(private var member: Member?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ROOM_INFO = 1
    private val VIEW_TYPE_MEMBER_BIO = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ROOM_INFO -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_member_room_info, parent, false)
                RoomInfoViewHolder(view)
            }
            VIEW_TYPE_MEMBER_BIO -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_member_bio, parent, false)
                MemberBioViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return if (member != null) 2 else 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = member ?: return

        when (holder) {
            is RoomInfoViewHolder -> {
                holder.memberRoomLevelDetail.text = item.room_level.toString()
                holder.memberCategoryDetail.text = item.genre_name
                holder.memberFollowerDetail.text = item.follower.toString()
            }
            is MemberBioViewHolder -> {
                holder.memberDescriptionDetail.text = item.description
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> VIEW_TYPE_ROOM_INFO
            1 -> VIEW_TYPE_MEMBER_BIO
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    class MemberBioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val memberDescriptionDetail: TextView = itemView.findViewById(R.id.tv_member_bio)
    }

    class RoomInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val memberFollowerDetail: TextView = itemView.findViewById(R.id.tv_room_followers)
        val memberRoomLevelDetail: TextView = itemView.findViewById(R.id.tv_room_level)
        val memberCategoryDetail: TextView = itemView.findViewById(R.id.tv_room_category)
    }


    fun updateData(newMember: Member) {
        member = newMember
        notifyDataSetChanged()
    }
}

