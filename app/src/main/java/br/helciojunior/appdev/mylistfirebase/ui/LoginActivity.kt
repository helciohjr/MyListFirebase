package br.helciojunior.appdev.mylistfirebase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.helciojunior.appdev.mylistfirebase.base.BaseActivity
import br.helciojunior.appdev.mylistfirebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var usuario: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usuario = FirebaseAuth.getInstance()

        checarUsuarioLogado()
    }

    override fun onStart() {
        super.onStart()

        iniciarListeners()
    }

    private fun iniciarListeners() {
        binding.btnLogin.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun checarUsuarioLogado() {
        if(usuario.currentUser != null) {
            irActivity(Intent(this, SalvarActivity::class.java), true)
        }
    }


    private fun registrarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        if (email.isNotEmpty() && senha.isNotEmpty()){
            usuario.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(LoginActivity()) { task ->

                    if(task.isSuccessful) {
                        Toast.makeText(this, "Usuário adicionado com sucesso!",
                            Toast.LENGTH_LONG)
                            .show()

                        irActivity(Intent(this, SalvarActivity::class.java), true)
                    } else {

                        usuario.signInWithEmailAndPassword(email, senha)
                            .addOnCompleteListener { mTask ->
                                if(mTask.isSuccessful){
                                    irActivity(Intent(this, SalvarActivity::class.java), true)
                                } else {
                                    Toast.makeText(this, task.exception!!.message,
                                        Toast.LENGTH_LONG)
                                        .show()
                                }
                            }
                    }
                }
        } else {
            Toast.makeText(this, "O Email e senha não pode estar vazio!",
                Toast.LENGTH_LONG)
                .show()
        }

    }

}