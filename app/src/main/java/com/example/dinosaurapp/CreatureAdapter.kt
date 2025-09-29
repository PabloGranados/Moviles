package com.example.dinosaurapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter para mostrar criaturas en un RecyclerView
 */
class CreatureAdapter(
    private val creatures: List<Creature>,
    private val onCreatureClick: (Creature) -> Unit
) : RecyclerView.Adapter<CreatureAdapter.CreatureViewHolder>() {

    inner class CreatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val creatureImage: ImageView = itemView.findViewById(R.id.ivCreatureImage)
        private val creatureName: TextView = itemView.findViewById(R.id.tvCreatureName)
        private val creatureScientificName: TextView = itemView.findViewById(R.id.tvCreatureScientificName)
        private val creatureDiet: TextView = itemView.findViewById(R.id.tvCreatureDiet)
        private val creatureDescription: TextView = itemView.findViewById(R.id.tvCreatureDescription)
        private val eraTag: TextView = itemView.findViewById(R.id.tvEraTag)
        private val sizeTag: TextView = itemView.findViewById(R.id.tvSizeTag)

        fun bind(creature: Creature) {
            creatureImage.setImageResource(creature.imageResource)
            creatureName.text = creature.name
            creatureScientificName.text = creature.scientificName
            creatureDiet.text = "${creature.diet} • ${creature.size}"
            creatureDescription.text = creature.description
            
            // Configurar tags
            eraTag.text = getEraDisplayName(creature.periodId)
            sizeTag.text = getSizeCategory(creature.size)
            
            // Configurar click listener con animación
            itemView.setOnClickListener {
                // Animación de selección
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
                                onCreatureClick(creature)
                            }
                    }
            }
        }

        private fun getEraDisplayName(periodId: String): String {
            return when (periodId) {
                "triassic", "jurassic", "cretaceous" -> "Mesozoico"
                "devonian", "carboniferous", "permian" -> "Paleozoico"
                "paleogene", "neogene", "quaternary" -> "Cenozoico"
                else -> "Prehistórico"
            }
        }

        private fun getSizeCategory(size: String): String {
            val sizeNumber = size.filter { it.isDigit() }.toIntOrNull() ?: 0
            return when {
                sizeNumber >= 15 -> "Gigante"
                sizeNumber >= 8 -> "Grande"
                sizeNumber >= 4 -> "Mediano"
                else -> "Pequeño"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_creature_card, parent, false)
        return CreatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreatureViewHolder, position: Int) {
        holder.bind(creatures[position])
    }

    override fun getItemCount(): Int = creatures.size
}