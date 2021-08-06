package com.example.kame.ourmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.home -> {
                menuImage.setImageResource(R.drawable.cook)
                menuText.text = ""
                return true
            }
            R.id.greencurry -> {
                menuImage.setImageResource(R.drawable.greencurry)
                menuText.text = getString(R.string.greencurry_text)
                return true
            }
            R.id.beefcurry -> {
                menuImage.setImageResource(R.drawable.beefcurry)
                menuText.text = getString(R.string.beefcurry_text)
                return true
            }
            R.id.tamoricurry -> {
                menuImage.setImageResource(R.drawable.tamoricurry)
                menuText.text = getString(R.string.tamoricurry_text)
                return true
            }
            R.id.katsucurry -> {
                menuImage.setImageResource(R.drawable.katsucurry)
                menuText.text = getString(R.string.katsucurry_text)
                return true
            }
            R.id.soba1 -> {
                menuImage.setImageResource(R.drawable.soba1)
                menuText.text = getString(R.string.soba1_text)
                return true
            }
            R.id.soba2 -> {
                menuImage.setImageResource(R.drawable.soba2)
                menuText.text = getString(R.string.soba2_text)
                return true
            }
            R.id.ramen1 -> {
                menuImage.setImageResource(R.drawable.ramen1)
                menuText.text = getString(R.string.ramen1_text)
                return true
            }
            R.id.ramen2 -> {
                menuImage.setImageResource(R.drawable.ramen2)
                menuText.text = getString(R.string.ramen2_text)
                return true
            }
            R.id.osyarenabe -> {
                menuImage.setImageResource(R.drawable.nabeyaki)
                menuText.text = getString(R.string.nabeyaki_text)
                return true
            }
            R.id.udon -> {
                menuImage.setImageResource(R.drawable.udon)
                menuText.text = getString(R.string.udon_text)
                return true
            }
            R.id.hiyashi -> {
                menuImage.setImageResource(R.drawable.hiyashi)
                menuText.text = getString(R.string.hiyashi_text)
                return true
            }


        }
        return super.onOptionsItemSelected(item)
    }

}