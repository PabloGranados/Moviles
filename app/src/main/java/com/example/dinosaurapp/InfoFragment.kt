package com.example.dinosaurapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Fragment para mostrar información adicional en la pantalla principal
 */
class InfoFragment : Fragment() {

    private lateinit var tvInfoTitle: TextView
    private lateinit var tvInfoDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        tvInfoTitle = view.findViewById(R.id.tvInfoTitle)
        tvInfoDescription = view.findViewById(R.id.tvInfoDescription)
        
        // Aplicar animación de entrada al fragment
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setDuration(600)
            .start()
    }

    /**
     * Actualiza la información mostrada en el fragment
     */
    fun updateInfo(title: String, description: String) {
        if (::tvInfoTitle.isInitialized && ::tvInfoDescription.isInitialized) {
            tvInfoTitle.text = title
            tvInfoDescription.text = description
            
            // Animación sutil al actualizar contenido
            tvInfoTitle.animate()
                .alpha(0f)
                .setDuration(150)
                .withEndAction {
                    tvInfoTitle.text = title
                    tvInfoTitle.animate().alpha(1f).setDuration(150)
                }
            
            tvInfoDescription.animate()
                .alpha(0f)
                .setDuration(150)
                .withEndAction {
                    tvInfoDescription.text = description
                    tvInfoDescription.animate().alpha(1f).setDuration(150)
                }
        }
    }

    companion object {
        fun newInstance(): InfoFragment {
            return InfoFragment()
        }
    }
}