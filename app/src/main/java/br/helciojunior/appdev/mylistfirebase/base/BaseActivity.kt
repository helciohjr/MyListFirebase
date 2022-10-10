package br.helciojunior.appdev.mylistfirebase.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    override fun onStart() {
        super.onStart()
    }

    fun irActivity(intent: Intent, finish: Boolean) {
        startActivity(intent)
        if(finish){
            finish()
        }
    }
}