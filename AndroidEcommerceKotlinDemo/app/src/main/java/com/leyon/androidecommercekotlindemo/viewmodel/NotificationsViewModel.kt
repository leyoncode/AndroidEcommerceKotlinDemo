package com.leyon.androidecommercekotlindemo.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leyon.androidecommercekotlindemo.model.repository.NotificationLogRepository
import com.leyon.androidecommercekotlindemo.model.storage.entity.NotificationLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var notificationLogLiveData : MutableLiveData<MutableList<NotificationLog>>

    init {
        NotificationLogRepository.loadDataFromJsonIntoLiveData(application.applicationContext)
        this.notificationLogLiveData = NotificationLogRepository.notificationLogLiveData
    }

    fun getNotificationLogLiveData(): MutableLiveData<MutableList<NotificationLog>> {
        return notificationLogLiveData
    }

    fun loadFromJsonFile() : MutableList<NotificationLog> {
        return NotificationLogRepository.loadFromJsonFile(this.getApplication<Application>().applicationContext)
    }

    fun saveToJsonFile(notificationLogList: List<NotificationLog>) {
        val context = this.getApplication<Application>().applicationContext
        CoroutineScope(Dispatchers.IO).launch {
            NotificationLogRepository.saveToJsonFile(context, notificationLogList)
        }
    }
}