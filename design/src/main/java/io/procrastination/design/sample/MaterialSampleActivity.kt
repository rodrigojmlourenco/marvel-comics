package io.procrastination.design.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.procrastination.design.R

class MaterialSampleActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabsLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_showcase)

        viewPager = findViewById(R.id.view_pager)
        tabsLayout = findViewById(R.id.tabs)

        val adapter = MaterialSampleAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        TabLayoutMediator(tabsLayout, viewPager) { tab, position ->
            tab.text = adapter.getTitle(position)
            viewPager.setCurrentItem(position, true)
        }.attach()
    }

    inner class MaterialSampleAdapter(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle
    ) : FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> TypographyFragment()
                1 -> ControlsFragment()
                2 -> FormsFragment()
                else -> error("Unsupported fragment position")
            }
        }

        fun getTitle(position: Int): String {
            return when (position) {
                0 -> getString(R.string.typography)
                1 -> getString(R.string.controls)
                2 -> getString(R.string.form_inputs)
                else -> error("Unsupported fragment position")
            }
        }
    }

    companion object {

        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, MaterialSampleActivity::class.java)
        }
    }
}