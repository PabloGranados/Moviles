package com.example.app1.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app1.R
import com.google.android.material.textfield.TextInputEditText

class ListasFragment : Fragment() {

    private var etNuevoElemento: TextInputEditText? = null
    private var btnAgregar: Button? = null
    private var recyclerView: RecyclerView? = null
    private var tvContador: TextView? = null
    private var btnLimpiar: Button? = null
    private var btnEjemplos: Button? = null
    
    private var adapter: TareasAdapter? = null
    private val tareas = mutableListOf<Tarea>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            initViews(view)
            setupRecyclerView()
            setupListeners()
            updateContador()
        } catch (e: Exception) {
            Toast.makeText(context, "Error al inicializar Listas: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews(view: View) {
        etNuevoElemento = view.findViewById(R.id.etNuevoElemento)
        btnAgregar = view.findViewById(R.id.btnAgregar)
        recyclerView = view.findViewById(R.id.recyclerView)
        tvContador = view.findViewById(R.id.tvContador)
        btnLimpiar = view.findViewById(R.id.btnLimpiar)
        btnEjemplos = view.findViewById(R.id.btnEjemplos)
    }

    private fun setupRecyclerView() {
        try {
            adapter = TareasAdapter(tareas) { tarea ->
                eliminarTarea(tarea)
            }
            recyclerView?.layoutManager = LinearLayoutManager(context)
            recyclerView?.adapter = adapter
        } catch (e: Exception) {
            Toast.makeText(context, "Error al configurar lista: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupListeners() {
        btnAgregar?.setOnClickListener {
            agregarTarea()
        }

        btnLimpiar?.setOnClickListener {
            limpiarLista()
        }

        btnEjemplos?.setOnClickListener {
            agregarEjemplos()
        }

        etNuevoElemento?.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || 
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                agregarTarea()
                true
            } else {
                false
            }
        }
    }

    private fun agregarTarea() {
        val texto = etNuevoElemento?.text?.toString()?.trim() ?: ""
        if (texto.isNotEmpty()) {
            val nuevaTarea = Tarea(
                id = System.currentTimeMillis(),
                texto = texto,
                completada = false
            )
            tareas.add(nuevaTarea)
            adapter?.notifyItemInserted(tareas.size - 1)
            etNuevoElemento?.setText("")
            updateContador()
            Toast.makeText(context, "✅ Tarea agregada", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "⚠️ Escribe algo primero", Toast.LENGTH_SHORT).show()
        }
    }

    private fun eliminarTarea(tarea: Tarea) {
        val posicion = tareas.indexOf(tarea)
        if (posicion != -1) {
            tareas.removeAt(posicion)
            adapter?.notifyItemRemoved(posicion)
            updateContador()
            Toast.makeText(context, "🗑️ Tarea eliminada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun limpiarLista() {
        if (tareas.isNotEmpty()) {
            tareas.clear()
            adapter?.notifyDataSetChanged()
            updateContador()
            Toast.makeText(context, "🧹 Lista limpiada", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "ℹ️ La lista ya está vacía", Toast.LENGTH_SHORT).show()
        }
    }

    private fun agregarEjemplos() {
        val ejemplos = listOf(
            "Estudiar Kotlin 📱",
            "Practicar Android Studio 🛠️",
            "Crear una app increíble 🚀",
            "Leer documentación 📚",
            "Hacer ejercicio 💪"
        )
        
        ejemplos.forEach { ejemplo ->
            val nuevaTarea = Tarea(
                id = System.currentTimeMillis() + ejemplo.hashCode(),
                texto = ejemplo,
                completada = false
            )
            tareas.add(nuevaTarea)
        }
        
        adapter?.notifyDataSetChanged()
        updateContador()
        Toast.makeText(context, "📝 Ejemplos agregados", Toast.LENGTH_SHORT).show()
    }

    private fun updateContador() {
        val total = tareas.size
        val completadas = tareas.count { it.completada }
        tvContador?.text = "$total elementos ($completadas completadas)"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        etNuevoElemento = null
        btnAgregar = null
        recyclerView = null
        tvContador = null
        btnLimpiar = null
        btnEjemplos = null
        adapter = null
    }

    // Clase de datos para las tareas
    data class Tarea(
        val id: Long,
        val texto: String,
        var completada: Boolean
    )

    // Adaptador para el RecyclerView
    inner class TareasAdapter(
        private val tareas: List<Tarea>,
        private val onEliminar: (Tarea) -> Unit
    ) : RecyclerView.Adapter<TareasAdapter.TareaViewHolder>() {

        inner class TareaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val cbCompletado: CheckBox = view.findViewById(R.id.cbCompletado)
            val tvTexto: TextView = view.findViewById(R.id.tvTexto)
            val btnEliminar: ImageButton = view.findViewById(R.id.btnEliminar)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lista, parent, false)
            return TareaViewHolder(view)
        }

        override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
            try {
                val tarea = tareas[position]
                
                holder.tvTexto.text = tarea.texto
                holder.cbCompletado.isChecked = tarea.completada
                
                // Aplicar estilo tachado si está completada
                if (tarea.completada) {
                    holder.tvTexto.paintFlags = holder.tvTexto.paintFlags or 
                        android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                    holder.tvTexto.alpha = 0.6f
                } else {
                    holder.tvTexto.paintFlags = holder.tvTexto.paintFlags and 
                        android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    holder.tvTexto.alpha = 1.0f
                }

                holder.cbCompletado.setOnCheckedChangeListener { _, isChecked ->
                    tarea.completada = isChecked
                    notifyItemChanged(position)
                    updateContador()
                }

                holder.btnEliminar.setOnClickListener {
                    onEliminar(tarea)
                }
            } catch (e: Exception) {
                // Evitar crashes por índices fuera de rango
            }
        }

        override fun getItemCount() = tareas.size
    }
}
