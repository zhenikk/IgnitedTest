package com.eugens.ignitedtest.db

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class MyDataBase(context: Context, val gson: Gson) : IDataBase {

    companion object {
        const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_NAME"
        const val SHARED_PREFERENCES_KEY = "SHARED_PREF_KEY"
    }

    private var sharedPrefs: SharedPreferences? = null

    init {
        sharedPrefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    override fun storeData(dbData: DbData) {
        val convertedStorage = gson.toJson(dbData)
        storeToAndroidStorage(convertedStorage)
    }

    override fun fetchData(): DbData? {
        val fetchedData = readFromAndroidStorage()
        return if (fetchedData != "") {
            gson.fromJson<DbData>(fetchedData, DbData::class.java)
        } else
            null
    }


    private fun storeToAndroidStorage(data: String) {
        val editor = sharedPrefs?.edit()
        editor?.putString(SHARED_PREFERENCES_KEY, data)
        editor?.apply()
    }

    private fun readFromAndroidStorage(): String? {
        return sharedPrefs?.getString(SHARED_PREFERENCES_KEY, "")
    }

}