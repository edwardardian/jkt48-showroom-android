package com.example.jkt48showroom.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jkt48showroom.R
import com.example.jkt48showroom.ui.adapters.MemberAdapter
import com.example.jkt48showroom.ui.adapters.RecentLivesAdapter
import com.example.jkt48showroom.ui.adapters.ShowroomLivesAdapter

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var showroomLivesAdapter: ShowroomLivesAdapter
    private lateinit var recentLivesAdapter: RecentLivesAdapter
    private lateinit var memberAdapter: MemberAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Showroom Lives
        val rvShowroomLives: RecyclerView = view.findViewById(R.id.rv_showroom_live)

        rvShowroomLives.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false)
        showroomLivesAdapter = ShowroomLivesAdapter(mutableListOf())
        rvShowroomLives.adapter = showroomLivesAdapter

        homeViewModel.showroomLives.observe(viewLifecycleOwner, Observer { imageList ->
            showroomLivesAdapter.updateData(imageList)
        })

        // Recent Lives
        val rvRecentLives: RecyclerView = view.findViewById(R.id.rv_recent_live)
        rvRecentLives.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false)
        recentLivesAdapter = RecentLivesAdapter(mutableListOf())
        rvRecentLives.adapter = recentLivesAdapter

        homeViewModel.recentLives.observe(viewLifecycleOwner, Observer { imageList ->
            recentLivesAdapter.updateData(imageList)
        })

    }
}
