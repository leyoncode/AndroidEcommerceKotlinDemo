package com.leyon.androidecommercekotlindemo.view.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.databinding.FragmentDashboardBinding
import com.leyon.androidecommercekotlindemo.viewmodel.DashboardViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private lateinit var dashboardViewModel: DashboardViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(DashboardViewModel::class.java)

        val recyclerAdapter = DashboardRecyclerViewAdapter(requireContext(), dashboardViewModel)

        dashboardViewModel.getTransactionsLiveData().observe(viewLifecycleOwner, Observer {
            recyclerAdapter.setList(it)
        })

        val recycler : RecyclerView = view.findViewById<RecyclerView>(R.id.dashboardRecyclerView)
        recycler.layoutManager = LinearLayoutManager(context)
        //recycler.layoutManager = GridLayoutManager(context,2)
        recycler.adapter = recyclerAdapter
    }
}