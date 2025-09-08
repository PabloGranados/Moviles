package com.example.app1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.app1.R

class SeleccionFragment : Fragment() {

    private var cbKotlin: CheckBox? = null
    private var cbJava: CheckBox? = null
    private var cbPython: CheckBox? = null
    private var cbJavaScript: CheckBox? = null
    private var rgNivel: RadioGroup? = null
    private var swNotificaciones: Switch? = null
    private var swModoOscuro: Switch? = null
    private var swSincronizacion: Switch? = null
    private var spinnerPais: Spinner? = null
    private var btnMostrarSelecciones: Button? = null
    private var tvResultadoSeleccion: TextView? = null

    private val paises = arrayOf(
        "Selecciona un país...",
        "Argentina 🇦🇷",
        "Brasil 🇧🇷", 
        "Chile 🇨🇱",
        "Colombia 🇨🇴",
        "España 🇪🇸",
        "México 🇲🇽",
        "Perú 🇵🇪",
        "Uruguay 🇺🇾",
        "Venezuela 🇻🇪"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seleccion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            initViews(view)
            setupSpinner()
            setupListeners()
        } catch (e: Exception) {
            Toast.makeText(context, "Error al inicializar Selección: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews(view: View) {
        cbKotlin = view.findViewById(R.id.cbKotlin)
        cbJava = view.findViewById(R.id.cbJava)
        cbPython = view.findViewById(R.id.cbPython)
        cbJavaScript = view.findViewById(R.id.cbJavaScript)
        rgNivel = view.findViewById(R.id.rgNivel)
        swNotificaciones = view.findViewById(R.id.swNotificaciones)
        swModoOscuro = view.findViewById(R.id.swModoOscuro)
        swSincronizacion = view.findViewById(R.id.swSincronizacion)
        spinnerPais = view.findViewById(R.id.spinnerPais)
        btnMostrarSelecciones = view.findViewById(R.id.btnMostrarSelecciones)
        tvResultadoSeleccion = view.findViewById(R.id.tvResultadoSeleccion)
    }

    private fun setupSpinner() {
        try {
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, paises)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPais?.adapter = adapter
        } catch (e: Exception) {
            Toast.makeText(context, "Error al configurar spinner: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupListeners() {
        btnMostrarSelecciones?.setOnClickListener {
            try {
                mostrarSelecciones()
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun mostrarSelecciones() {
        val resultado = StringBuilder()
        resultado.append("📋 TUS SELECCIONES:\n\n")

        // CheckBoxes
        val lenguajes = mutableListOf<String>()
        if (cbKotlin?.isChecked == true) lenguajes.add("Kotlin 🟢")
        if (cbJava?.isChecked == true) lenguajes.add("Java ☕")
        if (cbPython?.isChecked == true) lenguajes.add("Python 🐍")
        if (cbJavaScript?.isChecked == true) lenguajes.add("JavaScript 💛")

        if (lenguajes.isNotEmpty()) {
            resultado.append("💻 Lenguajes favoritos:\n")
            lenguajes.forEach { lenguaje ->
                resultado.append("   • $lenguaje\n")
            }
            resultado.append("\n")
        } else {
            resultado.append("💻 Lenguajes: Ninguno seleccionado\n\n")
        }

        // RadioButton
        val nivelSeleccionado = when (rgNivel?.checkedRadioButtonId) {
            R.id.rbPrincipiante -> "Principiante 🌱"
            R.id.rbIntermedio -> "Intermedio 🌳"
            R.id.rbAvanzado -> "Avanzado 🌟"
            R.id.rbExperto -> "Experto 🚀"
            else -> "No seleccionado"
        }
        resultado.append("📊 Nivel de experiencia: $nivelSeleccionado\n\n")

        // Switches
        resultado.append("⚙️ Configuraciones:\n")
        resultado.append("   🔔 Notificaciones: ${if (swNotificaciones?.isChecked == true) "Activadas" else "Desactivadas"}\n")
        resultado.append("   🌙 Modo oscuro: ${if (swModoOscuro?.isChecked == true) "Activado" else "Desactivado"}\n")
        resultado.append("   🔄 Sincronización: ${if (swSincronizacion?.isChecked == true) "Activada" else "Desactivada"}\n\n")

        // Spinner
        val paisSeleccionado = spinnerPais?.selectedItem?.toString() ?: ""
        if ((spinnerPais?.selectedItemPosition ?: 0) > 0) {
            resultado.append("🌍 País: $paisSeleccionado")
        } else {
            resultado.append("🌍 País: No seleccionado")
        }

        tvResultadoSeleccion?.text = resultado.toString()
        tvResultadoSeleccion?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cbKotlin = null
        cbJava = null
        cbPython = null
        cbJavaScript = null
        rgNivel = null
        swNotificaciones = null
        swModoOscuro = null
        swSincronizacion = null
        spinnerPais = null
        btnMostrarSelecciones = null
        tvResultadoSeleccion = null
    }
}
