package com.example.dinosaurapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Fragment que muestra información detallada del período
 */
class PeriodInfoFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_period_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupPeriodInfo(view)
        
        // Aplicar animación de entrada
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setDuration(500)
            .start()
    }

    private fun setupPeriodInfo(view: View) {
        val tvPeriodName: TextView = view.findViewById(R.id.tvPeriodName)
        val tvTimeRange: TextView = view.findViewById(R.id.tvTimeRange)
        val tvPeriodDescription: TextView = view.findViewById(R.id.tvPeriodDescription)
        val tvClimate: TextView = view.findViewById(R.id.tvClimate)
        val tvEvolution: TextView = view.findViewById(R.id.tvEvolution)
        val tvFossils: TextView = view.findViewById(R.id.tvFossils)

        val periodInfo = getPeriodInfo(periodId)
        
        tvPeriodName.text = periodInfo.name
        tvTimeRange.text = periodInfo.timeRange
        tvPeriodDescription.text = periodInfo.description
        tvClimate.text = periodInfo.climate
        tvEvolution.text = periodInfo.evolution
        tvFossils.text = periodInfo.fossils
    }

    private fun getPeriodInfo(periodId: String): PeriodInfo {
        return when (periodId) {
            "triassic" -> PeriodInfo(
                name = "Período Triásico",
                timeRange = "252 - 201 millones de años atrás",
                description = "El Triásico marca el inicio de la Era Mesozoica y el resurgimiento de la vida tras la gran extinción del Pérmico. Durante este período aparecieron los primeros dinosaurios, aunque aún eran pequeños y compartían el ecosistema con otros reptiles arcosaurios.",
                climate = "Clima cálido y seco, con un supercontinente llamado Pangea. Sin casquetes polares, con vastos desiertos interiores y costas húmedas. La atmósfera tenía niveles altos de CO₂.",
                evolution = "• Aparición de los primeros dinosaurios\n• Evolución de los primeros mamíferos\n• Diversificación de reptiles marinos\n• Desarrollo de coníferas modernas\n• Primeros pterosaurios voladores",
                fossils = "• Coelophysis - uno de los primeros dinosaurios\n• Plateosaurus - precursor de saurópodos\n• Lystrosaurus - superviviente de la extinción\n• Desmatosuchus - reptil acorazado\n• Postosuchus - gran depredador arcosaurio"
            )
            "jurassic" -> PeriodInfo(
                name = "Período Jurásico",
                timeRange = "201 - 145 millones de años atrás",
                description = "El Jurásico es famoso por ser la edad dorada de los dinosaurios. Durante este período, los saurópodos gigantes dominaron las tierras, mientras que en los océanos prosperaron reptiles marinos gigantes.",
                climate = "Clima cálido y húmedo sin hielo polar. Fragmentación de Pangea creando nuevos océanos. Abundantes bosques de coníferas, helechos y cícadas. Niveles altos del mar.",
                evolution = "• Gigantismo en saurópodos\n• Diversificación de terópodos\n• Evolución de las primeras aves\n• Dominio de reptiles marinos\n• Desarrollo de estegosaurios",
                fossils = "• Brachiosaurus - saurópodo gigante\n• Allosaurus - gran depredador\n• Stegosaurus - herbívoro acorazado\n• Archaeopteryx - primer ave conocida\n• Diplodocus - saurópodo de cuello largo"
            )
            "cretaceous" -> PeriodInfo(
                name = "Período Cretácico",
                timeRange = "145 - 66 millones de años atrás",
                description = "El Cretácico fue el último período de los dinosaurios y vio la evolución de algunos de los depredadores más grandes de todos los tiempos. También marca la aparición de las plantas con flores.",
                climate = "Clima muy cálido con alta actividad volcánica. Separación completa de continentes. Aparición de plantas con flores que transformaron los ecosistemas terrestres.",
                evolution = "• Evolución de tiranosáuridos gigantes\n• Desarrollo de ceratópsidos\n• Aparición de hadrosaurios\n• Revolución de las angiospermas\n• Diversificación de aves modernas",
                fossils = "• Tyrannosaurus rex - rey de los depredadores\n• Triceratops - herbívoro con cuernos\n• Spinosaurus - dinosaurio semiacuático\n• Parasaurolophus - hadrosaurio con cresta\n• Carcharocles megalodon - tiburón gigante"
            )
            else -> PeriodInfo(
                name = "Período Prehistórico",
                timeRange = "Hace millones de años",
                description = "Un fascinante período de la historia de la Tierra.",
                climate = "Condiciones climáticas únicas de la época.",
                evolution = "Importantes eventos evolutivos.",
                fossils = "Diversos descubrimientos paleontológicos."
            )
        }
    }

    companion object {
        private const val ARG_PERIOD_ID = "period_id"

        fun newInstance(periodId: String): PeriodInfoFragment {
            return PeriodInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PERIOD_ID, periodId)
                }
            }
        }
    }
}

/**
 * Modelo de datos para información del período
 */
data class PeriodInfo(
    val name: String,
    val timeRange: String,
    val description: String,
    val climate: String,
    val evolution: String,
    val fossils: String
)