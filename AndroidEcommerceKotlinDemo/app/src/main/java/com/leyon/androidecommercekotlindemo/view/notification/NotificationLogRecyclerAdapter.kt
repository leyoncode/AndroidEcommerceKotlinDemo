package com.leyon.androidecommercekotlindemo.view.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.model.storage.entity.NotificationLog

class NotificationLogRecyclerAdapter(val context: Context) : RecyclerView.Adapter<NotificationLogRecyclerAdapter.NotificationLogViewHolder>()  {

    private var notificationLogList = mutableListOf<NotificationLog>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationLogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.notification_item,
            parent,
            false
        )

        return NotificationLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationLogViewHolder, position: Int) {
        val notificationLog = notificationLogList[position]

        holder.logTextView.text = "${notificationLog.dateTime}\n${notificationLog.logMessage}"
    }

    override fun getItemCount(): Int {
        return notificationLogList.size
    }

    fun setList(notificationLogList: MutableList<NotificationLog>) {
        this.notificationLogList = notificationLogList
    }

    //view holder class
    class NotificationLogViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val logTextView : TextView = itemView.findViewById(R.id.notificationLogText)
    }
}