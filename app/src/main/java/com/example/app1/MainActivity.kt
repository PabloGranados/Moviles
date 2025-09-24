package com.example.app1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private var btnTextFields: Button? = null
    private var btnBotones: Button? = null
    private var btnSeleccion: Button? = null
    private var btnListas: Button? = null
    private var btnInformacion: Button? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            setContentView(R.layout.activity_main)
            
            // Configurar ActionBar
            supportActionBar?.title = "📱 UI Elements Demo"
            
            initViews()
            setupListeners()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al inicializar la aplicación: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    private fun initViews() {
        btnTextFields = findViewById(R.id.btnTextFields)
        btnBotones = findViewById(R.id.btnBotones)
        btnSeleccion = findViewById(R.id.btnSeleccion)
        btnListas = findViewById(R.id.btnListas)
        btnInformacion = findViewById(R.id.btnInformacion)
    }
    
    private fun setupListeners() {
        btnTextFields?.setOnClickListener {
            try {
                val intent = Intent(this, TextFieldsActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error al abrir TextFields: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        
        btnBotones?.setOnClickListener {
            try {
                val intent = Intent(this, BotonesActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error al abrir Botones: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        
        btnSeleccion?.setOnClickListener {
            try {
                val intent = Intent(this, SeleccionActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error al abrir Selección: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        
        btnListas?.setOnClickListener {
            try {
                val intent = Intent(this, ListasActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error al abrir Listas: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
        
        btnInformacion?.setOnClickListener {
            try {
                val intent = Intent(this, InformacionActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "Error al abrir Información: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        btnTextFields = null
        btnBotones = null
        btnSeleccion = null
        btnListas = null
        btnInformacion = null
    }
}