package com.example.news.storage

import android.content.Context
import android.content.SharedPreferences

class SessionManager(var context: Context) {

    var pref: SharedPreferences?= context.getSharedPreferences("KEY", 0)
    var editor: SharedPreferences.Editor?=pref?.edit()

    fun setToken(token:String){
        editor?.putString("token", "Bearer $token")
        editor?.commit()
    }

    fun setNama(nama:String){
        editor?.putString("nama", nama)
        editor?.commit()
    }

    fun getToken():String?{
        return pref?.getString("token", "")
    }

    fun getNama():String?{
        return pref?.getString("nama","")
    }

    fun logout(){
        editor?.clear()
        editor?.commit()
    }

}