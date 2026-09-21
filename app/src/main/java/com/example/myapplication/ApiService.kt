package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET

//  Definimos todas las estructuras de datos que viajan entre tu app y Node.js
data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String?, val message: String?)
data class Rutina(val id: Int, val name: String, val image_url: String?)
data class RegisterRequest(val nombre: String, val correo: String, val password: String)

// Definimos las rutas (endpoints) a las que se conecta la app
interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("routines")
    fun getRutinas(): Call<List<Rutina>>

    @POST("register")
    fun register(@Body request: RegisterRequest): Call<LoginResponse>
}