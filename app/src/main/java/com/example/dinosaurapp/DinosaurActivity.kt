package com.example.dinosaurapp

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.widget.ImageView
import android.widget.Toast

/**
 * Actividad que muestra las criaturas de un período específico
 * Nivel 3 de la jerarquía: Criaturas específicas con información detallada
 */
class DinosaurActivity : AppCompatActivity() {

    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var ivPeriodBackground: ImageView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private var periodId: String = ""
    private var periodName: String = ""
    private var eraName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dinosaur)

        getIntentData()
        initializeViews()
        setupToolbar()
        setupViewPager()
        applyEnterAnimation()
    }

    private fun getIntentData() {
        periodId = intent.getStringExtra("period_id") ?: ""
        periodName = intent.getStringExtra("period_name") ?: ""
        eraName = intent.getStringExtra("era_name") ?: ""
    }

    private fun initializeViews() {
        collapsingToolbar = findViewById(R.id.collapsingToolbar)
        toolbar = findViewById(R.id.toolbar)
        ivPeriodBackground = findViewById(R.id.ivPeriodBackground)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        // Configurar título
        collapsingToolbar.title = "Criaturas del $periodName"
        
        // Configurar imagen de fondo según el período
        val backgroundResource = getPeriodBackgroundResource(periodId)
        ivPeriodBackground.setImageResource(backgroundResource)
    }

    private fun setupViewPager() {
        val adapter = DinosaurPagerAdapter(this, periodId)
        viewPager.adapter = adapter
        
        // Desactivar el offscreen page limit para mejor rendimiento con contenido dinámico
        viewPager.offscreenPageLimit = 1
        
        // Conectar TabLayout con ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Criaturas"
                1 -> "Explorar"
                2 -> "Información"
                else -> "Tab $position"
            }
        }.attach()
        
        // Aplicar animación al ViewPager
        val slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_fade_in)
        viewPager.startAnimation(slideInAnimation)
    }



    private fun applyEnterAnimation() {
        val slideInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right)
        findViewById<androidx.coordinatorlayout.widget.CoordinatorLayout>(R.id.dinosaur_coordinator)
            ?.startAnimation(slideInAnimation)
    }



    private fun getPeriodBackgroundResource(periodId: String): Int {
        return when (periodId) {
            "triassic" -> R.drawable.ic_triassic_period
            "jurassic" -> R.drawable.ic_jurassic_period
            "cretaceous" -> R.drawable.ic_cretaceous_period
            "devonian" -> R.drawable.ic_devonian_period
            "carboniferous" -> R.drawable.ic_carboniferous_period
            "permian" -> R.drawable.ic_permian_period
            "paleogene" -> R.drawable.ic_paleogene_period
            "neogene" -> R.drawable.ic_neogene_period
            "quaternary" -> R.drawable.ic_quaternary_period
            else -> R.drawable.ic_triassic_period
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

/**
 * Adapter para el ViewPager2 que maneja los diferentes tabs
 */
class DinosaurPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val periodId: String
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CreaturesListFragment.newInstance(periodId)
            1 -> ExploreFragment.newInstance(periodId)
            2 -> PeriodInfoFragment.newInstance(periodId)
            else -> CreaturesListFragment.newInstance(periodId)
        }
    }
}