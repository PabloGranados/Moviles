package com.example.app1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.app1.ui.TextFieldsFragment
import com.example.app1.ui.BotonesFragment
import com.example.app1.ui.SeleccionFragment
import com.example.app1.ui.ListasFragment
import com.example.app1.ui.InformacionFragment

class MainActivity : AppCompatActivity() {
    
    private var viewPager: ViewPager2? = null
    private var tabLayout: TabLayout? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        try {
            setContentView(R.layout.activity_main)
            
            viewPager = findViewById(R.id.viewPager)
            tabLayout = findViewById(R.id.tabLayout)
            
            setupViewPager()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al inicializar la aplicación: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    private fun setupViewPager() {
        try {
            val adapter = ViewPagerAdapter(this)
            viewPager?.adapter = adapter
            
            // Conectar TabLayout con ViewPager2
            viewPager?.let { vp ->
                tabLayout?.let { tl ->
                    TabLayoutMediator(tl, vp) { tab, position ->
                        tab.text = when (position) {
                            0 -> "Textos"
                            1 -> "Botones"
                            2 -> "Selección"
                            3 -> "Listas"
                            4 -> "Info"
                            else -> "Tab $position"
                        }
                    }.attach()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Error al configurar navegación: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    
    private class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        
        override fun getItemCount(): Int = 5
        
        override fun createFragment(position: Int): Fragment {
            return try {
                when (position) {
                    0 -> TextFieldsFragment()
                    1 -> BotonesFragment()
                    2 -> SeleccionFragment()
                    3 -> ListasFragment()
                    4 -> InformacionFragment()
                    else -> TextFieldsFragment()
                }
            } catch (e: Exception) {
                // En caso de error, devolver un fragment básico
                TextFieldsFragment()
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        viewPager = null
        tabLayout = null
    }
}