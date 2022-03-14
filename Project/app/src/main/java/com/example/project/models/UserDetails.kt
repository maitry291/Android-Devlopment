package com.example.project.models

class UserDetails() {
    lateinit var name:String
    lateinit var email:String
    lateinit var password:String

    constructor(name:String,email:String,password:String):this(){
        this.name=name
        this.email=email
        this.password=password
    }
}