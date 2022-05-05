package com.leyon.androidecommercekotlindemo.view

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.databinding.FragmentHomeBinding
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Product
import com.leyon.androidecommercekotlindemo.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var homeViewModel : HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(HomeViewModel::class.java)


        val recyclerAdapter = HomeRecyclerViewAdapter()
        homeViewModel.getProductLiveData().observe(viewLifecycleOwner, Observer {
            recyclerAdapter.setList(it)
        })

        val recycler : RecyclerView = view.findViewById<RecyclerView>(R.id.homeRecyclerView)
        //recycler.layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = GridLayoutManager(context,2)
        recycler.adapter = recyclerAdapter
    }
}