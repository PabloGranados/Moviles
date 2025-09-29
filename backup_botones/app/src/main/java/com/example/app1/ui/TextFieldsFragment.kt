package com.example.app1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.app1.R
import com.google.android.material.textfield.TextInputEditText

class TextFieldsFragment : Fragment() {

    private var etNombre: TextInputEditText? = null
    private var etEmail: TextInputEditText? = null
    private var etPassword: TextInputEditText? = null
    private var etNumero: TextInputEditText? = null
    private var etComentario: TextInputEditText? = null
    private var btnMostrarInfo: Button? = null
    private var tvResultado: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_textfields, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            initViews(view)
            setupListeners()
        } catch (e: Exception) {
            Toast.makeText(context, "Error al inicializar TextFields: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews(view: View) {
        etNombre = view.findViewById(R.id.etNombre)
        etEmail = view.findViewById(R.id.etEmail)
        etPassword = view.findViewById(R.id.etPassword)
        etNumero = view.findViewById(R.id.etNumero)
        etComentario = view.findViewById(R.id.etComentario)
        btnMostrarInfo = view.findViewById(R.id.btnMostrarInfo)
        tvResultado = view.findViewById(R.id.tvResultado)
    }

    private fun setupListeners() {
        btnMostrarInfo?.setOnClickListener {
            try {
                mostrarInformacion()
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun mostrarInformacion() {
        val nombre = etNombre?.text?.toString()?.trim() ?: ""
        val email = etEmail?.text?.toString()?.trim() ?: ""
        val password = etPassword?.text?.toString() ?: ""
        val numero = etNumero?.text?.toString()?.trim() ?: ""
        val comentario = etComentario?.text?.toString()?.trim() ?: ""

        val resultado = StringBuilder()
        resultado.append("📋 INFORMACIÓN INGRESADA:\n\n")
        
        if (nombre.isNotEmpty()) {
            resultado.append("👤 Nombre: $nombre\n")
        }
        
        if (email.isNotEmpty()) {
            resultado.append("📧 Email: $email\n")
        }
        
        if (password.isNotEmpty()) {
            resultado.append("🔒 Contraseña: ${"*".repeat(password.length)} (${password.length} caracteres)\n")
        }
        
        if (numero.isNotEmpty()) {
            resultado.append("🔢 Edad: $numero años\n")
        }
        
        if (comentario.isNotEmpty()) {
            resultado.append("📝 Comentario: $comentario\n")
        }
        
        if (resultado.length == "📋 INFORMACIÓN INGRESADA:\n\n".length) {
            resultado.append("⚠️ No se ha ingresado información en ningún campo.")
        }

        tvResultado?.text = resultado.toString()
        tvResultado?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        etNombre = null
        etEmail = null
        etPassword = null
        etNumero = null
        etComentario = null
        btnMostrarInfo = null
        tvResultado = null
    }
}
