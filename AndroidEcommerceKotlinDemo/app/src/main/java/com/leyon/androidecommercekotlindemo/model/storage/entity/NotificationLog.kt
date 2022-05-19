package com.leyon.androidecommercekotlindemo.model.storage.entity

import kotlinx.serialization.Serializable


@Serializable
data class NotificationLog(val dateTime : String, val logMessage : String)