package com.example.recyclerviewmultipletype

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewmultipletype.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        binding.progressBar.isVisible = true
        getPosts()
        getImages()
        binding.progressBar.isVisible = false
    }


    private fun getPosts() {
        lifecycleScope.launchWhenCreated {

            Log.v(TAG, "sent request")
            val postFetchResponse = try {
                RetrofitInstance.api.getPosts()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.handleError(), Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            }
            Log.d("InterceptorLog", "onCreate: ${postFetchResponse.raw().request.headers}")
            Log.v(TAG, "success request")
            if (postFetchResponse.isSuccessful && postFetchResponse.body() != null) {
                postAdapter.posts = postFetchResponse.body()!!
            } else {
                Log.e(TAG, "postFetchResponse not successful")
            }
        }
    }

    private fun getImages() {
        lifecycleScope.launchWhenCreated {
            Log.v(TAG, "sent request")
            val imageFetchResponse = try {
                RetrofitInstance.api.getImages()
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, e.handleError(), Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            }
            Log.d("InterceptorLog", "onCreate: ${imageFetchResponse.raw().request.headers}")
            Log.v(TAG, "success request")
            if (imageFetchResponse.isSuccessful && imageFetchResponse.body() != null) {
                postAdapter.images = imageFetchResponse.body()!!
            } else {
                Log.e(TAG, "postFetchResponse not successful")
            }
        }
    }

    private fun setupRecyclerView() = binding.postList.apply {
        postAdapter = PostAdapter(LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false))
        adapter = postAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}