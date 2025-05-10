package com.example.bmicalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*

class BMIChartActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    // Przykładowe dane BMI
    private val bmiData = listOf(
        BMIEntry("2025-01-01", 24.6f),
        BMIEntry("2025-02-01", 24.2f),
        BMIEntry("2025-03-01", 23.8f),
        BMIEntry("2025-04-01", 23.4f),
        BMIEntry("2025-05-01", 22.9f)
    )

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_chart_webview)

        webView = findViewById(R.id.webViewChart)

        // Włączenie JavaScript w WebView
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Wygenerowanie zawartości HTML z wykresem
        val htmlContent = generateChartHtml()

        // Wyświetlenie wykresu w WebView
        webView.loadDataWithBaseURL(
            "https://www.google.com",
            htmlContent,
            "text/html",
            "UTF-8",
            null
        )
    }

    private fun generateChartHtml(): String {
        // Przygotowanie danych dla Google Charts
        val dataArray = JSONArray()

        // Dodanie nagłówków
        val headers = JSONArray()
        headers.put("Data")
        headers.put("BMI")
        dataArray.put(headers)

        // Dodanie danych BMI
        for (entry in bmiData) {
            val row = JSONArray()
            row.put(formatDate(entry.date))
            row.put(entry.bmiValue)
            dataArray.put(row)
        }

        // Stworzenie HTML z wykresem Google Charts
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                <script type="text/javascript">
                    google.charts.load('current', {'packages':['corechart']});
                    google.charts.setOnLoadCallback(drawChart);
                    
                    function drawChart() {
                        var data = google.visualization.arrayToDataTable(${dataArray});
                        
                        var options = {
                            title: 'Zmiany BMI w czasie',
                            curveType: 'function',
                            legend: { position: 'bottom' },
                            hAxis: {
                                title: 'Data'
                            },
                            vAxis: {
                                title: 'BMI',
                                viewWindow: {
                                    min: 18,
                                    max: 35
                                },
                                ticks: [
                                    {v: 18.5, f: '18.5 - Niedowaga'},
                                    {v: 25, f: '25 - Nadwaga'},
                                    {v: 30, f: '30 - Otyłość'}
                                ]
                            },
                            colors: ['#4CAF50'],
                            lineWidth: 3,
                            pointSize: 7,
                            backgroundColor: { fill: 'transparent' }
                        };
                        
                        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
                        chart.draw(data, options);
                    }
                </script>
                <style>
                    body, html {
                        margin: 0;
                        padding: 0;
                        width: 100%;
                        height: 100%;
                    }
                    #chart_div {
                        width: 100%;
                        height: 100%;
                    }
                </style>
            </head>
            <body>
                <div id="chart_div"></div>
            </body>
            </html>
        """.trimIndent()
    }

    private fun formatDate(dateString: String): String {
        // Konwersja formatu daty dla czytelności
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateString) ?: return dateString
        return outputFormat.format(date)
    }

    // Klasa pomocnicza do przechowywania danych BMI
    data class BMIEntry(val date: String, val bmiValue: Float)
}