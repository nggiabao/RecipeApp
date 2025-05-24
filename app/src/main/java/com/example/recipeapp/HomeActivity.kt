package com.example.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipeapp.adapter.MainCategoryAdapter
import com.example.recipeapp.adapter.SubCategoryAdapter
import com.example.recipeapp.entities.Recipes
import com.example.recipeapp.ui.theme.RecipeAppTheme

class HomeActivity : ComponentActivity() {
    var arrMainCategory = ArrayList<Recipes>()
    var arrSubCategory = ArrayList<Recipes>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val rv_main_category = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_main_category)
        val rv_sub_category = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_sub_category)

        // Test Data
        arrMainCategory.add(Recipes(1, "Beef"))
        arrMainCategory.add(Recipes(1, "Chicken"))
        arrMainCategory.add(Recipes(1, "Dessert"))
        arrMainCategory.add(Recipes(1, "Lamp"))

        mainCategoryAdapter.setData(arrMainCategory)

        arrSubCategory.add(Recipes(1, "Beef and mustart pie"))
        arrSubCategory.add(Recipes(1, "Chicken and mashroom hotpot"))
        arrSubCategory.add(Recipes(1, "Banana panacakes"))
        arrSubCategory.add(Recipes(1, "Kapsalon"))

        subCategoryAdapter.setData(arrSubCategory)

        rv_main_category.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_main_category.adapter = mainCategoryAdapter

        rv_sub_category.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_sub_category.adapter = subCategoryAdapter

    }
}