package com.example.project.models

class SchemesTable(){

    var dept_id:Int=0
    var scheme_id:Int=0
    var scheme_name:String=""
    var scheme_type:String=""
    var scheme_info:String=""
    var scheme_benefits:String=""
    var gender:String=""
    var lower_age:Int=0
    var upper_age:Int=0
    var lower_income:Int=0
    var higher_income:Int=0
    var disability:String=""
    var caste:String=""

    constructor( dept_id:Int, scheme_id:Int,scheme_name:String,scheme_type:String,scheme_info:String, scheme_benefits:String,
                 gender:String, lower_age:Int, upper_age:Int, lower_income:Int, higher_income:Int, disability:String, caste:String)
    :this(){
        this.dept_id=dept_id
        this.scheme_id=scheme_id
        this.scheme_name=scheme_name
        this.scheme_type=scheme_type
        this.scheme_info=scheme_info
        this.scheme_benefits=scheme_benefits
        this.gender=gender
        this.lower_age=lower_age
        this.upper_age=upper_age
        this.lower_income=lower_income
        this.higher_income=higher_income
        this.disability=disability
        this.caste=caste
    }

}