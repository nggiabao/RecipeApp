package com.codingwithme.recipeapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MealItems")
data class MealsItems(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "idMeal")
    @Expose
    @SerializedName(value = "idMeal")
    val idMeal: String,

    @ColumnInfo(name = "categoryName")
    val categoryName: String,

    @ColumnInfo(name = "strMeal")
    @Expose
    @SerializedName(value = "strMeal")
    val strMeal: String,

    @ColumnInfo(name = "strMealThumb")
    @Expose
    @SerializedName(value = "strMealThumb")
    val strMealThumb: String
)