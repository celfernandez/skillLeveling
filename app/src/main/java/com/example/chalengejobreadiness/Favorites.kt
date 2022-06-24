package com.example.chalengejobreadiness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.chalengejobreadiness.SharedPreferences.PreferenceHelper
import com.example.chalengejobreadiness.SharedPreferences.PreferenceManager
import com.example.chalengejobreadiness.databinding.ActivityFavoritesBinding
import com.example.chalengejobreadiness.responses.ItemResponse.Body
import kotlinx.android.synthetic.main.activity_favorites.view.*

class Favorites : AppCompatActivity() {

    private val sharedPreference: PreferenceHelper by lazy { PreferenceManager(applicationContext) }
    lateinit var binding : ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenerBackButton()

        /*Hides the action bar of the app*/
        supportActionBar?.hide()

        val arrayAdapter : ArrayAdapter<*>
        val listFavorites = sharedPreference.showFavorites()

        val favoriteList = binding.favoriteList

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,listFavorites)
        favoriteList.adapter = arrayAdapter
    }

    /*When the back button is pressed, the favorites activity is closed
    */
    private fun listenerBackButton(){
        binding.favoriteBackBT.setOnClickListener {
            finish()
        }
    }

}