package me.dio.appcadastro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import me.dio.appcadastro.databinding.ActivityFormUser2Binding

class FormUser2 : AppCompatActivity() {

    private lateinit var binding: ActivityFormUser2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormUser2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.TT1.setOnClickListener{
            val preco = Intent(this, Cortes ::class.java)
            startActivity(preco)
        }

        binding.TT2.setOnClickListener{
            val uca = Intent(this, Unha::class.java)
            startActivity(uca)
        }


        binding.userDeslogar.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val voltar = Intent(this, FormLogin :: class.java)
            startActivity(voltar)
            finish()
        }

    }
}