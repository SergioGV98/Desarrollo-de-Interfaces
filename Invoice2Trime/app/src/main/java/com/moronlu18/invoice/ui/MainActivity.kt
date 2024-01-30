package com.moronlu18.invoice.ui

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.moronlu18.invoice.R
import com.moronlu18.invoice.databinding.ActivityMainBinding
import com.moronlu18.invoice.utils.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    //Todas las propiedades son publicas
    //Propiedades de acceso al botón flotante de la Activity principal y b. herramientas

    val fab: FloatingActionButton get() = binding.content.fab
    val toolbar: Toolbar get() = binding.content.toolbar
    val drawer: DrawerLayout get() = binding.drawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Sustituir la appbar por defecto por el widget toolbar de nuetro layout
        setSupportActionBar(binding.content.toolbar)
        //Habilitar el icono home

        //Opcion 1
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        setUpNavigationView()

        //1. Metodos que permite acceder al controlar del Grafo de navegacion
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController
        /*
        //Yo le digo que se configure la barra de navegación con este grafo
        //Opcion 2*/
        //2. Se crea la configuracion de la App bar
        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).setOpenableLayout(binding.drawerLayout).build()
        //3. Que sincronice el DrawerLayout con la appbar
        NavigationUI.setupWithNavController(binding.content.toolbar, navController, appBarConfiguration)

        //setupActionBarWithNavController(navController, appBarConfiguration)

        //Si utilizo estas tres últimos líneas de código puedo resetear la barra de navegación.


        /*val navController = findNavController(R.id.nav_host_fragment_content_main) as NavHostController
        navController = navHostFragment.navController*/


        binding.content.fab.setOnClickListener { view ->
            Snackbar.make(
                view,
                "No me dejes así, pon una función o hazla no visible <_<",
                Snackbar.LENGTH_LONG
            )
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Añadido por mi.
        //binding.fab.visibility = View.VISIBLE

        return when (item.itemId) {
            R.id.action_settings -> {
                //La navegación se realiza directamente utilizando el id del fragment.
                navController.navigate(R.id.settingsFragment)
                true
            }

            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpNavigationView(){
        binding.navView.setNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.draw_clientes -> {
                    navController.navigate(R.id.action_mainFragment_to_nav_graph_customer)
                }
                R.id.draw_articulos -> {
                    navController.navigate(R.id.action_mainFragment_to_nav_graph_item)
                }
                R.id.draw_tareas-> {
                    navController.navigate(R.id.action_mainFragment_to_nav_graph_task)
                }
                R.id.draw_facturas -> {
                    navController.navigate(R.id.action_mainFragment_to_nav_graph_invoice)
                }
                else -> {
                    showToast("Opcion invalida")
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


}