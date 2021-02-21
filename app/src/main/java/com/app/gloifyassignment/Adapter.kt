package com.app.gloifyassignment

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class Adapter(private val list: ArrayList<Article>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        var image = view.findViewById<ImageView>(R.id.image)
        var title = view.findViewById<TextView>(R.id.title)
        var author = view.findViewById<TextView>(R.id.author)
        var date = view.findViewById<TextView>(R.id.date)
        var des = view.findViewById<TextView>(R.id.des)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lis=list[position]
        Log.d("LogTag", lis.title)
        Picasso.get().load(lis.urlToImage).fit().placeholder(R.mipmap.ic_launcher).into(holder.image)
        holder.title.text = lis.title
        holder.date.text = lis.publishedAt
        holder.author.text = lis.author
        holder.des.text = lis.description
        val d= lis.url
        holder.image.setOnClickListener(View.OnClickListener {
            val intent = Intent(holder.itemView.context, webview::class.java)
            intent.putExtra("url", d)
            startActivity(holder.itemView.context,intent,null)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layoutrecycler,
            parent,
            false
        )
        return ViewHolder(view)
    }
}

