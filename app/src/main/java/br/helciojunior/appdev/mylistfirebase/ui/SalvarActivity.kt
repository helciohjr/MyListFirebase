package br.helciojunior.appdev.mylistfirebase.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.helciojunior.appdev.mylistfirebase.base.BaseActivity
import br.helciojunior.appdev.mylistfirebase.databinding.ActivityLoginBinding
import br.helciojunior.appdev.mylistfirebase.databinding.ActivitySalvarBinding
import br.helciojunior.appdev.mylistfirebase.model.Contato
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SalvarActivity : BaseActivity() {
    private lateinit var binding: ActivitySalvarBinding
    private lateinit var database: DatabaseReference
    private lateinit var usuario: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySalvarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usuario = FirebaseAuth.getInstance()
        usuarioLogado()

    }

    override fun onStart() {
        super.onStart()

        iniciarListeners()
    }

    private fun usuarioLogado() {
        if(usuario.currentUser != null) {
            usuario.currentUser?.let {
                binding.txtUsuarioLogado.text = it.email
            }
        }
    }

    private fun iniciarListeners() {
        binding.btnSair.setOnClickListener {
            usuario.signOut()
            irActivity(Intent(this, LoginActivity::class.java), true)
        }

        binding.btnSalvar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val numero = binding.editNumero.text.toString()
            val email = binding.editEmail.text.toString()


            database = FirebaseDatabase.getInstance().getReference("Contato")
            val contato = Contato(nome, numero, email)
            database.child(nome).setValue(contato).addOnCompleteListener {

                binding.editNome.text.clear()
                binding.editNumero.text.clear()
                binding.editEmail.text.clear()

                Toast.makeText(this, "Contato salvo com sucesso!",
                    Toast.LENGTH_LONG)
                    .show()
            }.addOnFailureListener {
                Toast.makeText(this, "Erro ao salvar o contato!",
                    Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

}