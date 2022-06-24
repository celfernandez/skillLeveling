package com.example.chalengejobreadiness.SharedPreferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast

open class PreferenceManager constructor(context: Context) : PreferenceHelper {
    private val PREFS_NAME = "SharedPreferenceDemo"
    private var preferenceId: SharedPreferences


    init {
        preferenceId = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /*@Param: itemId : String
    *Given an specific itemId, it is added to favorites
    */
    override fun setFavoriteId(itemId: String) {
        preferenceId.edit().putString("$itemId" , itemId).apply()
    }

    /*@Param: isFavorite : Boolean
    *it sets true or false to the item
    */
    override fun setIsFavorite(isFavorite: Boolean){
        preferenceId.edit().putBoolean("isFavorite", isFavorite).apply()
    }

    /*returns the id of the favorite */
    override fun getFavoriteId(): String {
        return (preferenceId.getString(ITEM_ID, "0") ?: 0) as String
    }

    /*@Param: itemId : String
    *it returns true of false depending in whether the item if favorite or not
    */
    override fun getIsFavorite(itemId: String): Boolean {
        return (preferenceId.getBoolean("isFavorite",false))
    }

    /*@Param: itemId : String
    *it deletes the id and the isFavorite atribute of an item
    */
    override fun clearPrefs(itemId: String) {
        preferenceId.edit().remove(itemId).apply()
        preferenceId.edit().remove("isFavorite")
    }

    /*It returns a list with the itemId that are saved as favorites
    */
    override fun showFavorites() : List<String>{
        val favoriteIdList = preferenceId.all.map { it.key }
        Log.d("favorites:",favoriteIdList.toString())
        return favoriteIdList
    }

    val sharedPreferences = context.getSharedPreferences("SHARED_PREFERENCES", Context.MODE_PRIVATE)

    companion object {
        const val ITEM_ID = "itemId"
    }
}