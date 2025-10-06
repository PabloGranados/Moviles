package com.example.app1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import com.example.app1.R
import com.google.android.material.button.MaterialButton

class BotonesFragment : Fragment() {

    private var btnNormal: Button? = null
    private var btnIcono: Button? = null
    private var btnOutlined: MaterialButton? = null
    private var btnTexto: MaterialButton? = null
    private var ibCorazon: ImageButton? = null
    private var ibCompartir: ImageButton? = null
    private var ibInfo: ImageButton? = null
    private var toggleButton: ToggleButton? = null
    private var tvContador: TextView? = null
    private var btnReset: Button? = null
    
    private var contadorClics = 0
    private var corazonActivo = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_botones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            initViews(view)
            setupListeners()
            updateContador()
        } catch (e: Exception) {
            Toast.makeText(context, "Error al inicializar Botones: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews(view: View) {
        btnNormal = view.findViewById(R.id.btnNormal)
        btnIcono = view.findViewById(R.id.btnIcono)
        btnOutlined = view.findViewById(R.id.btnOutlined)
        btnTexto = view.findViewById(R.id.btnTexto)
        ibCorazon = view.findViewById(R.id.ibCorazon)
        ibCompartir = view.findViewById(R.id.ibCompartir)
        ibInfo = view.findViewById(R.id.ibInfo)
        toggleButton = view.findViewById(R.id.toggleButton)
        tvContador = view.findViewById(R.id.tvContador)
        btnReset = view.findViewById(R.id.btnReset)
    }

    private fun setupListeners() {
        btnNormal?.setOnClickListener {
            contadorClics++
            updateContador()
            Toast.makeText(context, "¡Botón normal presionado! 👍", Toast.LENGTH_SHORT).show()
        }

        btnIcono?.setOnClickListener {
            contadorClics++
            updateContador()
            Toast.makeText(context, "¡Me gusta presionado! 👍", Toast.LENGTH_SHORT).show()
        }

        btnOutlined?.setOnClickListener {
            contadorClics++
            updateContador()
            Toast.makeText(context, "Botón outlined activado 📝", Toast.LENGTH_SHORT).show()
        }

        btnTexto?.setOnClickListener {
            contadorClics++
            updateContador()
            Toast.makeText(context, "Botón de texto activado 📄", Toast.LENGTH_SHORT).show()
        }

        ibCorazon?.setOnClickListener {
            contadorClics++
            corazonActivo = !corazonActivo
            updateContador()
            
            if (corazonActivo) {
                ibCorazon?.setImageResource(android.R.drawable.ic_menu_add)
                Toast.makeText(context, "¡Elemento agregado! ➕", Toast.LENGTH_SHORT).show()
            } else {
                ibCorazon?.setImageResource(android.R.drawable.ic_input_add)
                Toast.makeText(context, "Elemento removido ➖", Toast.LENGTH_SHORT).show()
            }
        }

        ibCompartir?.setOnClickListener {
            contadorClics++
            updateContador()
            Toast.makeText(context, "¡Compartiendo contenido! 📤", Toast.LENGTH_SHORT).show()
        }

        ibInfo?.setOnClickListener {
            contadorClics++
            updateContador()
            Toast.makeText(context, "Mostrando información adicional ℹ️", Toast.LENGTH_SHORT).show()
        }

        toggleButton?.setOnClickListener {
            contadorClics++
            updateContador()
            
            if (toggleButton?.isChecked == true) {
                Toast.makeText(context, "¡Función activada! 🟢", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Función desactivada 🔴", Toast.LENGTH_SHORT).show()
            }
        }

        btnReset?.setOnClickListener {
            contadorClics = 0
            corazonActivo = false
            updateContador()
            
            // Resetear estados
            ibCorazon?.setImageResource(android.R.drawable.ic_input_add)
            toggleButton?.isChecked = false
            
            Toast.makeText(context, "¡Contador reiniciado! 🔄", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateContador() {
        tvContador?.text = "Total de clics: $contadorClics"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        btnNormal = null
        btnIcono = null
        btnOutlined = null
        btnTexto = null
        ibCorazon = null
        ibCompartir = null
        ibInfo = null
        toggleButton = null
        tvContador = null
        btnReset = null
    }
}
