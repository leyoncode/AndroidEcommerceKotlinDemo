package com.leyon.androidecommercekotlindemo.model.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.leyon.androidecommercekotlindemo.model.storage.entity.NotificationLog
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import java.io.File
import java.time.LocalDateTime

object NotificationLogRepository {

    private const val filename = "NotificationLogsJson.json" //file stored with this name

    val notificationLogLiveData = MutableLiveData<MutableList<NotificationLog>>()

    fun addNotificationLog(notificationLog: NotificationLog) {
        notificationLogLiveData.value?.add(notificationLog)
    }

    @Synchronized
    fun saveToJsonFile(context : Context, notificationLogList: List<NotificationLog>) {
        try {
            //convert serializable NotificationLog list to json
            val jsonString : String = Json.encodeToString(notificationLogList)

            val f : File = File(context.filesDir, filename)

            f.writeText(jsonString)
        } catch (e : Exception) {
            Log.e("ErrorSaveFile",e.message.toString())
        }
    }

    @Synchronized
    fun loadFromJsonFile(context : Context) : MutableList<NotificationLog> {

        try {
            val f : File = File(context.filesDir, filename)
            val jsonString = f.readText()

            val notificationLog : MutableList<NotificationLog> = Json.decodeFromString(jsonString)

            return notificationLog

        } catch (e : Exception) {
            Log.e("ErrorLoadFile",e.message.toString())
        }

        return mutableListOf() //default to empty list if file not found or other error
    }

    @Synchronized
    fun deleteJsonFile(context : Context) {
        try {
            val f : File = File(context.filesDir, filename)
            if (f.exists()) {
                f.delete()
            }
        } catch (e : Exception) {
            Log.e("ErrorLoadFile",e.message.toString())
        }
    }

    fun getDateTime(): LocalDateTime {
        return LocalDateTime.now()
    }

    fun loadDataFromJsonIntoLiveData(context : Context) {
        this.notificationLogLiveData.value = loadFromJsonFile(context)
    }
}