package com.example.dinosaurapp

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Actividad principal que muestra las eras prehistóricas
 * Nivel 1 de la jerarquía: Eras (Mesozoica, Paleozoica, Cenozoica)
 */
class MainActivity : AppCompatActivity() {

    private lateinit var rvEras: RecyclerView
    private lateinit var fabTimePortal: FloatingActionButton
    private lateinit var eraAdapter: EraAdapter
    private lateinit var infoFragment: InfoFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupRecyclerView()
        setupFragment()
        setupFloatingActionButton()
        
        // Aplicar animación de entrada a la actividad
        applyEnterAnimation()
    }

    private fun initializeViews() {
        rvEras = findViewById(R.id.rvEras)
        fabTimePortal = findViewById(R.id.fabTimePortal)
    }

    private fun setupRecyclerView() {
        val eras = createErasData()
        
        eraAdapter = EraAdapter(eras) { era ->
            navigateToEra(era)
        }
        
        rvEras.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = eraAdapter
            
            // Aplicar animación al RecyclerView
            val slideInAnimation = AnimationUtils.loadAnimation(
                this@MainActivity, 
                R.anim.scale_fade_in
            )
            startAnimation(slideInAnimation)
        }
    }

    private fun setupFragment() {
        infoFragment = InfoFragment.newInstance()
        
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, infoFragment)
            .commit()
    }

    private fun setupFloatingActionButton() {
        fabTimePortal.setOnClickListener {
            // Animación del portal temporal
            val portalAnimation = AnimationUtils.loadAnimation(
                this, 
                R.anim.time_portal_effect
            )
            fabTimePortal.startAnimation(portalAnimation)
            
            // Actualizar información en el fragment
            infoFragment.updateInfo(
                "Portal del Tiempo",
                "¡Haz clic en una era para viajar a través del tiempo y explorar las criaturas prehistóricas!"
            )
        }
    }

    private fun applyEnterAnimation() {
        val slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main_container)
            ?.startAnimation(slideInAnimation)
    }

    private fun navigateToEra(era: Era) {
        // Crear intent para navegar a PeriodActivity
        val intent = Intent(this, PeriodActivity::class.java).apply {
            putExtra("era_id", era.id)
            putExtra("era_name", era.name)
            putExtra("era_primary_color", era.primaryColor)
        }
        
        // Aplicar transición personalizada
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        
        // Actualizar información del fragment antes de salir
        infoFragment.updateInfo(
            "Viajando...",
            "Transportándote a la ${era.name}..."
        )
    }

    private fun createErasData(): List<Era> {
        return listOf(
            Era(
                id = "mesozoic",
                name = getString(R.string.era_mesozoica),
                description = getString(R.string.era_mesozoica_desc),
                timeRange = "252 - 66 Ma",
                iconResource = R.drawable.ic_mesozoic_era,
                primaryColor = ContextCompat.getColor(this, R.color.mesozoic_primary),
                secondaryColor = ContextCompat.getColor(this, R.color.mesozoic_secondary),
                periods = listOf("triassic", "jurassic", "cretaceous")
            ),
            Era(
                id = "paleozoic",
                name = getString(R.string.era_paleozoica),
                description = getString(R.string.era_paleozoica_desc),
                timeRange = "541 - 252 Ma",
                iconResource = R.drawable.ic_paleozoic_era,
                primaryColor = ContextCompat.getColor(this, R.color.paleozoic_primary),
                secondaryColor = ContextCompat.getColor(this, R.color.paleozoic_secondary),
                periods = listOf("devonian", "carboniferous", "permian")
            ),
            Era(
                id = "cenozoic",
                name = getString(R.string.era_cenozoica),
                description = getString(R.string.era_cenozoica_desc),
                timeRange = "66 Ma - presente",
                iconResource = R.drawable.ic_cenozoic_era,
                primaryColor = ContextCompat.getColor(this, R.color.cenozoic_primary),
                secondaryColor = ContextCompat.getColor(this, R.color.cenozoic_secondary),
                periods = listOf("paleogene", "neogene", "quaternary")
            )
        )
    }
}