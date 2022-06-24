package com.example.chalengejobreadiness

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.chalengejobreadiness.API.APIServiceItem
import com.example.chalengejobreadiness.SharedPreferences.PreferenceHelper
import com.example.chalengejobreadiness.SharedPreferences.PreferenceManager
import com.example.chalengejobreadiness.databinding.ItemInformationBinding
import com.example.chalengejobreadiness.responses.ItemResponse.Body
import com.example.chalengejobreadiness.responses.ItemResponse.ItemDescription
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ItemResults : AppCompatActivity() {

    lateinit var binding: ItemInformationBinding
    private val sharedPreference: PreferenceHelper by lazy { PreferenceManager(applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*Hides the action bar of the app*/
        supportActionBar?.hide()
        bindingInformation()
        listenerFavoriteButton()
        listenerShowFavoritesButton(binding.root)
    }

    /*Creates an instance of retrofit
     *base url: "https://api.mercadolibre.com/"
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mercadolibre.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*@Param: itemId : String
    * By using coroutines, it calls getItemDescription for an specific itemId
    * It binds the description of the item to the item_information.xml
    */
    private fun callItemDescription(itemId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val callDescription: Response<ItemDescription> =
                getRetrofit().create(APIServiceItem::class.java).getItemDescription(itemId)
            val response = callDescription.body()
            if (callDescription.isSuccessful) {
                runOnUiThread {
                    val itemInformation = response?.plainText
                    binding.itemDescriptionTV.text = itemInformation
                }
            } else {
                Log.d("description not found", "description null")
            }
        }
    }


    /*Does the binding between the item_information.xml and the information of the item selected
    * title - price - condition - picture
    * */
    private fun bindingInformation() {
        val dataItem = intent.getParcelableExtra<Body>("information")
        callItemDescription(dataItem?.id.toString())
        with(binding) {
            productNameTV.text = dataItem?.title
            productPriceTV.text = "$ " + dataItem?.price.toString()
            moreInformationTV.text = dataItem?.condition
            Picasso.get().load(dataItem?.picture).into(productImageIV)
        }
    }

    /*When the favorite button is pressed, the addToFavorite function es called
    */
    private fun listenerFavoriteButton() {
        val dataItem = intent.getParcelableExtra<Body>("information")
        val itemId = dataItem?.id

        binding.favoriteIV.setOnClickListener {
            if (itemId != null) {
                sharedPreference.setFavoriteId(itemId)
                addToFavorite(itemId, sharedPreference.getIsFavorite(itemId))
            }
        }
    }

    /*When the showFavorite button is pressed, a list with the favorite ids is printed
    * it also starts a new activity showing the favorite ids */
    private fun listenerShowFavoritesButton(view: View) {
        binding.favoriteListBT.setOnClickListener {
            val listFavorites = sharedPreference.showFavorites()
            val intent = Intent(this, Favorites::class.java).apply {}
            startActivity(intent)
        }
    }


    /*@Param: itemId: String , isFavorite: Boolean
    *If the itemId is not already in favorites, it adds it on favorites (Shared preferences)
    *If the itemId is already in favorites, it deletes it from favorites (shared preferences)
    * */
    @SuppressLint("LongLogTag")
    fun addToFavorite(itemId: String, isFavorite: Boolean) {
        val dataItem = intent.getParcelableExtra<Body>("information")
        val idForFav = dataItem?.id

        if (isFavorite) {
            sharedPreference.setIsFavorite(false)
            sharedPreference.clearPrefs(itemId)
            Toast.makeText(this, "$idForFav deleted", Toast.LENGTH_SHORT).show()
            Log.d("$idForFav favorite deleted", isFavorite.toString())
        } else {
            sharedPreference.setFavoriteId(itemId)
            sharedPreference.setIsFavorite(true)
            Toast.makeText(this, "$idForFav added", Toast.LENGTH_SHORT).show()
            Log.d("$idForFav added to favorites", isFavorite.toString())
        }
    }
}