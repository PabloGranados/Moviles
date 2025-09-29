package com.example.dinosaurapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Fragment que muestra la lista de criaturas de un período
 */
class CreaturesListFragment : Fragment() {

    private lateinit var rvCreatures: RecyclerView
    private lateinit var creatureAdapter: CreatureAdapter
    private var periodId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            periodId = it.getString(ARG_PERIOD_ID) ?: ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_creatures_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        rvCreatures = view.findViewById(R.id.rvCreatures)
        setupRecyclerView()
        
        // Aplicar animación de entrada
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setDuration(500)
            .start()
    }

    private fun setupRecyclerView() {
        val creatures = createCreaturesData(periodId)
        
        creatureAdapter = CreatureAdapter(creatures) { creature ->
            showCreatureDetails(creature)
        }
        
        rvCreatures.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = creatureAdapter
        }
    }

    private fun showCreatureDetails(creature: Creature) {
        // Mostrar información detallada de la criatura
        val detailsText = """
            ${creature.name}
            
            Nombre científico: ${creature.scientificName}
            
            Características:
            ${creature.characteristics.joinToString("\n• ", "• ")}
            
            Hábitat: ${creature.habitat}
            Dieta: ${creature.diet}
            Tamaño: ${creature.size}
            
            Descubrimiento: ${creature.discovery}
        """.trimIndent()
        
        Toast.makeText(context, detailsText, Toast.LENGTH_LONG).show()
        
        // Aquí se podría abrir un diálogo detallado o una nueva actividad
    }

    private fun createCreaturesData(periodId: String): List<Creature> {
        return when (periodId) {
            "triassic" -> listOf(
                Creature(
                    id = "coelophysis",
                    name = "Coelophysis",
                    scientificName = "Coelophysis bauri",
                    description = "Uno de los primeros dinosaurios terópodos, ágil cazador del Triásico tardío.",
                    periodId = periodId,
                    characteristics = listOf("Bípedo", "Carnívoro", "Ágil", "Cuello largo"),
                    habitat = "Llanuras secas y bosques abiertos",
                    diet = "Carnívoro - peces, pequeños reptiles",
                    size = "3 metros de largo",
                    discovery = "Descubierto en Nuevo México, 1881",
                    imageResource = R.drawable.ic_coelophysis
                ),
                Creature(
                    id = "plateosaurus",
                    name = "Plateosaurus",
                    scientificName = "Plateosaurus trossingensis",
                    description = "Dinosaurio herbívoro de cuello largo, precursor de los saurópodos gigantes.",
                    periodId = periodId,
                    characteristics = listOf("Herbívoro", "Cuello largo", "Bípedo/Cuadrúpedo", "Grande"),
                    habitat = "Bosques de coníferas y helechos",
                    diet = "Herbívoro - coníferas, helechos",
                    size = "8 metros de largo",
                    discovery = "Descubierto en Alemania, 1834",
                    imageResource = R.drawable.ic_plateosaurus
                )
            )
            "jurassic" -> listOf(
                Creature(
                    id = "brachiosaurus",
                    name = "Brachiosaurus",
                    scientificName = "Brachiosaurus altithorax",
                    description = "Gigantesco saurópodo con patas delanteras más largas que las traseras.",
                    periodId = periodId,
                    characteristics = listOf("Gigantesco", "Cuello muy largo", "Herbívoro", "Cuadrúpedo"),
                    habitat = "Bosques de coníferas altas",
                    diet = "Herbívoro - copas de árboles altos",
                    size = "25 metros de largo, 13m de alto",
                    discovery = "Descubierto en Colorado, 1903",
                    imageResource = R.drawable.ic_brachiosaurus
                ),
                Creature(
                    id = "stegosaurus",
                    name = "Stegosaurus",
                    scientificName = "Stegosaurus stenops",
                    description = "Dinosaurio herbívoro famoso por sus placas dorsales y púas en la cola.",
                    periodId = periodId,
                    characteristics = listOf("Placas dorsales", "Púas en cola", "Herbívoro", "Blindado"),
                    habitat = "Llanuras con abundante vegetación",
                    diet = "Herbívoro - plantas bajas, helechos",
                    size = "9 metros de largo",
                    discovery = "Descubierto en Colorado, 1877",
                    imageResource = R.drawable.ic_stegosaurus
                )
            )
            "cretaceous" -> listOf(
                Creature(
                    id = "tyrannosaurus",
                    name = "Tyrannosaurus Rex",
                    scientificName = "Tyrannosaurus rex",
                    description = "El rey de los dinosaurios depredadores, uno de los carnívoros terrestres más grandes.",
                    periodId = periodId,
                    characteristics = listOf("Depredador apex", "Mandíbulas poderosas", "Bípedo", "Brazos pequeños"),
                    habitat = "Bosques subtropicales y llanuras",
                    diet = "Carnívoro - grandes herbívoros",
                    size = "12 metros de largo, 4m de alto",
                    discovery = "Descubierto en Montana, 1902",
                    imageResource = R.drawable.ic_triceratops
                ),
                Creature(
                    id = "triceratops",
                    name = "Triceratops",
                    scientificName = "Triceratops horridus",
                    description = "Herbívoro con tres cuernos característicos y gran cresta ósea protectora.",
                    periodId = periodId,
                    characteristics = listOf("Tres cuernos", "Cresta ósea", "Herbívoro", "Defensa grupal"),
                    habitat = "Bosques abiertos y praderas",
                    diet = "Herbívoro - plantas fibrosas, palmeras",
                    size = "9 metros de largo",
                    discovery = "Descubierto en Colorado, 1889",
                    imageResource = R.drawable.ic_triceratops
                )
            )
            else -> emptyList()
        }
    }

    companion object {
        private const val ARG_PERIOD_ID = "period_id"

        fun newInstance(periodId: String): CreaturesListFragment {
            return CreaturesListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PERIOD_ID, periodId)
                }
            }
        }
    }
}