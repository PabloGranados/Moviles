package com.example.app1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app1.ui.InformacionFragment

class InformacionActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            setContentView(R.layout.activity_informacion)
            
            // Configurar ActionBar
            supportActionBar?.apply {
                title = "ℹ️ Elementos de Información"
                setDisplayHomeAsUpEnabled(true)
            }
            
            // Cargar el fragment si no existe
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, InformacionFragment())
                    .commit()
            }
            
        } catch (e: Exception) {
            Toast.makeText(this, "Error al inicializar Información: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}