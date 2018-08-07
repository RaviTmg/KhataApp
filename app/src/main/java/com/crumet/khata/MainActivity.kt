package com.crumet.khata

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.github.mikephil.charting.utils.ColorTemplate
import android.R.attr.data
import android.content.Intent
import android.graphics.Color
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.formatter.PercentFormatter






class MainActivity : AppCompatActivity() {

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_bar)
        startActivity(Intent(this, AddRecordActivity::class.java))
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        bottom_bar.setNavigationOnClickListener {
            CustomDialog().show(supportFragmentManager, "dialog")
        }
        pichart.setUsePercentValues(true)
        pichart.description.isEnabled = false
        pichart.setDrawCenterText(true)
        pichart.setDrawEntryLabels(false)

        val legend = pichart.legend
        legend.position = Legend.LegendPosition.BELOW_CHART_CENTER

        var entry = ArrayList<PieEntry>()
        entry.add(PieEntry(8f,"jan",1))
        entry.add(PieEntry(15f, "feb",2))
        entry.add(PieEntry(12f, "mar",3))
        entry.add(PieEntry(25f, "apr",4))
        entry.add(PieEntry(23f, "may",5))
        entry.add(PieEntry(17f, "jun",6))


        val piDataSet = PieDataSet(entry,"")
        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        piDataSet.colors = colors
        val piData = PieData(piDataSet)
        piData.setValueFormatter(PercentFormatter())
        piData.setValueTextSize(11f)
        piData.setValueTextColor(Color.WHITE)

        pichart.data = piData

    }


}
