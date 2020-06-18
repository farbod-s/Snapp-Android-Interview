package com.snapp.cache.db

/**
 * Defines constants for the Vehicle Table
 */
object Const {

    const val DATABASE_NAME = "snapp.db"

    const val TABLE_NAME = "vehicles"

    const val QUERY_ALL_VEHICLES = "SELECT * FROM" + " " + TABLE_NAME

    const val DELETE_ALL_VEHICLES = "DELETE FROM" + " " + TABLE_NAME
}