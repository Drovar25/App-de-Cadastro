package me.dio.appcadastro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.dio.appcadastro.databinding.ActivityCortesBinding

class Cortes : AppCompatActivity() {

    private lateinit var binding: ActivityCortesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCortesBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}