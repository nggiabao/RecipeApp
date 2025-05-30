package com.example.recipeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.codingwithme.recipeapp.entities.MealsItems
import com.example.recipeapp.database.RecipeDatabase
import com.example.recipeapp.entities.Category
import com.example.recipeapp.entities.Meal
import com.example.recipeapp.interfaces.GetDataService
import com.example.recipeapp.retrofitclient.RetrofitClientInstance
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.launch


class SplashActivity : BaseActivity(), EasyPermissions.RationaleCallbacks, EasyPermissions.PermissionCallbacks {
    private var READ_STORAGE_PERM = 123
    val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)
    val loader = findViewById<View>(R.id.loader)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        readStorageTask()

        btnGetStarted.setOnClickListener {
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun getCategories() {
        val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
        val call = service.getCategoryList()

        call.enqueue(object : Callback<Category> {
            override fun onResponse(call: Call<Category>, response: Response<Category>) {

                for (arr in response.body()!!.categorieitems!!) {
                    getMeal(arr.strcategory)
                }
                insertDataIntoRoomDb(response.body())
            }

            override fun onFailure(call: Call<Category>, t: Throwable) {

                Toast.makeText(this@SplashActivity, "Something went would", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getMeal(categoryName:String) {
        val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
        val call = service.getMealList(categoryName)

        call.enqueue(object : Callback<Meal> {

            override fun onResponse(call: Call<Meal>, response: Response<Meal>) {
                insertMealIntoRoomDb(categoryName.response.body())
            }

            override fun onFailure(call: Call<Meal>, t: Throwable) {
                loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashActivity, "Something went would", Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun insertDataIntoRoomDb(category: Category?) {
        // Implementation for inserting data into Room database would go here.
        launch {
            this.let {
                for (arr in category!!.categorieitems!!) {
                    RecipeDatabase.getDatabase(this@SplashActivity)
                        .RecipeDao().insertCategory(arr)
                }
            }
        }


    }

    fun insertMealIntoRoomDb(categoryName: String, meal: Meal?) {
        // Implementation for inserting data into Room database would go here.
        launch {
            this.let {

                for (arr in meal!!.mealsItem!!) {
                    var mealItemModel = MealsItems(arr.id, arr.idMeal, arr.strMeal, categoryName, arr.strMealThumb)
                    RecipeDatabase.getDatabase(this@SplashActivity)
                        .RecipeDao().insertMeal(arr)
                }

                btnGetStarted.visibility = View.VISIBLE
            }
        }
    }

    fun clearDatabase() {
        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashActivity).RecipeDao().clearDb()
            }
        }
    }

    private fun hasReadStoragePermission(): Boolean {
        return EasyPermissions.hasPermissions(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private fun readStorageTask(){
        if (hasReadStoragePermission()) {
            clearDatabase()
            getCategories()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your storage to function properly.",
                READ_STORAGE_PERM,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onRationaleAccepted(requestCode: Int) {

    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }
}