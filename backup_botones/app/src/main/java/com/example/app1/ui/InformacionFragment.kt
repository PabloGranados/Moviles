package com.example.app1.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.app1.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InformacionFragment : Fragment() {

    private var tvProgreso: TextView? = null
    private var progressBarHorizontal: ProgressBar? = null
    private var progressBarCircular: ProgressBar? = null
    private var btnIniciarProgreso: Button? = null
    private var btnDetenerProgreso: Button? = null
    private var tvFechaHora: TextView? = null
    private var tvContadorVisitas: TextView? = null
    private var tvEstadoConexion: TextView? = null
    private var btnActualizar: Button? = null
    private var ivIcono1: ImageView? = null
    private var ivIcono2: ImageView? = null
    private var ivIcono3: ImageView? = null

    private var contadorVisitas = 0
    private var progresoActual = 0
    private var progresando = false
    private val handler = Handler(Looper.getMainLooper())
    private var progresoRunnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_informacion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            initViews(view)
            setupListeners()
            setupImageViews()
            actualizarInformacion()
            
            // Incrementar contador de visitas
            contadorVisitas++
            updateContadorVisitas()
        } catch (e: Exception) {
            Toast.makeText(context, "Error al inicializar Información: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews(view: View) {
        tvProgreso = view.findViewById(R.id.tvProgreso)
        progressBarHorizontal = view.findViewById(R.id.progressBarHorizontal)
        progressBarCircular = view.findViewById(R.id.progressBarCircular)
        btnIniciarProgreso = view.findViewById(R.id.btnIniciarProgreso)
        btnDetenerProgreso = view.findViewById(R.id.btnDetenerProgreso)
        tvFechaHora = view.findViewById(R.id.tvFechaHora)
        tvContadorVisitas = view.findViewById(R.id.tvContadorVisitas)
        tvEstadoConexion = view.findViewById(R.id.tvEstadoConexion)
        btnActualizar = view.findViewById(R.id.btnActualizar)
        ivIcono1 = view.findViewById(R.id.ivIcono1)
        ivIcono2 = view.findViewById(R.id.ivIcono2)
        ivIcono3 = view.findViewById(R.id.ivIcono3)
    }

    private fun setupListeners() {
        btnIniciarProgreso?.setOnClickListener {
            iniciarProgreso()
        }

        btnDetenerProgreso?.setOnClickListener {
            detenerProgreso()
        }

        btnActualizar?.setOnClickListener {
            actualizarInformacion()
            contadorVisitas++
            updateContadorVisitas()
        }
    }

    private fun setupImageViews() {
        ivIcono1?.setOnClickListener {
            tvEstadoConexion?.text = "📡 Estado: Información mostrada ℹ️"
        }

        ivIcono2?.setOnClickListener {
            tvEstadoConexion?.text = "📡 Estado: ¡Favorito marcado! ⭐"
        }

        ivIcono3?.setOnClickListener {
            tvEstadoConexion?.text = "📡 Estado: ¡Alerta activada! ⚠️"
        }
    }

    private fun iniciarProgreso() {
        if (!progresando) {
            progresando = true
            btnIniciarProgreso?.isEnabled = false
            btnDetenerProgreso?.isEnabled = true
            progressBarCircular?.visibility = View.VISIBLE
            
            progresoRunnable = object : Runnable {
                override fun run() {
                    if (progresando && progresoActual < 100) {
                        progresoActual += 2
                        progressBarHorizontal?.progress = progresoActual
                        tvProgreso?.text = "Progreso: $progresoActual%"
                        
                        if (progresoActual >= 100) {
                            completarProgreso()
                        } else {
                            handler.postDelayed(this, 100)
                        }
                    }
                }
            }
            progresoRunnable?.let { handler.post(it) }
        }
    }

    private fun detenerProgreso() {
        progresando = false
        progresoRunnable?.let { handler.removeCallbacks(it) }
        btnIniciarProgreso?.isEnabled = true
        btnDetenerProgreso?.isEnabled = false
        progressBarCircular?.visibility = View.GONE
    }

    private fun completarProgreso() {
        progresando = false
        btnIniciarProgreso?.isEnabled = true
        btnDetenerProgreso?.isEnabled = false
        progressBarCircular?.visibility = View.GONE
        tvEstadoConexion?.text = "📡 Estado: ¡Progreso completado! ✅"
        
        // Reiniciar para la próxima vez
        handler.postDelayed({
            progresoActual = 0
            progressBarHorizontal?.progress = 0
            tvProgreso?.text = "Progreso: 0%"
        }, 2000)
    }

    private fun actualizarInformacion() {
        try {
            // Actualizar fecha y hora
            val formatoFecha = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            val fechaActual = formatoFecha.format(Date())
            tvFechaHora?.text = "🕐 Fecha y hora actual: $fechaActual"
            
            // Simular estado de conexión
            val estados = listOf(
                "📡 Estado: Conectado perfectamente 🟢",
                "📡 Estado: Conexión estable 🔵", 
                "📡 Estado: Señal buena 🟡",
                "📡 Estado: Todo funcionando 🚀"
            )
            tvEstadoConexion?.text = estados.random()
        } catch (e: Exception) {
            Toast.makeText(context, "Error al actualizar: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateContadorVisitas() {
        tvContadorVisitas?.text = "👁️ Veces que has visitado esta pantalla: $contadorVisitas"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        detenerProgreso()
        
        // Limpiar referencias
        tvProgreso = null
        progressBarHorizontal = null
        progressBarCircular = null
        btnIniciarProgreso = null
        btnDetenerProgreso = null
        tvFechaHora = null
        tvContadorVisitas = null
        tvEstadoConexion = null
        btnActualizar = null
        ivIcono1 = null
        ivIcono2 = null
        ivIcono3 = null
    }
}
