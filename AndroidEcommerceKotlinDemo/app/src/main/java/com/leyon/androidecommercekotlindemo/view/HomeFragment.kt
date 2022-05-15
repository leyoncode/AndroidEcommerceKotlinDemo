package com.leyon.androidecommercekotlindemo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.leyon.androidecommercekotlindemo.AddNewProductDialog
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.databinding.FragmentHomeBinding
import com.leyon.androidecommercekotlindemo.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private lateinit var homeViewModel : HomeViewModel

    private lateinit var addNewProductFAB : FloatingActionButton

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


        val recyclerAdapter = HomeRecyclerViewAdapter(requireContext(), homeViewModel)
        homeViewModel.getProductLiveData().observe(viewLifecycleOwner, Observer {
            recyclerAdapter.setList(it)
        })

        val recycler : RecyclerView = view.findViewById<RecyclerView>(R.id.homeRecyclerView)
        //recycler.layoutManager = LinearLayoutManager(context)
        recycler.layoutManager = GridLayoutManager(context,2)
        recycler.adapter = recyclerAdapter

        // FAB used to open dialog to add new product to room database
        addNewProductFAB = view.findViewById(R.id.addProductFAB)

        addNewProductFAB.setOnClickListener{
            showAddNewProductDialog(homeViewModel) //homeViewModel needed for database access
        }
    }

    private fun showAddNewProductDialog(homeViewModel: HomeViewModel) {
        val addDialog : AddNewProductDialog = AddNewProductDialog(homeViewModel)
        addDialog.show(childFragmentManager,"AddNewProductDialog")
    }
}