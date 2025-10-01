package com.example.dinosaurapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter para sitios de exploración
 */
class ExplorationSiteAdapter(
    private val sites: List<ExplorationSite>,
    private val onSiteClick: (ExplorationSite) -> Unit
) : RecyclerView.Adapter<ExplorationSiteAdapter.SiteViewHolder>() {

    inner class SiteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val siteIcon: ImageView = itemView.findViewById(R.id.ivSiteIcon)
        private val siteName: TextView = itemView.findViewById(R.id.tvSiteName)
        private val siteDescription: TextView = itemView.findViewById(R.id.tvSiteDescription)
        private val siteType: TextView = itemView.findViewById(R.id.tvSiteType)

        fun bind(site: ExplorationSite) {
            siteIcon.setImageResource(site.iconResource)
            siteName.text = "${getEmojiForType(site.type)} ${site.name}"
            siteDescription.text = site.description
            siteType.text = getTypeLabel(site.type)
            
            itemView.setOnClickListener {
                // Animación de click
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
                                onSiteClick(site)
                            }
                    }
            }
        }
        
        private fun getEmojiForType(type: InterestPointType): String {
            return when (type) {
                InterestPointType.FOSSIL_SITE -> "🦴"
                InterestPointType.RESEARCH_STATION -> "🔬"
                InterestPointType.OBSERVATION_POINT -> "🔭"
                InterestPointType.TIME_PORTAL -> "⏰"
            }
        }
        
        private fun getTypeLabel(type: InterestPointType): String {
            return when (type) {
                InterestPointType.FOSSIL_SITE -> "Yacimiento"
                InterestPointType.RESEARCH_STATION -> "Laboratorio"
                InterestPointType.OBSERVATION_POINT -> "Observatorio"
                InterestPointType.TIME_PORTAL -> "Portal Temporal"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exploration_site, parent, false)
        return SiteViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        holder.bind(sites[position])
    }

    override fun getItemCount(): Int = sites.size
}