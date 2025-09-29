package com.example.app1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app1.ui.TextFieldsFragment

class TextFieldsActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            setContentView(R.layout.activity_textfields)
            
            // Configurar ActionBar
            supportActionBar?.apply {
                title = "📝 Campos de Texto"
                setDisplayHomeAsUpEnabled(true)
            }
            
            // Cargar el fragment si no existe
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, TextFieldsFragment())
                    .commit()
            }
            
        } catch (e: Exception) {
            Toast.makeText(this, "Error al inicializar TextFields: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}