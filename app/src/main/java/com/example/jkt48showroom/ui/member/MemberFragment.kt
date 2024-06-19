package com.example.jkt48showroom.ui.member

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jkt48showroom.R
import com.example.jkt48showroom.ui.adapters.MemberAdapter
import com.example.jkt48showroom.ui.member_detail.MemberDetailActivity

class MemberFragment : Fragment() {

    private val memberViewModel: MemberViewModel by viewModels()
    private lateinit var memberAdapter: MemberAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_member, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMemberList: RecyclerView = view.findViewById(R.id.rv_member)
        rvMemberList.layoutManager = GridLayoutManager(context, 2)

        memberAdapter = MemberAdapter(mutableListOf(), mutableListOf()) { mainName ->
            val intent = Intent(activity, MemberDetailActivity::class.java)
            intent.putExtra("MEMBER_NAME", mainName)
            startActivity(intent)
        }
        rvMemberList.adapter = memberAdapter

        memberViewModel.membersImage.observe(viewLifecycleOwner, Observer { memberImages ->
            val memberNames = memberViewModel.membersName.value ?: emptyList()
            memberAdapter.updateData(memberImages, memberNames)
        })

        memberViewModel.membersName.observe(viewLifecycleOwner, Observer { memberNames ->
            val memberImages = memberViewModel.membersImage.value ?: emptyList()
            memberAdapter.updateData(memberImages, memberNames)
        })
    }
}
