package com.amaru.sandboxy

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.widget.Toast
import com.google.gson.JsonObject

class UserService {
    private val retrofit = RetrofitClient.instance
    private val userAPI = retrofit.create(UserAPI::class.java)
    private val signAPI = retrofit.create(SignInterface::class.java)
    private val verifyAPI = retrofit.create(VerifyAPI::class.java)
    private val infoAPI = retrofit.create(InfoInterface::class.java)

    fun addUser(user: User, callback: (String?) -> Unit) {
        userAPI.addUser(user).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("UserService", "User pending for e-mail verification...")
                    callback(null) // No error
                } else {
                    Log.e(
                        "UserService",
                        "Response was not successful: ${response.code()} ${response.message()}"
                    )
                    if (response.message() == "Conflict") {
                        callback("This username/email already exists.")
                    } else {
                        callback("There is some server error...Please try again later!")
                    }
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("UserService", "Request failed: ${t.message}")
                callback("Request failed: ${t.message}")
            }
        })
    }


    fun signIn(emailOrUsername: String, password: String, callback: CallBackSıgnIn) {
        val user = User(email = emailOrUsername, password = password, username = emailOrUsername)
        signAPI.signIn(user).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("UserService", "Sign-in successful")

                    callback.onSuccess("User signed in successfully")
                } else {
                    Log.e("UserService", "Sign-in failed: ${response.code()}")
                    callback.onFailure("Sign-in failed: ${response.code()}")

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("UserService", "Sign-in request failed", t)
            }
        })
    }


    fun getUserID(
        emailOrUsername: String,
        onResult: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        val call = RetrofitClient.fetchInterface.getUserID(emailOrUsername)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val userID = response.body()
                    val msg = "Your user id is: " + userID
                    onResult(userID)
                    Log.d("UserService", msg)
                } else {
                    onError(Exception("Error: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                onError(t)
            }
        })
    }

    fun verifyMail(email: String, verifCode: String, callback: CallBackSıgnIn) {
        val requestBody = VerifyRequestBody(email, verifCode)

        verifyAPI.verify(requestBody).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("UserService", "Verification Successful.")
                    callback.onSuccess("User Verified successfully")
                } else {
                    Log.e("UserService", "Verification failed: ${response.code()}")
                    callback.onFailure("User verification failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("UserService", "Verification request failed", t)
            }
        })
    }

    fun getInfo(
        userID: Int, // Change to Int
        onResult: (String?) -> Unit,
        onError: (Throwable?) -> Unit
    ) {
        val call = infoAPI.getUserName(userID)

        call.enqueue(object : Callback<UsernameResponse> {
            override fun onResponse(call: Call<UsernameResponse>, response: Response<UsernameResponse>) {
                if (response.isSuccessful) {
                    val userName = response.body()?.username
                    val msg = "Your user name is: $userName"
                    onResult(userName)
                    Log.d("UserService", msg)
                } else {
                    onError(Exception("Error: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<UsernameResponse>, t: Throwable) {
                onError(t)
            }
        })
    }

}