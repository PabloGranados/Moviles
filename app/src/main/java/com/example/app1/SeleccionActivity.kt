package com.example.app1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app1.ui.SeleccionFragment

class SeleccionActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            setContentView(R.layout.activity_seleccion)
            
            // Configurar ActionBar
            supportActionBar?.apply {
                title = "☑️ Elementos de Selección"
                setDisplayHomeAsUpEnabled(true)
            }
            
            // Cargar el fragment si no existe
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SeleccionFragment())
                    .commit()
            }
            
        } catch (e: Exception) {
            Toast.makeText(this, "Error al inicializar Selección: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}