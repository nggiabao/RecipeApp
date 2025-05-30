package com.example.recipeapp

import android.os.Bundle
import android.widget.TextView
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
import com.codingwithme.recipeapp.entities.CategoryItems
import com.codingwithme.recipeapp.entities.MealsItems
import com.example.recipeapp.adapter.MainCategoryAdapter
import com.example.recipeapp.adapter.SubCategoryAdapter
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.entities.Recipes
import com.example.recipeapp.ui.theme.RecipeAppTheme
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {
    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealsItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    val rv_main_category = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_main_category)
    val rv_sub_category = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_sub_category)

    val tvCategory = findViewById<TextView>(R.id.tvCategory)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        getDataFromDb()

        mainCategoryAdapter.setClickListener(onClicked)



    }

    private val onClicked = object : MainCategoryAdapter.OnItemClickListener {
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private fun getDataFromDb() {
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).RecipeDao().getallCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()

                getMealDataFromDb(arrMainCategory[0].strcategory)
                mainCategoryAdapter.setData(arrMainCategory)
                rv_main_category.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                rv_main_category.adapter = mainCategoryAdapter
            }
        }
    }

    private fun getMealDataFromDb(categoryName: String) {
        tvCategory.text = "$categoryName Category"
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).RecipeDao().getSpecificMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealsItems>
                subCategoryAdapter.setData(arrSubCategory)

                rv_sub_category.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
                rv_sub_category.adapter = subCategoryAdapter

            }
        }
    }


}