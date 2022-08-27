package ru.bibaboba.socialautoanswer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.core.view.contains
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.bibaboba.core_utils.ItemChoosingListener
import ru.bibaboba.core_utils.ChoosingListener
import ru.bibaboba.socialautoanswer.databinding.ActivityMainBinding
import ru.bibaboba.socialautoanswer.databinding.ContactActionToolbarBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val toolbarBinding: ContactActionToolbarBinding by lazy { ContactActionToolbarBinding.inflate(layoutInflater) }
    private val navController : NavController by lazy { findNavController(R.id.navFragment) }
    private val layout: LinearLayout by lazy { binding.mainLayout }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainBottomNavigation.setupWithNavController(navController)

        //toolbarBinding.stopSelecting.setOnClickListener{ onBackPressed() }

        ChoosingListener.setOnItemChoosingListener(object: ItemChoosingListener{

            override fun isChoosing(delete: () -> Unit, cancel: () -> Unit) {
                layout.removeView(binding.mainBottomNavigation)
                layout.addView(toolbarBinding.root)

                toolbarBinding.deleteSelected.setOnClickListener{ delete() }
                toolbarBinding.stopSelecting.setOnClickListener{ cancel() }
            }

            override fun notChoosing() {
                layout.removeView(toolbarBinding.root)
                layout.addView(binding.mainBottomNavigation)
            }

        })

    }
}