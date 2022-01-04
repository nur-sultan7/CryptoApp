package com.nursultan.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jjoe64.graphview.LabelFormatter
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.nursultan.cryptoapp.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

class CoinDetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: return
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        viewModel.getCoinPriceInfo(fromSymbol).observe(this, Observer {
            binding.tvFSym.text = it.fromSymbol
            binding.tvTSym.text = it.toSymbol
            binding.tvPrice10.text = it.price.toString()
            binding.tvMinDay.text = it.lowDay.toString()
            binding.tvMaxDay.text = it.highDay.toString()
            binding.tvLastMarket.text = it.lastMarket
            binding.tvLastUpdate.text = it.getFormattedTime()
            Picasso.get()
                .load(it.getFullImageURL())
                .into(binding.ivLogoCoin10)
        })

        viewModel.loadDailyInfoData(fromSymbol)
        val dataFormat = SimpleDateFormat("dd.MM", Locale.getDefault())
        dataFormat.timeZone = TimeZone.getDefault()
        binding.graphCoinPrice.title = "За последнюю неделю"
        val series = LineGraphSeries<DataPoint>()
        series.color = Color.GREEN
        series.isDrawDataPoints = true
        series.dataPointsRadius = 10F
        series.thickness = 8
        // graphCoinPrice.gridLabelRenderer.numVerticalLabels=5
        // graphCoinPrice.gridLabelRenderer.numHorizontalLabels=4
        viewModel.getCoinDailyInfo(fromSymbol).observe(this, Observer {

            if (it.isNotEmpty()) {
                for (di in it) {
                    series.appendData(DataPoint(di.time.toDouble(), di.close), true, 8)
                }
                binding.graphCoinPrice.addSeries(series)
                // graphCoinPrice.gridLabelRenderer.setHorizontalLabelsAngle(90)
                binding.graphCoinPrice.viewport.isXAxisBoundsManual = true
                binding.graphCoinPrice.viewport.setMinX((it[0].time - 160_000).toDouble())
                binding.graphCoinPrice.viewport.setMaxX((it[6].time + 160_000).toDouble())
            }
        })
        binding.graphCoinPrice.gridLabelRenderer.labelFormatter = object : LabelFormatter {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                if (isValueX) {
                    return dataFormat.format(Date(value.toLong() * 1000))
                }
                val roundedValue = round(value * 1000_000) / 1000_000
                return if (roundedValue > 1) {

                    roundedValue.toInt().toString()
                } else
                    roundedValue.toString().take(8)

            }

            override fun setViewport(viewport: Viewport?) {
            }
        }
    }
}
