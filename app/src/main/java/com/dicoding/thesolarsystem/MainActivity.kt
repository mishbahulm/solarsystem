package com.dicoding.thesolarsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvSolarSystems: RecyclerView
    private var list: ArrayList<SolarSystem> = arrayListOf()
    private var title: String = "Mode List"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvSolarSystems = findViewById(R.id.rv_solar_systems)
        rvSolarSystems.setHasFixedSize(true)

        list.addAll(SolarSystemData.listData)
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerList() {
        rvSolarSystems.layoutManager = LinearLayoutManager(this)
        val listSolarSystemAdapter = ListSolarSystemAdapter(list)
        rvSolarSystems.adapter = listSolarSystemAdapter

        listSolarSystemAdapter.setOnItemClickCallback(object : ListSolarSystemAdapter.OnItemClickCallback {
            override fun onItemClicked(data: SolarSystem) {
                showSelectedSolarSystem(data)
            }
        })
    }

    private fun showRecyclerGrid() {
        rvSolarSystems.layoutManager = GridLayoutManager(this, 2)
        val gridSolarSystemAdapter = GridSolarSystemAdapter(list)
        rvSolarSystems.adapter = gridSolarSystemAdapter

        gridSolarSystemAdapter.setOnItemClickCallback(object : GridSolarSystemAdapter.OnItemClickCallback {
            override fun onItemClicked(data: SolarSystem) {
                showSelectedSolarSystem(data)
            }
        })
    }

    private fun showRecyclerCardView() {
        rvSolarSystems.layoutManager = LinearLayoutManager(this)
        val cardViewSolarSystemAdapter = CardViewSolarSystemAdapter(list)
        rvSolarSystems.adapter = cardViewSolarSystemAdapter

        cardViewSolarSystemAdapter.setOnShareClickCallback(object : CardViewSolarSystemAdapter.OnShareClickCallback {
            override fun onShareClicked(data: SolarSystem) {
                shareSelectedSolarSystem(data)
            }
        })
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showSelectedSolarSystem(planet: SolarSystem) {
        val moveWithDataIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_NAME, planet.name)
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_DETAIL, planet.detail)
        moveWithDataIntent.putExtra(DetailActivity.EXTRA_PHOTO, planet.photo)
        startActivity(moveWithDataIntent)
//        Toast.makeText(this, "Kamu memilih " + planet.name, Toast.LENGTH_SHORT).show()
    }

    private fun shareSelectedSolarSystem(planet: SolarSystem) {
        val sendIntent = Intent()

        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, planet.name + "\n" +planet.detail)
        sendIntent.setType("text/plain")

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }

            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }

            R.id.action_cardview -> {
                title = "Mode CardView"
                showRecyclerCardView()
            }

            R.id.action_about -> {
                title = "About"
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        setActionBarTitle(title)
    }
}
