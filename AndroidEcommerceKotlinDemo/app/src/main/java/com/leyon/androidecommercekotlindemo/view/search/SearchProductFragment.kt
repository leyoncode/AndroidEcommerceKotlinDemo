package com.leyon.androidecommercekotlindemo.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.databinding.FragmentSearchProductBinding
import com.leyon.androidecommercekotlindemo.viewmodel.SearchProductViewModel

class SearchProductFragment : Fragment() {

    private var _binding: FragmentSearchProductBinding? = null

    private lateinit var searchProductViewModel: SearchProductViewModel

    //for getting arguments passed during navigation. setup in navigation graph file
    private val navigationArgs : SearchProductFragmentArgs by navArgs<SearchProductFragmentArgs>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchProductBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchProductViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(SearchProductViewModel::class.java)


        val recyclerAdapter = SearchProductRecyclerViewAdapter(requireContext(), searchProductViewModel)


        //get string from navigation
        val searchString : String = "%" + navigationArgs.searchText + "%"

        searchProductViewModel.searchForProductWithString(searchString).observe(viewLifecycleOwner, Observer {
            recyclerAdapter.setList(it.toMutableList())
        })

        val recycler : RecyclerView = view.findViewById<RecyclerView>(R.id.searchFragmentRecyclerView)
        recycler.layoutManager = LinearLayoutManager(context)
        //recycler.layoutManager = GridLayoutManager(context,2)
        recycler.adapter = recyclerAdapter

    }

}