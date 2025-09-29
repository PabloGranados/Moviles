package com.example.dinosaurapp

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.ImageView
import android.widget.TextView

/**
 * Actividad que muestra los períodos de una era específica
 * Nivel 2 de la jerarquía: Períodos (Triásico, Jurásico, Cretácico, etc.)
 */
class PeriodActivity : AppCompatActivity() {

    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var ivEraBackground: ImageView
    private lateinit var tvEraDescription: TextView
    private lateinit var rvPeriods: RecyclerView
    private lateinit var fabEraInfo: FloatingActionButton
    private lateinit var periodAdapter: PeriodAdapter
    private lateinit var interestPointsFragment: InterestPointsFragment

    private var eraId: String = ""
    private var eraName: String = ""
    private var eraPrimaryColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_period)

        getIntentData()
        initializeViews()
        setupToolbar()
        setupRecyclerView()
        setupFragment()
        setupFloatingActionButton()
        applyEnterAnimation()
    }

    private fun getIntentData() {
        eraId = intent.getStringExtra("era_id") ?: ""
        eraName = intent.getStringExtra("era_name") ?: ""
        eraPrimaryColor = intent.getIntExtra("era_primary_color", 
            ContextCompat.getColor(this, R.color.primary_green))
    }

    private fun initializeViews() {
        collapsingToolbar = findViewById(R.id.collapsingToolbar)
        toolbar = findViewById(R.id.toolbar)
        ivEraBackground = findViewById(R.id.ivEraBackground)
        tvEraDescription = findViewById(R.id.tvEraDescription)
        rvPeriods = findViewById(R.id.rvPeriods)
        fabEraInfo = findViewById(R.id.fabEraInfo)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        // Configurar título del CollapsingToolbar
        collapsingToolbar.title = eraName
        collapsingToolbar.setContentScrimColor(eraPrimaryColor)
        
        // Configurar imagen de fondo según la era
        val backgroundResource = when (eraId) {
            "mesozoic" -> R.drawable.ic_mesozoic_era
            "paleozoic" -> R.drawable.ic_paleozoic_era
            "cenozoic" -> R.drawable.ic_cenozoic_era
            else -> R.drawable.ic_mesozoic_era
        }
        ivEraBackground.setImageResource(backgroundResource)
        
        // Configurar descripción
        tvEraDescription.text = getString(R.string.period_subtitle)
    }

    private fun setupRecyclerView() {
        val periods = createPeriodsData(eraId)
        
        periodAdapter = PeriodAdapter(periods) { period ->
            navigateToPeriod(period)
        }
        
        rvPeriods.apply {
            layoutManager = LinearLayoutManager(this@PeriodActivity)
            adapter = periodAdapter
            
            // Aplicar animación de entrada
            val slideInAnimation = AnimationUtils.loadAnimation(
                this@PeriodActivity, 
                R.anim.scale_fade_in
            )
            startAnimation(slideInAnimation)
        }
    }

    private fun setupFragment() {
        interestPointsFragment = InterestPointsFragment.newInstance()
        
        supportFragmentManager.beginTransaction()
            .replace(R.id.interestPointsContainer, interestPointsFragment)
            .commit()
    }

    private fun setupFloatingActionButton() {
        fabEraInfo.setOnClickListener {
            // Animación del FAB
            val portalAnimation = AnimationUtils.loadAnimation(
                this, 
                R.anim.time_portal_effect
            )
            fabEraInfo.startAnimation(portalAnimation)
            
            // Mostrar información de la era
            showEraInfo()
        }
    }

    private fun applyEnterAnimation() {
        val slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        findViewById<androidx.coordinatorlayout.widget.CoordinatorLayout>(R.id.main_coordinator)
            ?.startAnimation(slideInAnimation)
    }

    private fun navigateToPeriod(period: Period) {
        val intent = Intent(this, DinosaurActivity::class.java).apply {
            putExtra("period_id", period.id)
            putExtra("period_name", period.name)
            putExtra("era_name", eraName)
        }
        
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun showEraInfo() {
        // Aquí se podría mostrar un diálogo con información detallada de la era
        // Por ahora, actualizamos la descripción
        val detailedDescription = when (eraId) {
            "mesozoic" -> "La Era Mesozoica, conocida como la 'Era de los Dinosaurios', se caracterizó por un clima cálido y la diversificación de los reptiles."
            "paleozoic" -> "La Era Paleozoica vio la evolución de los primeros vertebrados terrestres y el desarrollo de vastos bosques de helechos."
            "cenozoic" -> "La Era Cenozoica es la 'Era de los Mamíferos', marcada por la diversificación de mamíferos tras la extinción de los dinosaurios."
            else -> "Era prehistórica fascinante llena de criaturas increíbles."
        }
        
        tvEraDescription.text = detailedDescription
    }

    private fun createPeriodsData(eraId: String): List<Period> {
        return when (eraId) {
            "mesozoic" -> listOf(
                Period(
                    id = "triassic",
                    name = getString(R.string.period_triassic),
                    description = getString(R.string.period_triassic_desc),
                    timeRange = "252 - 201 Ma",
                    eraId = eraId,
                    iconResource = R.drawable.ic_triassic_period,
                    creatures = listOf("Coelophysis", "Plateosaurus", "Desmatosuchus")
                ),
                Period(
                    id = "jurassic",
                    name = getString(R.string.period_jurassic),
                    description = getString(R.string.period_jurassic_desc),
                    timeRange = "201 - 145 Ma",
                    eraId = eraId,
                    iconResource = R.drawable.ic_jurassic_period,
                    creatures = listOf("Brachiosaurus", "Stegosaurus", "Allosaurus")
                ),
                Period(
                    id = "cretaceous",
                    name = getString(R.string.period_cretaceous),
                    description = getString(R.string.period_cretaceous_desc),
                    timeRange = "145 - 66 Ma",
                    eraId = eraId,
                    iconResource = R.drawable.ic_cretaceous_period,
                    creatures = listOf("Tyrannosaurus", "Triceratops", "Spinosaurus")
                )
            )
            "paleozoic" -> listOf(
                Period(
                    id = "devonian",
                    name = getString(R.string.period_devonian),
                    description = getString(R.string.period_devonian_desc),
                    timeRange = "419 - 359 Ma",
                    eraId = eraId,
                    iconResource = R.drawable.ic_devonian_period,
                    creatures = listOf("Dunkleosteus", "Acanthostega", "Ichthyostega")
                ),
                Period(
                    id = "carboniferous",
                    name = getString(R.string.period_carboniferous),
                    description = getString(R.string.period_carboniferous_desc),
                    timeRange = "359 - 299 Ma",
                    eraId = eraId,
                    iconResource = R.drawable.ic_carboniferous_period,
                    creatures = listOf("Arthropleura", "Meganeura", "Hylonomus")
                ),
                Period(
                    id = "permian",
                    name = getString(R.string.period_permian),
                    description = getString(R.string.period_permian_desc),
                    timeRange = "299 - 252 Ma",
                    eraId = eraId,
                    iconResource = R.drawable.ic_permian_period,
                    creatures = listOf("Dimetrodon", "Gorgonops", "Scutosaurus")
                )
            )
            "cenozoic" -> listOf(
                Period(
                    id = "paleogene",
                    name = getString(R.string.period_paleogene),
                    description = getString(R.string.period_paleogene_desc),
                    timeRange = "66 - 23 Ma",
                    eraId = eraId,
                    iconResource = R.drawable.ic_paleogene_period,
                    creatures = listOf("Basilosaurus", "Uintatherium", "Hyaenodon")
                ),
                Period(
                    id = "neogene",
                    name = getString(R.string.period_neogene),
                    description = getString(R.string.period_neogene_desc),
                    timeRange = "23 - 2.6 Ma",
                    eraId = eraId,
                    iconResource = R.drawable.ic_neogene_period,
                    creatures = listOf("Megalodon", "Sivatherium", "Australopithecus")
                ),
                Period(
                    id = "quaternary",
                    name = getString(R.string.period_quaternary),
                    description = getString(R.string.period_quaternary_desc),
                    timeRange = "2.6 Ma - presente",
                    eraId = eraId,
                    iconResource = R.drawable.ic_quaternary_period,
                    creatures = listOf("Mammuthus", "Smilodon", "Homo sapiens")
                )
            )
            else -> emptyList()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right)
    }
}