package com.crumet.khata

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_bar)
        startActivity(Intent(this, LoginActivity::class.java))
        fab.setOnClickListener {
            startActivity(Intent(this, AddRecordActivity::class.java))

        }


        bottom_bar.setNavigationOnClickListener {

            CustomDialog().show(supportFragmentManager, "dialog")
        }
        setUpPieChart()
        setUpLineChart()

    }

    private fun setUpLineChart() {
        var lineEntries1 = ArrayList<Entry>()
        lineEntries1.add(Entry(1f, 1f))
        lineEntries1.add(Entry(2f, 3f))
        lineEntries1.add(Entry(3f, 4f))
        lineEntries1.add(Entry(4f, 2f))
        lineEntries1.add(Entry(5f, 1f))

        val dataSet1 = LineDataSet(lineEntries1, "Income")
        dataSet1.color = ColorTemplate.getHoloBlue()

        var lineEntries2 = ArrayList<Entry>()
        lineEntries2.add(Entry(3f, 2f))
        lineEntries2.add(Entry(2f, 3f))
        lineEntries2.add(Entry(4f, 4f))
        lineEntries2.add(Entry(5f, 5f))
        lineEntries2.add(Entry(7f, 2f))

        val dataSet2 = LineDataSet(lineEntries2, "Expense")
        dataSet2.color = Color.RED

        val lineData = LineData(dataSet1, dataSet2)
        lineChart.data = lineData
        lineChart.invalidate()

    }

    private fun setUpPieChart() {
        pichart.setUsePercentValues(true)
        pichart.description.isEnabled = false
        pichart.setDrawCenterText(true)
        pichart.setDrawEntryLabels(false)

        val legend = pichart.legend
        legend.position = Legend.LegendPosition.BELOW_CHART_CENTER

        var entry = ArrayList<PieEntry>()
        entry.add(PieEntry(8f, "jan", 1))
        entry.add(PieEntry(15f, "feb", 2))
        entry.add(PieEntry(12f, "mar", 3))
        entry.add(PieEntry(25f, "apr", 4))
        entry.add(PieEntry(23f, "may", 5))
        entry.add(PieEntry(17f, "jun", 6))


        val piDataSet = PieDataSet(entry, "")
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
