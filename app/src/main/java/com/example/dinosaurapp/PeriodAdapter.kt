package com.example.dinosaurapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter para mostrar los períodos de una era en un RecyclerView
 */
class PeriodAdapter(
    private val periods: List<Period>,
    private val onPeriodClick: (Period) -> Unit
) : RecyclerView.Adapter<PeriodAdapter.PeriodViewHolder>() {

    inner class PeriodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val periodIcon: ImageView = itemView.findViewById(R.id.ivPeriodIcon)
        private val periodName: TextView = itemView.findViewById(R.id.tvPeriodName)
        private val periodTime: TextView = itemView.findViewById(R.id.tvPeriodTime)
        private val periodDescription: TextView = itemView.findViewById(R.id.tvPeriodDescription)
        private val timelineIndicator: View = itemView.findViewById(R.id.timelineIndicator)

        fun bind(period: Period, position: Int) {
            periodIcon.setImageResource(period.iconResource)
            periodName.text = period.name
            periodTime.text = period.timeRange
            periodDescription.text = period.description
            
            // Asegurar que la descripción no se corte
            periodDescription.maxLines = Int.MAX_VALUE
            
            // Alternar colores del timeline
            val colors = arrayOf(
                R.color.mesozoic_accent,
                R.color.paleozoic_accent,
                R.color.cenozoic_accent
            )
            timelineIndicator.setBackgroundColor(
                itemView.context.getColor(colors[position % colors.size])
            )
            
            // Configurar click listener con animación
            itemView.setOnClickListener {
                // Animación de pulso
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
                                onPeriodClick(period)
                            }
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeriodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_period_card, parent, false)
        return PeriodViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeriodViewHolder, position: Int) {
        holder.bind(periods[position], position)
    }

    override fun getItemCount(): Int = periods.size
}