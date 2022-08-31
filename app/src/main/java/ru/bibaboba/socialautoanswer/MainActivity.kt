package ru.bibaboba.socialautoanswer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.bibaboba.core_android.BackPressed
import ru.bibaboba.core_utils.ItemSelectingListener
import ru.bibaboba.core_utils.SelectingListener
import ru.bibaboba.socialautoanswer.databinding.ActivityMainBinding
import ru.bibaboba.socialautoanswer.databinding.ContactActionToolbarBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val toolbarBinding: ContactActionToolbarBinding by lazy { ContactActionToolbarBinding.inflate(layoutInflater) }
    private val navController : NavController by lazy { findNavController(R.id.navFragment) }
    private val layout: LinearLayout by lazy { binding.mainLayout }

    private lateinit var backPressed: () -> Unit
    private var backPressedConstant = BackPressed.STANDARD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.mainBottomNavigation.setupWithNavController(navController)

        SelectingListener.setOnItemSelectingListener(object: ItemSelectingListener{

            override fun isChoosing(delete: () -> Unit, cancel: () -> Unit) {
                layout.removeView(binding.mainBottomNavigation)
                layout.addView(toolbarBinding.root)

                setOnBackPressedListener(BackPressed.UNSELECT_ALL, cancel)

                toolbarBinding.deleteSelected.setOnClickListener{ delete() }
                toolbarBinding.stopSelecting.setOnClickListener{ onBackPressed() }
            }

            override fun notChoosing() {
                layout.removeView(toolbarBinding.root)
                layout.addView(binding.mainBottomNavigation)

                setOnBackPressedListener(BackPressed.STANDARD)

                toolbarBinding.stopSelecting.setOnClickListener{ onBackPressed() }
            }

        })

    }

    fun setOnBackPressedListener(backPressedConstant: BackPressed, listener: () -> Unit = {}){
        backPressed = listener
        this.backPressedConstant = backPressedConstant
    }


    override fun onBackPressed() {
        if(backPressedConstant == BackPressed.STANDARD)
            super.onBackPressed()
        if(backPressedConstant == BackPressed.UNSELECT_ALL)
            backPressed()
    }

}