package com.example.dinosaurapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Fragment para explorar sitios de interés del período
 */
class ExploreFragment : Fragment() {

    private lateinit var rvExplorationSites: RecyclerView
    private lateinit var explorationAdapter: ExplorationSiteAdapter
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
        
        setupMapPoints(view)
        setupRecyclerView(view)
        
        // Aplicar animación de entrada
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setDuration(500)
            .start()
    }

    private fun setupMapPoints(view: View) {
        val point1: ImageView = view.findViewById(R.id.ivPoint1)
        val point2: ImageView = view.findViewById(R.id.ivPoint2)
        val point3: ImageView = view.findViewById(R.id.ivPoint3)

        point1.setOnClickListener {
            animatePoint(point1)
            showPointInfo("Sitio de Fósiles Principal", "Yacimiento rico en especímenes del $periodId")
        }

        point2.setOnClickListener {
            animatePoint(point2)
            showPointInfo("Estación de Investigación", "Centro de análisis paleontológico avanzado")
        }

        point3.setOnClickListener {
            animatePoint(point3)
            showPointInfo("Punto de Observación", "Mirador estratégico del paisaje prehistórico")
        }
    }

    private fun setupRecyclerView(view: View) {
        rvExplorationSites = view.findViewById(R.id.rvExplorationSites)
        
        val explorationSites = createExplorationSitesData(periodId)
        
        explorationAdapter = ExplorationSiteAdapter(explorationSites) { site ->
            showSiteDetails(site)
        }
        
        rvExplorationSites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = explorationAdapter
        }
    }

    private fun animatePoint(point: ImageView) {
        point.animate()
            .scaleX(1.3f)
            .scaleY(1.3f)
            .setDuration(200)
            .withEndAction {
                point.animate()
                    .scaleX(1.0f)
                    .scaleY(1.0f)
                    .setDuration(200)
            }
    }

    private fun showPointInfo(title: String, description: String) {
        Toast.makeText(context, "$title: $description", Toast.LENGTH_SHORT).show()
    }

    private fun showSiteDetails(site: ExplorationSite) {
        Toast.makeText(
            context,
            "${site.name}: ${site.description}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun createExplorationSitesData(periodId: String): List<ExplorationSite> {
        return listOf(
            ExplorationSite(
                id = "site_1",
                name = "Yacimiento Fósil Central",
                description = "Principal sitio de excavación con múltiples especímenes completos",
                type = InterestPointType.FOSSIL_SITE,
                iconResource = R.drawable.ic_fossil_site
            ),
            ExplorationSite(
                id = "site_2",
                name = "Laboratorio de Campo",
                description = "Instalación para análisis in-situ de descubrimientos",
                type = InterestPointType.RESEARCH_STATION,
                iconResource = R.drawable.ic_research_station
            ),
            ExplorationSite(
                id = "site_3",
                name = "Torre de Observación",
                description = "Vista panorámica del hábitat prehistórico reconstituido",
                type = InterestPointType.OBSERVATION_POINT,
                iconResource = R.drawable.ic_observation_point
            ),
            ExplorationSite(
                id = "site_4",
                name = "Portal Temporal",
                description = "Simulador de viaje a través de las eras geológicas",
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