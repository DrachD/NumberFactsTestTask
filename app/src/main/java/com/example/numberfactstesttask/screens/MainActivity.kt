package com.example.numberfactstesttask.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.numberfactstesttask.R
import com.example.numberfactstesttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        navController = getRootNavController()
        prepareRootNavController(navController!!)
        NavigationUI.setupActionBarWithNavController(this, navController!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        return (navController?.navigateUp() ?: false) || super.onSupportNavigateUp()
    }

    private fun getRootNavController(): NavController {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        return navHost.navController
    }

    private fun prepareRootNavController(navController: NavController) {
        val graph = navController.navInflater.inflate(getMainNavigationGraphId())
        graph.setStartDestination(getHomeFragmentId())
        navController.graph = graph
    }

    private fun getMainNavigationGraphId() = R.navigation.main_graph
    private fun getHomeFragmentId() = R.id.homeFragment
}