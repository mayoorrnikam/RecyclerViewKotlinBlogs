package com.example.recyclerviewkotlinblogs.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewkotlinblogs.R
import com.example.recyclerviewkotlinblogs.databinding.BlogPostItemBinding
import com.example.recyclerviewkotlinblogs.models.BlogPost

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = "RecyclerViewAdapter"

    private lateinit var blogItems: ArrayList<BlogPost>

    private lateinit var binding: BlogPostItemBinding
    private lateinit var blogPostListener: BlogViewHolder.BlogPostListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return BlogViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.blog_post_item, parent, false)
//        )

        binding =
            BlogPostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogViewHolder(binding.root, blogPostListener)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BlogViewHolder -> {
                holder.bind(blogItems[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return blogItems.size
    }

    fun submitBlogList(blogPostItems: ArrayList<BlogPost>) {
        Log.d(TAG, "Data Set Size is  : ${blogPostItems.size}")
        blogItems = blogPostItems
    }

    fun submitBlogPostListener(blogPostListener: BlogViewHolder.BlogPostListener) {
        Log.d(TAG, "BlogPostListener submitted: ")
        this.blogPostListener = blogPostListener
    }

    class BlogViewHolder(
        var blogPostItemView: View,
        var blogPostClickListener: BlogPostListener
    ) : RecyclerView.ViewHolder(blogPostItemView), View.OnClickListener {
        private val TAG = "RecyclerViewAdapter.BlogViewHolder"

        private val imageView: ImageView = blogPostItemView.findViewById(R.id.blog_image)
        private val title: TextView = blogPostItemView.findViewById(R.id.blog_title)
        private val body: TextView = blogPostItemView.findViewById(R.id.blog_body)



        fun bind(blogPost: BlogPost) {
            title.text = blogPost.title
            body.text = blogPost.body

            Glide.with(blogPostItemView.context)
                .load(blogPost.image)
                .into(imageView)

            blogPostItemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Log.d(TAG, "BlogViewHolder onClick ")
            blogPostClickListener.onBlogPostClicked(absoluteAdapterPosition)
        }

        interface BlogPostListener {
            fun onBlogPostClicked(position: Int)
        }
    }


}