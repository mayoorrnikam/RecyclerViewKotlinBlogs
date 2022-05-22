package com.example.recyclerviewkotlinblogs.models

class BlogPost(
    var title: String,
    var body: String,
    var image: String,
    var userName: String

) {
    override fun toString(): String {
        return "BlogPost(title='$title', image = '$image', userName = '$userName')"
    }
}