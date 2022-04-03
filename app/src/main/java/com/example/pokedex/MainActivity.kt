package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pokedex.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var queue: RequestQueue
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        queue = Volley.newRequestQueue(this)
        binding.btnUpdate.setOnClickListener{
            if (binding.etPokeNumber.text.toString() == ""){
                Toast.makeText(this, "Ingrese un numero primero", Toast.LENGTH_LONG).show()
            }
            else{
                getPokeList(binding.etPokeNumber.text.toString().toInt())
            }
        }
        getPokeList(5)

    }
    fun getPokeList(listAmount: Int){
        val url = "https://pokeapi.co/api/v2/pokemon/?limit=${listAmount}"
        val jsonRequest = JsonObjectRequest(url, Response.Listener<JSONObject>{ response ->
            Log.i("JSONRESPONSE", response.getJSONArray("results").toString())
        },
        Response.ErrorListener { error ->
            Log.w("JSONRESPONSE", error.message as String)
        })
        queue.add(jsonRequest)
    }

    override fun onStop(){
        super.onStop()
        queue.cancelAll("stopped")
    }
}