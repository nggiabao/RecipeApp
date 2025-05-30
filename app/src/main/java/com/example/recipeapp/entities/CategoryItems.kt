package com.codingwithme.recipeapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CategoryItems")
data class CategoryItems(
    @PrimaryKey(autoGenerate = true)
    var id:Int,

    @ColumnInfo(name = "idcategory")
    @Expose
    @SerializedName(value = "idCategory")
    val idcategory: String,

    @ColumnInfo(name = "strcategory")
    @Expose
    @SerializedName(value = "strCategory")
    val strcategory: String,

    @ColumnInfo(name = "strcategorythumb")
    @Expose
    @SerializedName(value = "strCategoryThumb")
    val strcategorythumb: String,

    @ColumnInfo(name = "strcategorydescription")
    @Expose
    @SerializedName(value = "strCategoryDescription")
    val strcategorydescription: String
)