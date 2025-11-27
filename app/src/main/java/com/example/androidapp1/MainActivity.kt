package com.example.androidapp1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapp1.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // SharedPreferences keys
    private val PREFS_NAME = "androidapp1_prefs"
    private val KEY_COUNT = "key_count"
    private val KEY_STEP = "key_step"
    private val KEY_HISTORY = "key_history"

    private lateinit var prefs: SharedPreferences

    // App data
    private var currentCount = 0
    private var currentStep = 1
    private val historyList = mutableListOf<HistoryItem>()
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Load count + step from storage
        loadDataFromPrefs()
        // ❗ Always reset counter to 0 on app start
        currentCount = 0
        persistCount()


        // Setup RecyclerView + Adapter
        adapter = HistoryAdapter(historyList, object : HistoryAdapter.HistoryListener {
            override fun onDelete(item: HistoryItem, position: Int) {
                deleteHistoryAt(position)
            }
        })
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = adapter

        // ❗ ALWAYS CLEAR HISTORY on app start
        historyList.clear()
        prefs.edit().remove(KEY_HISTORY).apply()
        adapter.notifyDataSetChanged()

        // Initialize UI
        updateCounterText()
        setupStepSpinner(binding.spinnerStep)

        // Buttons
        binding.btnIncrement.setOnClickListener {
            changeCountBy(currentStep)
        }
        binding.btnDecrement.setOnClickListener {
            changeCountBy(-currentStep)
        }
        binding.btnSave.setOnClickListener {
            saveCurrentToHistory()
        }

        // Spinner change listener
        binding.spinnerStep.setSelection(stepIndexFromValue(currentStep))
        binding.spinnerStep.setOnItemSelectedListener(
            SimpleItemSelectedListener { pos ->
                val steps = listOf(1, 5, 10, 50)
                currentStep = steps[pos]
                persistStep()
            }
        )

        // Long press reset counter
        binding.tvCounter.setOnLongClickListener {
            currentCount = 0
            updateCounterText()
            persistCount()
            Toast.makeText(this, "Counter reset to 0", Toast.LENGTH_SHORT).show()
            true
        }
    }

    /** Update counter text */
    private fun updateCounterText() {
        binding.tvCounter.text = currentCount.toString()
    }

    /** Add or subtract count */
    private fun changeCountBy(delta: Int) {
        currentCount += delta
        updateCounterText()
        persistCount()
    }

    /** Save current count to history */
    private fun saveCurrentToHistory() {
        val ts = System.currentTimeMillis()
        val fmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val displayTime = fmt.format(Date(ts))

        val item = HistoryItem(currentCount, ts, displayTime)
        historyList.add(0, item)
        adapter.notifyItemInserted(0)
        binding.rvHistory.scrollToPosition(0)

        persistHistory()
        Toast.makeText(this, "Saved: $currentCount", Toast.LENGTH_SHORT).show()
    }

    /** Delete history item */
    private fun deleteHistoryAt(position: Int) {
        if (position < 0 || position >= historyList.size) return
        historyList.removeAt(position)
        adapter.notifyItemRemoved(position)
        persistHistory()
        Toast.makeText(this, "History item deleted", Toast.LENGTH_SHORT).show()
    }

    /** Save counter to SharedPreferences */
    private fun persistCount() {
        prefs.edit().putInt(KEY_COUNT, currentCount).apply()
    }

    /** Save step to SharedPreferences */
    private fun persistStep() {
        prefs.edit().putInt(KEY_STEP, currentStep).apply()
    }

    /** Save full history as JSON */
    private fun persistHistory() {
        val arr = JSONArray()
        for (item in historyList) {
            val o = JSONObject()
            o.put("value", item.value)
            o.put("timeMillis", item.timeMillis)
            o.put("display", item.displayTime)
            arr.put(o)
        }
        prefs.edit().putString(KEY_HISTORY, arr.toString()).apply()
    }

    /** Load saved counter + step */
    private fun loadDataFromPrefs() {
        currentCount = prefs.getInt(KEY_COUNT, 0)
        currentStep = prefs.getInt(KEY_STEP, 1)

        // Load history (will be cleared on start anyway)
        val json = prefs.getString(KEY_HISTORY, null)
        if (!json.isNullOrBlank()) {
            try {
                val arr = JSONArray(json)
                for (i in 0 until arr.length()) {
                    val o = arr.getJSONObject(i)
                    val value = o.getInt("value")
                    val timeMillis = o.getLong("timeMillis")
                    val display = o.getString("display")
                    historyList.add(HistoryItem(value, timeMillis, display))
                }
            } catch (_: Exception) { }
        }
    }

    /** Spinner setup */
    private fun setupStepSpinner(spinner: Spinner) {
        val steps = listOf("1", "5", "10", "50")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, steps)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun stepIndexFromValue(value: Int): Int {
        return when (value) {
            1 -> 0
            5 -> 1
            10 -> 2
            50 -> 3
            else -> 0
        }
    }
}
