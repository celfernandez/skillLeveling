package com.example.chalengejobreadiness

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chalengejobreadiness.API.APIServiceItem
import com.example.chalengejobreadiness.databinding.ActivityMainBinding
import com.example.chalengejobreadiness.recyclerView.ItemAdapter
import com.example.chalengejobreadiness.responses.ItemResponse.Body
import com.example.chalengejobreadiness.responses.ItemResponse.ResponseItem
import com.example.chalengejobreadiness.responses.ResponseTopTwenty
import com.example.chalengejobreadiness.responses.TopTwentyResponse.Item
import com.example.chalengejobreadiness.responses.ValidCategoryResponse.ValidCategory
import kotlinx.coroutines.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var binding : ActivityMainBinding
private lateinit var adapter : ItemAdapter
private val itemList = mutableListOf<Body>()

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenerSearchButton()

        /*Hides the action bar of the app*/
        supportActionBar?.hide()
    }

    /*@Param: itemList : MutableList<Body>
    *Initializes the recyclerView:
    *   -Creates an instance of adapter
    *   -Does the binding for the layout and the adapter
    */
    private fun setUpRecyclerView(itemList : MutableList<Body> ){
        adapter = ItemAdapter(itemList)
        with (binding) {
            itemsRV.layoutManager = LinearLayoutManager(this@MainActivity)
            itemsRV.adapter = adapter
        }
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

    /*When the  bar is clicked it shows the results  of the search on the main screen
    *hideKeyboard function is called
    */
    private fun listenerSearchButton (){
        binding.textContainer.setOnClickListener {
            searchItem(binding.textContainer.text.toString())
            hideKeyboad()
        }
    }

    /* @param: category : String -> category searched by the user
    * Uses coroutine to consume the API
    * 3 different functions are called
    * categoryId -> is a valid category returned based on what the user searched for itemId
    * In case any call gets an eror, it's showed on a log
    */
    @SuppressLint("LongLogTag")
    private fun searchItem(category: String){
        CoroutineScope(Dispatchers.IO).launch {

            val call1: Response<List<ValidCategory>> =
                getRetrofit().create(APIServiceItem::class.java).getValidCategory(
                    1, category.lowercase()
                )
            val response = call1.body()?.get(0)
            if (call1.isSuccessful) {
                    val id = response?.validCategory
                    if (id != null){
                        Log.d("valid category found", id)
                        val call2: Response<ResponseTopTwenty> = getRetrofit().create(APIServiceItem::class.java).getTopItems(id)
                        val response2 : List<Item>? = call2.body()?.content
                        if (call2.isSuccessful){
                            Log.d("top twenty found", call2.body().toString())
                            if (response2 != null) {
                                val listIds : List<String> = response2.toList().map{ "${it.id}" }
                                val itemsIdString=listIds.toString().replace(" ", "")
                                val items1 =itemsIdString.replace("[","")
                                val items2 =items1.replace("]","")
                                val call3: Response<List<ResponseItem>> = getRetrofit().create(APIServiceItem::class.java).getItemsFromAPI(items2)
                                if(call3.isSuccessful){
                                    val response3 = call3.body() as List<ResponseItem>
                                    val itemsDetailList: MutableList<Body> = response3.map { it.body } as MutableList<Body>
                                    runOnUiThread{
                                        setUpRecyclerView(itemsDetailList)
                                    }
                                }else{
                                    Log.d("error on multiget",call3.body().toString())
                                }
                            }
                        }else{
                            Log.d("couldn't find top twenty", call2.code().toString())
                        }
                    }
            }else{
                Log.d("couldn't find valid category",call1.code().toString())
            }
        }
    }

    /*It hides the keyboard
    */
    private fun hideKeyboad(){
        val hide = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        hide.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }
}