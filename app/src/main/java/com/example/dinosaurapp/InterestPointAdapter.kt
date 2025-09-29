package com.example.dinosaurapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter para mostrar puntos de interés en un RecyclerView horizontal
 */
class InterestPointAdapter(
    private val interestPoints: List<InterestPoint>,
    private val onInterestPointClick: (InterestPoint) -> Unit
) : RecyclerView.Adapter<InterestPointAdapter.InterestPointViewHolder>() {

    inner class InterestPointViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val interestIcon: ImageView = itemView.findViewById(R.id.ivInterestIcon)
        private val interestTitle: TextView = itemView.findViewById(R.id.tvInterestTitle)
        private val interestDesc: TextView = itemView.findViewById(R.id.tvInterestDesc)

        fun bind(interestPoint: InterestPoint) {
            // Configurar icono según el tipo
            val iconResource = when (interestPoint.type) {
                InterestPointType.FOSSIL_SITE -> R.drawable.ic_fossil_site
                InterestPointType.RESEARCH_STATION -> R.drawable.ic_research_station
                InterestPointType.OBSERVATION_POINT -> R.drawable.ic_observation_point
                InterestPointType.TIME_PORTAL -> R.drawable.ic_time_portal
                InterestPointType.HABITAT_INFO -> R.drawable.ic_habitat
                InterestPointType.DISCOVERY_SITE -> R.drawable.ic_discovery
            }
            
            interestIcon.setImageResource(iconResource)
            interestTitle.text = interestPoint.title
            interestDesc.text = interestPoint.description
            
            // Configurar click listener con animación
            itemView.setOnClickListener {
                // Animación de rotación y escala
                itemView.animate()
                    .rotationY(360f)
                    .scaleX(1.1f)
                    .scaleY(1.1f)
                    .setDuration(300)
                    .withEndAction {
                        itemView.animate()
                            .scaleX(1.0f)
                            .scaleY(1.0f)
                            .setDuration(200)
                            .withEndAction {
                                onInterestPointClick(interestPoint)
                            }
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestPointViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_interest_point, parent, false)
        return InterestPointViewHolder(view)
    }

    override fun onBindViewHolder(holder: InterestPointViewHolder, position: Int) {
        holder.bind(interestPoints[position])
    }

    override fun getItemCount(): Int = interestPoints.size
}