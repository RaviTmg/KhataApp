package com.crumet.khata

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.crumet.khata.adapter.HomeRecyclerAdapter
import com.crumet.khata.database.DatabaseHelper
import com.crumet.khata.database.model.Record
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.sql.Date
import java.util.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userSession = UserSession(this)
        if (userSession.firstTime) {
            userSession.setFirstTime()
            startActivity(Intent(this, SignupActivity::class.java))
        } else {
            userSession.setFirstTime()
            if (!userSession.isUserLoggedIn) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        val databaseHelper = DatabaseHelper(applicationContext)

        setContentView(R.layout.activity_main)
        setSupportActionBar(bottom_bar)
        // databaseHelper.insertRecord("1000","expense","salary","movies")

        var totalIncome = databaseHelper.getSumRecordsByType("income")
        var totalExpense = databaseHelper.getSumRecordsByType("expense")
        var totalBalance = totalIncome - totalExpense
        income_text.text = "Rs. $totalIncome"
        expense_text.text = "Rs. $totalExpense"
        balance_text.text = "Rs. $totalBalance"



        fab.setOnClickListener {
            // startActivity(Intent(this, AddRecordActivity::class.java))

            // val id = databaseHelper.insertRecord("1000","expense","salary","movies")
            // Toast.makeText(applicationContext, "added entry: "+ databaseHelper.getSumRecordsByType("income"),Toast.LENGTH_LONG).show()
            /*val rec = databaseHelper.getRecordsByType("income")
            for ( i: Record in rec){
                Log.d("DATABASE", " ${i.amount} ${i.id} ${i.type} ${i.category} ${i.note} ")
            }
            val re = databaseHelper.getRecord(1)
            Log.d("DATABASE", " ${re.amount} ")

*/
        }

        bottom_bar.setNavigationOnClickListener {

            CustomDialog().show(supportFragmentManager, "dialog")
        }


        val record = databaseHelper.allRecords
        list_recent.layoutManager = LinearLayoutManager(applicationContext)
        list_recent.adapter = HomeRecyclerAdapter(applicationContext, record)

        setUpPieChart()
        setUpLineChart()

    }


    private fun setUpPieChart() {
        pichart.setUsePercentValues(true)
        pichart.description.isEnabled = false
        pichart.setDrawCenterText(true)
        pichart.setDrawEntryLabels(false)
        val databaseHelper = DatabaseHelper(applicationContext)

        val legend = pichart.legend
        legend.position = Legend.LegendPosition.RIGHT_OF_CHART_CENTER

        val recordExpenses = databaseHelper.getRecordsByType("expense")
        val entry = ArrayList<PieEntry>()

        for (expense: Record in recordExpenses) {
            entry.add(PieEntry(expense.amount.toFloat(), expense.category))
        }

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
        piData.setValueTextColor(Color.TRANSPARENT)

        pichart.data = piData
    }

    private fun setUpLineChart() {
        val databaseHelper = DatabaseHelper(applicationContext)
        val recordExpenses = databaseHelper.getRecordsByType("expense")
        val expenseEntries = ArrayList<Entry>()
        for (expense: Record in recordExpenses) {
//            expenseEntries.add(Entry(expense.timestamp(), expense.amount))
        }
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




}
