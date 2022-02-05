package com.nursultan.cryptoapp.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jjoe64.graphview.LabelFormatter
import com.jjoe64.graphview.Viewport
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.nursultan.cryptoapp.databinding.ActivityCoinDetailBinding
import com.nursultan.cryptoapp.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.round

class CoinDetailFragment : Fragment() {

    private lateinit var fromSymbol: String

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding is null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CoinDetailViewModel
    private val component by lazy {
        (requireActivity().application as CryptoApp).component
            .coinDetailSubcomponent().create("32423")

    }

    override fun onAttach(context: Context) {
        component.inject(this)
        fromSymbol = getSymbol()
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[CoinDetailViewModel::class.java]
        viewModel.getCoinInfo(fromSymbol).observe(viewLifecycleOwner) {
            with(binding)
            {
                tvFSym.text = it.fromSymbol
                tvTSym.text = it.toSymbol
                tvPrice10.text = it.price.toString()
                tvMinDay.text = it.lowDay.toString()
                tvMaxDay.text = it.highDay.toString()
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.lastUpdate
                Picasso.get()
                    .load(it.imageUrl)
                    .into(ivLogoCoin10)
            }
        }
        viewModel.loadCoinDailyInfo(fromSymbol)
        val dataFormat = SimpleDateFormat("dd.MM", Locale.getDefault())
        dataFormat.timeZone = TimeZone.getDefault()
        binding.graphCoinPrice.title = "За последнюю неделю"
        // graphCoinPrice.gridLabelRenderer.numVerticalLabels=5
        // graphCoinPrice.gridLabelRenderer.numHorizontalLabels=4
        viewModel.getCoinDailyInfo(fromSymbol).observe(viewLifecycleOwner) {

            if (it.isNotEmpty()) {
                val series = LineGraphSeries<DataPoint>()
                series.color = Color.GREEN
                series.isDrawDataPoints = true
                series.dataPointsRadius = 10F
                series.thickness = 8
                for (di in it) {
                    series.appendData(
                        DataPoint(di.time.toDouble(), di.close),
                        true,
                        8
                    )
                }
                with(binding)
                {
                    graphCoinPrice.addSeries(series)
                    // graphCoinPrice.gridLabelRenderer.setHorizontalLabelsAngle(90)
                    graphCoinPrice.viewport.isXAxisBoundsManual = true
                    graphCoinPrice.viewport.setMinX((it[0].time - 160_000).toDouble())
                    graphCoinPrice.viewport.setMaxX((it[6].time + 160_000).toDouble())
                }

            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSymbol(): String {
        return requireArguments().getString(EXTRA_FROM_SYMBOL, DEFAULT_FROM_SYMBOL)
    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fSym"
        const val DEFAULT_FROM_SYMBOL = ""

        fun newInstance(fromSymbol: String): CoinDetailFragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromSymbol)
                }
            }
        }
    }
}
