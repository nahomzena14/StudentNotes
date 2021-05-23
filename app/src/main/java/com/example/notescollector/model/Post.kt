package com.example.notescollector.model

data class Post(val user:String,val subject:String,val noteContent:String) {
    constructor(): this("", "", "")
}