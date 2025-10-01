package com.example.dinosaurapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.app.AlertDialog

/**
 * Fragment para explorar sitios de interés del período
 */
class ExploreFragment : Fragment() {

    private lateinit var rvExplorationSites: RecyclerView
    private lateinit var explorationAdapter: ExplorationSiteAdapter
    private lateinit var tvExplorationDescription: TextView
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
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initializeViews(view)
        setupRecyclerView()
        setupDescription()
        
        // Aplicar animación de entrada
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setDuration(500)
            .start()
    }

    private fun initializeViews(view: View) {
        rvExplorationSites = view.findViewById(R.id.rvExplorationSites)
        tvExplorationDescription = view.findViewById(R.id.tvExplorationDescription)
    }

    private fun setupRecyclerView() {
        val explorationSites = createExplorationSitesData(periodId)
        
        explorationAdapter = ExplorationSiteAdapter(explorationSites) { site ->
            showSiteDetails(site)
        }
        
        rvExplorationSites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = explorationAdapter
            // Añadir animación sutil
            alpha = 0f
            animate()
                .alpha(1f)
                .setDuration(300)
                .setStartDelay(200)
                .start()
        }
    }

    private fun setupDescription() {
        val description = when (periodId) {
            "triassic" -> "Explora los primeros ecosistemas donde aparecieron los dinosaurios tras la gran extinción del Pérmico."
            "jurassic" -> "Descubre los sitios donde dominaron los gigantescos saurópodos y primeros terópodos."
            "cretaceous" -> "Investiga los últimos hábitats de los dinosaurios antes de su extinción masiva."
            "devonian" -> "Sumérgete en los océanos donde evolucionaron los primeros vertebrados terrestres."
            "carboniferous" -> "Explora los vastos bosques pantanosos que formaron los depósitos de carbón."
            "permian" -> "Descubre los ecosistemas dominados por los antecesores de los mamíferos."
            "paleogene" -> "Investiga el renacimiento de la vida tras la extinción de los dinosaurios."
            "neogene" -> "Explora la diversificación de mamíferos en las nuevas praderas."
            "quaternary" -> "Descubre los hábitats de la megafauna pleistocena y la evolución humana."
            else -> "Descubre los sitios más fascinantes de este período geológico."
        }
        
        tvExplorationDescription.text = description
    }

    private fun showSiteDetails(site: ExplorationSite) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("🏛️ ${site.name}")
        
        val detailedInfo = getDetailedSiteInfo(site, periodId)
        dialogBuilder.setMessage(detailedInfo)
        
        dialogBuilder.setPositiveButton("¡Fascinante!") { dialog, _ ->
            dialog.dismiss()
        }
        
        dialogBuilder.setNeutralButton("Ver más sitios") { dialog, _ ->
            dialog.dismiss()
        }
        
        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun getDetailedSiteInfo(site: ExplorationSite, periodId: String): String {
        return when (site.id) {
            "site_1" -> buildString {
                append("📍 ${site.description}\n\n")
                append("🔍 Descubrimientos importantes:\n")
                append("• Esqueletos completos de especies representativas\n")
                append("• Evidencia de comportamiento social\n")
                append("• Huellas y rastros fosilizados\n\n")
                append("🏛️ Este sitio nos ayuda a entender cómo vivían las criaturas del ${getPeriodDisplayName(periodId)}.")
            }
            "site_2" -> buildString {
                append("🔬 ${site.description}\n\n")
                append("⚗️ Investigaciones actuales:\n")
                append("• Análisis de isótopos para determinar dieta\n")
                append("• Estudios de crecimiento óseo\n")
                append("• Reconstrucción paleoambiental\n\n")
                append("📊 Los datos obtenidos aquí revolucionan nuestro conocimiento del período.")
            }
            "site_3" -> buildString {
                append("🔭 ${site.description}\n\n")
                append("👁️ Desde aquí puedes observar:\n")
                append("• Estratos geológicos del período\n")
                append("• Formaciones rocosas características\n")
                append("• Evidencia de cambios climáticos\n\n")
                append("🌍 Una ventana única al mundo prehistórico.")
            }
            "site_4" -> buildString {
                append("⏰ ${site.description}\n\n")
                append("🌀 Capacidades del portal:\n")
                append("• Visualización de paleoambientes\n")
                append("• Simulación de ecosistemas antiguos\n")
                append("• Experiencia inmersiva 4D\n\n")
                append("✨ ¡Viaja ${getPeriodTimeTravel(periodId)} y experimenta el pasado!")
            }
            else -> "${site.description}\n\nUn lugar verdaderamente especial para explorar este fascinante período de la historia de la Tierra."
        }
    }

    private fun getPeriodDisplayName(periodId: String): String {
        return when (periodId) {
            "triassic" -> "Triásico"
            "jurassic" -> "Jurásico" 
            "cretaceous" -> "Cretácico"
            "devonian" -> "Devónico"
            "carboniferous" -> "Carbonífero"
            "permian" -> "Pérmico"
            "paleogene" -> "Paleógeno"
            "neogene" -> "Neógeno"
            "quaternary" -> "Cuaternario"
            else -> "período prehistórico"
        }
    }

    private fun getPeriodTimeTravel(periodId: String): String {
        return when (periodId) {
            "triassic" -> "252 millones de años atrás"
            "jurassic" -> "201 millones de años atrás"
            "cretaceous" -> "145 millones de años atrás"
            "devonian" -> "419 millones de años atrás"
            "carboniferous" -> "359 millones de años atrás"
            "permian" -> "299 millones de años atrás"
            "paleogene" -> "66 millones de años atrás"
            "neogene" -> "23 millones de años atrás"
            "quaternary" -> "2.6 millones de años atrás"
            else -> "millones de años atrás"
        }
    }

    private fun createExplorationSitesData(periodId: String): List<ExplorationSite> {
        return listOf(
            ExplorationSite(
                id = "site_1",
                name = "Yacimiento Fósil Principal",
                description = "Sitio de excavación con los especímenes más importantes y mejor conservados del período",
                type = InterestPointType.FOSSIL_SITE,
                iconResource = R.drawable.ic_fossil_site
            ),
            ExplorationSite(
                id = "site_2",
                name = "Laboratorio de Investigación",
                description = "Centro de análisis paleontológico donde se estudian los hallazgos más recientes",
                type = InterestPointType.RESEARCH_STATION,
                iconResource = R.drawable.ic_research_station
            ),
            ExplorationSite(
                id = "site_3",
                name = "Mirador Geológico",
                description = "Punto estratégico para observar las formaciones rocosas y estratos del período",
                type = InterestPointType.OBSERVATION_POINT,
                iconResource = R.drawable.ic_observation_point
            ),
            ExplorationSite(
                id = "site_4",
                name = "Portal Temporal",
                description = "Simulador avanzado que te transporta visualmente al ecosistema prehistórico",
                type = InterestPointType.TIME_PORTAL,
                iconResource = R.drawable.ic_time_portal
            )
        )
    }

    companion object {
        private const val ARG_PERIOD_ID = "period_id"

        fun newInstance(periodId: String): ExploreFragment {
            return ExploreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PERIOD_ID, periodId)
                }
            }
        }
    }
}

/**
 * Modelo de datos para sitios de exploración
 */
data class ExplorationSite(
    val id: String,
    val name: String,
    val description: String,
    val type: InterestPointType,
    val iconResource: Int
)