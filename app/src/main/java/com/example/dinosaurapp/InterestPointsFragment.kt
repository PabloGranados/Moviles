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
 * Fragment para mostrar puntos de interés interactivos
 */
class InterestPointsFragment : Fragment() {

    private lateinit var rvInterestPoints: RecyclerView
    private lateinit var interestAdapter: InterestPointAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_interest_points, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        rvInterestPoints = view.findViewById(R.id.rvInterestPoints)
        setupRecyclerView()
        
        // Aplicar animación de entrada
        view.alpha = 0f
        view.animate()
            .alpha(1f)
            .setDuration(500)
            .start()
    }

    private fun setupRecyclerView() {
        val interestPoints = createInterestPointsData()
        
        interestAdapter = InterestPointAdapter(interestPoints) { interestPoint ->
            showInterestPointInfo(interestPoint)
        }
        
        rvInterestPoints.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = interestAdapter
        }
    }

    private fun showInterestPointInfo(interestPoint: InterestPoint) {
        // Mostrar información del punto de interés
        Toast.makeText(
            context,
            "${interestPoint.title}: ${interestPoint.description}",
            Toast.LENGTH_LONG
        ).show()
        
        // Aquí se podría abrir un diálogo o navegar a otra pantalla
        // con información detallada del punto de interés
    }

    private fun createInterestPointsData(): List<InterestPoint> {
        return listOf(
            InterestPoint(
                id = "fossil_site_1",
                title = getString(R.string.fossil_site),
                description = "Explora sitios donde se han encontrado fósiles importantes",
                type = InterestPointType.FOSSIL_SITE,
                xPosition = 0.2f,
                yPosition = 0.3f,
                additionalInfo = "Más de 50 especies descubiertas"
            ),
            InterestPoint(
                id = "research_station_1",
                title = getString(R.string.research_station),
                description = "Centro de investigación paleontológica avanzada",
                type = InterestPointType.RESEARCH_STATION,
                xPosition = 0.5f,
                yPosition = 0.6f,
                additionalInfo = "Tecnología de última generación"
            ),
            InterestPoint(
                id = "observation_point_1",
                title = getString(R.string.observation_point),
                description = "Punto estratégico para observar el paisaje prehistórico",
                type = InterestPointType.OBSERVATION_POINT,
                xPosition = 0.8f,
                yPosition = 0.4f,
                additionalInfo = "Vista panorámica de 360°"
            ),
            InterestPoint(
                id = "time_portal_1",
                title = getString(R.string.time_portal),
                description = "Portal para viajar entre diferentes períodos",
                type = InterestPointType.TIME_PORTAL,
                xPosition = 0.6f,
                yPosition = 0.2f,
                additionalInfo = "Viaje instantáneo en el tiempo"
            )
        )
    }

    companion object {
        fun newInstance(): InterestPointsFragment {
            return InterestPointsFragment()
        }
    }
}