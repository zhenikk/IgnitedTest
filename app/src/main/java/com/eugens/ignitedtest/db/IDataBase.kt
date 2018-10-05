package com.eugens.ignitedtest.db

interface IDataBase {
    fun storeData(dbData: DbData)
    fun fetchData(): DbData?
}