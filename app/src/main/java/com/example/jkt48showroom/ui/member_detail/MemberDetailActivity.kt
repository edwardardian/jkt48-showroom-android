package com.example.jkt48showroom.ui.member_detail

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jkt48showroom.HomeActivity
import com.example.jkt48showroom.R
import com.example.jkt48showroom.data.model.Member
import com.example.jkt48showroom.ui.adapters.MemberDetailAdapter
import com.example.jkt48showroom.ui.member.MemberFragment

class MemberDetailActivity : AppCompatActivity() {
    private lateinit var memberDetailAdapter: MemberDetailAdapter
    private val memberDetailViewModel: MemberDetailViewModel by viewModels()
    private lateinit var imgMemberDetail: ImageView
    private lateinit var tvTitleMemberDetail: TextView
    private lateinit var btnBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_member_detail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val rvMemberDetail: RecyclerView = findViewById(R.id.rv_member_detail)
        rvMemberDetail.layoutManager = GridLayoutManager(this, 1)

        imgMemberDetail = findViewById(R.id.img_member_detail)
        tvTitleMemberDetail = findViewById(R.id.tv_title_member_detail)
        btnBack = findViewById(R.id.btn_back_detail)
        btnBack.setOnClickListener {
            finish()
        }

        // Initialize adapter
        memberDetailAdapter = MemberDetailAdapter(null)
        rvMemberDetail.adapter = memberDetailAdapter

        // Get data from intent
        val memberName = intent.getStringExtra("MEMBER_NAME")

        // Observe LiveData from ViewModel and update adapter
        memberDetailViewModel.filteredMember.observe(this, Observer { member ->
            if (member != null) {
                memberDetailAdapter.updateData(member)
                updateMemberDetailViews(member)
            }
        })

        // Fetch member data
        memberDetailViewModel.fetchMemberData(memberName.toString())
    }

    private fun updateMemberDetailViews(member: Member) {
        Glide.with(this)
            .load(member.image)
            .placeholder(R.drawable.baseline_image_24)
            .error(R.drawable.baseline_image_24)
            .into(imgMemberDetail)

        tvTitleMemberDetail.text = member.main_name
    }
}
