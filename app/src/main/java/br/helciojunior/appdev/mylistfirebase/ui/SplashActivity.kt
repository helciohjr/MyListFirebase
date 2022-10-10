package br.helciojunior.appdev.mylistfirebase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import br.helciojunior.appdev.mylistfirebase.base.BaseActivity
import br.helciojunior.appdev.mylistfirebase.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            irActivity(Intent(this, LoginActivity::class.java), true)
        }, 3000)
    }
}