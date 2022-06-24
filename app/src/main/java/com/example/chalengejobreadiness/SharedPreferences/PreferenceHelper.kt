package com.example.chalengejobreadiness.SharedPreferences

interface PreferenceHelper {
    fun setFavoriteId(itemId : String)
    fun setIsFavorite(isFavorite : Boolean)
    fun getFavoriteId() : String
    fun getIsFavorite(itemId : String) : Boolean
    fun clearPrefs(itemId : String)
    fun showFavorites() : List<String>
}