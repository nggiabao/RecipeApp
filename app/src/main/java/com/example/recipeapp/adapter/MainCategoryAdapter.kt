package com.example.recipeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codingwithme.recipeapp.entities.CategoryItems
import com.example.recipeapp.R
import com.example.recipeapp.entities.Recipes
import android.content.Context
import com.bumptech.glide.Glide

class MainCategoryAdapter:RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {

    var listener: OnItemClickListener? = null
    var ctx: Context? = null
    var arrMainCategory = ArrayList<CategoryItems>()
    class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tv_dish_name: TextView = view.findViewById(R.id.tv_dish_name)


    }

    fun setData(arrData : List<CategoryItems>) {
        arrMainCategory = arrData as ArrayList<CategoryItems>
    }

    fun setClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        return  RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_category,parent,false))
    }

    override fun getItemCount(): Int {
        return arrMainCategory.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.tv_dish_name.text = arrMainCategory[position].strcategory

        Glide.with(ctx!!).load(arrMainCategory[position].strcategorythumb).into(holder.itemView.findViewById(R.id.img_dish))

        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(arrMainCategory[position].strcategory)
        }
    }

    interface OnItemClickListener {
        fun onClicked(categoryName: String)
    }
}