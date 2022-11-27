package com.example.alfa_bank_android_app_teacher

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.alfa_bank_android_app_teacher.databinding.ActivityMainBinding
import com.example.alfa_bank_android_app_teacher.ui.classes.ClassesFragment
import com.example.alfa_bank_android_app_teacher.ui.notification.NotificationFragment
import com.example.alfa_bank_android_app_teacher.ui.setting.SettingFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeNavigation()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun initializeNavigation() {
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.children -> {
                    goToFragment(ClassesFragment())
                    binding.appBarMain.toolbarTitle.text = "Мои дети"
                    true
                }
                R.id.notification -> {
                    goToFragment(NotificationFragment())
                    binding.appBarMain.toolbarTitle.text = "Уведомления"
                    true
                }
                R.id.settings -> {
                    goToFragment(SettingFragment())
                    binding.appBarMain.toolbarTitle.text = "Настройки"
                    true
                }
                R.id.exit -> {
                    lifecycleScope.launch(context = Dispatchers.Main){
                        delay(20)
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                else -> {
                    true
                }
            }
        }

    }

    private fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, fragment)
            .commit()
        closeDrawerLayout(fragment)
    }

    private fun closeDrawerLayout(fragment: Fragment?) {
        lifecycleScope.launch(context = Dispatchers.Main) {
            delay(20)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun confirmExit(funAfterConfirm: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("School food")
        builder.setMessage("Вы точно хотите выйти?")
        builder.setCancelable(true)
        builder.setPositiveButton("Нет"
        ) { _, _ -> }
        builder.setNegativeButton("Да"){_,_->funAfterConfirm.invoke()}
        builder.show()
    }

    //private fun initializeButtonNav() {
    //    binding.appBarMain.buttonNav.setOnClickListener {
    //        binding.drawerLayout.openDrawer(GravityCompat.START)
    //    }
    //}

   
}