package com.app.gloifyassignment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var recycler: RecyclerView? = null
    lateinit var dataList: List<Article>
    lateinit var adapter:Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataList = ArrayList<Article>()
        recycler= findViewById(R.id.recycler)
        recycler?.layoutManager = LinearLayoutManager(this)
        Handler(Looper.getMainLooper()).post {
            fetchdata()
        }
    }
    private fun fetchdata(){
        val client: OkHttpClient = OkHttpClient().newBuilder()
                .build()
        val request: Request = Request.Builder()
                .url("http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=c80c41f2e44048ac86c0a662f0b9011b")
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }
            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                val gson = GsonBuilder().serializeNulls().create()
                val parcedjson = gson.fromJson(json, Dataitem::class.java)
                dataList= parcedjson.articles
                Handler(Looper.getMainLooper())
                        .post {
                            adapter = Adapter(dataList as ArrayList<Article>)
                            recycler?.adapter = adapter
                        }
            }
        })
    }
}
data class Dataitem(
        val articles: List<Article>,
        val status: String,
        val totalResults: Int
)

data class Article(
        val author: String,
        val description: String,
        val publishedAt: String,
        val title: String,
        val url: String,
        val urlToImage: String
)

data class Source(
        val id: Any,
        val name: String
)


