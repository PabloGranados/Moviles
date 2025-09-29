package com.example.dinosaurapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter para mostrar las eras prehistóricas en un RecyclerView
 */
class EraAdapter(
    private val eras: List<Era>,
    private val onEraClick: (Era) -> Unit
) : RecyclerView.Adapter<EraAdapter.EraViewHolder>() {

    inner class EraViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eraIcon: ImageView = itemView.findViewById(R.id.ivEraIcon)
        private val eraName: TextView = itemView.findViewById(R.id.tvEraName)
        private val eraDescription: TextView = itemView.findViewById(R.id.tvEraDescription)
        private val progressIndicator: View = itemView.findViewById(R.id.progressIndicator)

        fun bind(era: Era) {
            eraIcon.setImageResource(era.iconResource)
            eraName.text = era.name
            eraDescription.text = era.description
            
            // Configurar color del indicador de progreso según la era
            progressIndicator.setBackgroundColor(era.secondaryColor)
            
            // Configurar click listener
            itemView.setOnClickListener {
                // Aplicar animación al hacer click
                itemView.animate()
                    .scaleX(0.95f)
                    .scaleY(0.95f)
                    .setDuration(100)
                    .withEndAction {
                        itemView.animate()
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .setDuration(100)
                            .withEndAction {
                                onEraClick(era)
                            }
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EraViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_era_card, parent, false)
        return EraViewHolder(view)
    }

    override fun onBindViewHolder(holder: EraViewHolder, position: Int) {
        holder.bind(eras[position])
    }

    override fun getItemCount(): Int = eras.size
}