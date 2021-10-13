package com.example.shofna.model

data class LoginModel (
    val success: Boolean? = null,
    val data: LoginData? = null,
)

data class LoginData (
    var userid: Int? = null,
    var roomid: Long? = null,
    var email: String? = null,
    var mobile: String? = null,
    var groupid: String? = null,
    var username: String? = null,
    var pass : String? = null,
    var token: String? = null,
    var message: String? = null,
    var id: Int? = null,
    var err:String? = null,
    var value: String?=null,
    var created: String?=null

)
