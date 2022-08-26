package ru.bibaboba.socialautoanswer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.bibaboba.core_utils.ItemChoosingListener
import ru.bibaboba.core_utils.ChoosingListener
import ru.bibaboba.socialautoanswer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navController : NavController by lazy { findNavController(R.id.navFragment) }
    private val layout: LinearLayout by lazy { binding.mainLayout }

    private val toolbar: Toolbar by lazy { Toolbar(this).apply {
    } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainBottomNavigation.setupWithNavController(navController)

        ChoosingListener.setOnItemChoosingListener(object: ItemChoosingListener{

            override fun isChoosing(isChoosing: Boolean) {
                if(isChoosing)
                    layout.addView(toolbar)
                else
                    layout.removeView(toolbar)
            }

        })

    }

}