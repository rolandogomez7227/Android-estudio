package com.example.myapplication

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String?, val message: String?)
data class RegisterRequest(val username: String, val email: String, val password: String)

data class Rutina(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String?
)

data class ExercisesResponse(
    @SerializedName("data") val data: List<Rutina>,
    @SerializedName("total") val total: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("totalPages") val totalPages: Int
)

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("exercises")
    fun getRutinas(
        @Header("Authorization") authHeader: String,
        @Query("body_part") bodyPart: String? // ¡Agregamos esto!
    ): Call<ExercisesResponse>

    @POST("auth/register")
    fun register(@Body request: RegisterRequest): Call<LoginResponse>
}