package me.dio.appcadastro

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import me.dio.appcadastro.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{view ->
            val email = binding.loginEmail.text.toString()
            val senha = binding.loginSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view, "preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()

            }else{

                auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener{autentica ->
                    if (autentica.isSuccessful){
                         navegarFormuser()


                    }

                }
            }

        }


    }
    private fun navegarFormuser(){

        val intent = Intent (this, FormUser2::class.java)
        startActivity(intent)
        finish()


    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null){
            navegarFormuser()

        }
    }
}