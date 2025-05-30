package com.example.recipeapp.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codingwithme.recipeapp.entities.CategoryItems
import com.codingwithme.recipeapp.entities.MealsItems
import com.example.recipeapp.entities.Category
import com.example.recipeapp.entities.Meal
import com.example.recipeapp.entities.Recipes

@Dao
interface RecipeDao {


    @Query("SELECT * FROM categoryitems ORDER BY id DESC")
    suspend fun getallCategory() : List<CategoryItems>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryItems: CategoryItems?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(mealsItems: MealsItems?)

    @Query("DELETE FROM categoryitems")
    suspend fun clearDb()

    @Query("SELECT * FROM MealItems WHERE categoryName = :categoryName ORDER BY id DESC")
    suspend fun getSpecificMealList(categoryName: String) : List<MealsItems>
}