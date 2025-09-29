package com.example.app1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app1.ui.ListasFragment

class ListasActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            setContentView(R.layout.activity_listas)
            
            // Configurar ActionBar
            supportActionBar?.apply {
                title = "📋 Listas Interactivas"
                setDisplayHomeAsUpEnabled(true)
            }
            
            // Cargar el fragment si no existe
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ListasFragment())
                    .commit()
            }
            
        } catch (e: Exception) {
            Toast.makeText(this, "Error al inicializar Listas: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}