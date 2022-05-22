package com.example.recyclerviewkotlinblogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewkotlinblogs.adapters.RecyclerViewAdapter
import com.example.recyclerviewkotlinblogs.databinding.ActivityMainBinding
import com.example.recyclerviewkotlinblogs.models.BlogPost

class HomeActivity : AppCompatActivity(), RecyclerViewAdapter.BlogViewHolder.BlogPostListener {

    private val TAG = "HomeActivity"
    private lateinit var blogPostAdapter : RecyclerViewAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var blogPostData: List<BlogPost>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        addDataSet()
        blogPostAdapter.submitBlogPostListener(this)
    }

    private fun initRecyclerView() {
        binding.parentLayout.apply {
//            layoutManager = GridLayoutManager(this@HomeActivity, 2)
            layoutManager = LinearLayoutManager(this@HomeActivity)
            blogPostAdapter = RecyclerViewAdapter()
            addItemDecoration(TopSpacingItemDecoration(30))
            adapter = blogPostAdapter
        }
    }

    private fun addDataSet() {
        blogPostData = DataSource.createDataSet()
        Log.d(TAG, "Data Set Size is  : ${blogPostData.size}")
        blogPostAdapter.submitBlogList(blogPostData as ArrayList<BlogPost> /* = java.util.ArrayList<com.example.recyclerviewkotlinblogs.models.BlogPost> */)
    }

    override fun onBlogPostClicked(position: Int) {
        val blogPost = blogPostData[position]
        Log.d(TAG, "onBlogPostClicked- : ${blogPost.title}")
        Toast.makeText(binding.root.context, blogPost.title , Toast.LENGTH_SHORT).show()
    }

}