package com.example.app1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app1.ui.BotonesFragment

class BotonesActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            setContentView(R.layout.activity_botones)
            
            // Configurar ActionBar
            supportActionBar?.apply {
                title = "🔘 Botones Interactivos"
                setDisplayHomeAsUpEnabled(true)
            }
            
            // Cargar el fragment si no existe
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, BotonesFragment())
                    .commit()
            }
            
        } catch (e: Exception) {
            Toast.makeText(this, "Error al inicializar Botones: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}