package me.dio.appcadastro

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import me.dio.appcadastro.databinding.ActivityFormCadastroBinding

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)





        binding.cdbt.setOnClickListener{view ->
            val email = binding.CDt1.text.toString()
            val senha = binding.CDt2.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()



            }else{
                auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener{cadastro ->
                    if (cadastro.isSuccessful){
                        val snackbar = Snackbar.make(view,"Sucesso ao cadastrar", Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.BLUE)
                        snackbar.show()
                        binding.CDt1.setText("")
                        binding.CDt2.setText("")

                    }

                }.addOnFailureListener{exception ->
                    val mensagemError = when(exception){
                        is FirebaseAuthWeakPasswordException -> "Digite uma senha com no minimo 6 caracteres"
                        is FirebaseAuthInvalidCredentialsException -> "Digite um e-mail valido"
                        is FirebaseAuthUserCollisionException -> "Está conta já existe"
                        is FirebaseNetworkException -> "sem conexão com a internet"
                        else -> "erro ao cadastrar"
                    }
                    val snackbar = Snackbar.make(view,mensagemError, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()

                }
            }
        }
    }
}