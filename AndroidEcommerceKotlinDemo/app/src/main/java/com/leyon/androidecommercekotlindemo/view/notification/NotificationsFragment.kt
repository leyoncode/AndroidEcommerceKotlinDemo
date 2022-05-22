package com.leyon.androidecommercekotlindemo.view.notification

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
import com.leyon.androidecommercekotlindemo.databinding.FragmentNotificationsBinding
import com.leyon.androidecommercekotlindemo.viewmodel.NotificationsViewModel

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    private lateinit var notificationsViewModel : NotificationsViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notificationsViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(NotificationsViewModel::class.java)


        val recyclerAdapter = NotificationLogRecyclerAdapter(requireContext())

        notificationsViewModel.getNotificationLogLiveData().observe(viewLifecycleOwner, Observer {
            recyclerAdapter.setList(it) //set updated list for recyclerview

            //save to json storage with new log
            notificationsViewModel.saveToJsonFile(it)
        })

        val recycler : RecyclerView = view.findViewById<RecyclerView>(R.id.notificationRecyclerView)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = recyclerAdapter
    }
}