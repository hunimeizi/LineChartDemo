package com.haolin.line.chart.sample;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haolin.line.chart.sample.AAChartCoreLib.AAChartConfiger.AAChartModel;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartConfiger.AAChartView;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartConfiger.AAOptionsConstructor;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartConfiger.AASeriesElement;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartEnum.AAChartSymbolStyleType;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartEnum.AAChartSymbolType;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartEnum.AAChartType;
import com.haolin.line.chart.sample.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.haolin.line.chart.sample.AAChartCoreLib.AAOptionsModel.AAStyle;
import com.haolin.line.chart.sample.AAChartCoreLib.AATools.AAGradientColor;
import com.haolin.line.chart.sample.AAChartCoreLib.AATools.AALinearGradientDirection;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AAChartView aaChartView = findViewById(R.id.AAChartView);
        aaChartView.aa_drawChartWithChartOptions(configureAreaChartThreshold());
    }

   private AAOptions configureAreaChartThreshold() {
        Map gradientColorDic1 = AAGradientColor.linearGradient(
                AALinearGradientDirection.ToBottom,
                "rgba(252,180,128,1)",//DodgerBlue, alpha 透明度 1
                "rgba(252,180,128,0.1)"//DodgerBlue, alpha 透明度 0.1
        );

       String[] categories = new String[]{"00:00", "01:00", "02:00", "03:00", "04:00", "05:00","06:00", "07:00",
               "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
               "19:00", "20:00", "21:00", "22:00", "23:00"};
       String categoryJSArrStr = javaScriptArrayStringWithJavaArray(categories);

       String xAxisLabelsFormatter =
               "function () {\n" +
                       "        return categoryJSArrStr[this.value];\n" +
                       "        }";

       xAxisLabelsFormatter = xAxisLabelsFormatter.replace("categoryJSArrStr",categoryJSArrStr);

       AAChartModel aaChartModel = new AAChartModel()
                .chartType(AAChartType.Area)
                .title("")
                .yAxisTitle("")//设置Y轴标题
                .yAxisGridLineWidth(0f)
                .yAxisLineWidth(1f)
                .dataLabelsEnabled(false)//是否显示值
                .markerRadius(0f)
               .xAxisTickInterval(6)
                .markerSymbol(AAChartSymbolType.Circle)
                .markerSymbolStyle(AAChartSymbolStyleType.InnerBlank)
                .legendEnabled(false)
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .fillOpacity(0.5f)
                                .threshold((-40f))
                                .data(new Object[]{-32, 21, 15, 11, 21, 22, 11, 32, 21, 24, 26, 230,-32, 21, 15, 11, 21, 22, 11, 32, 21, 24, 26, 230})
                                .lineWidth(2f)
                                .color("rgba(255,102,0,1)")
                                .fillColor(gradientColorDic1)
                });
       String tooltipFormatter =
               "function () {\n" +
                       "        return  categoryJSArrStr[this.x] +\n" +
                       "        ' ' + this.y + \"℃\";\n" +
                       "        }";
       tooltipFormatter = tooltipFormatter.replace("categoryJSArrStr",categoryJSArrStr);
        AAOptions aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
       aaOptions.xAxis.labels
               .formatter(xAxisLabelsFormatter);
       aaOptions.tooltip
               .useHTML(true)
               .formatter(tooltipFormatter)
               .backgroundColor("#243133")
               .borderColor("#243133")
               .style(new AAStyle()
                       .color("#ffffff")
                       .fontSize(16F));
        return aaOptions;
    }

    private String javaScriptArrayStringWithJavaArray(Object[] javaArray) {
        String originalJsArrStr = "";
        for (int i = 0; i< javaArray.length; i++) {
            Object element = javaArray[i];
            originalJsArrStr = originalJsArrStr + "'" + element.toString() +  "',";
        }

        String finalJSArrStr = "[" + originalJsArrStr + "]";

        return finalJSArrStr;
    }

    public void gotoDoubleChart(View view) {
        startActivity(new Intent(MainActivity.this,DoubleChartActivity.class));
    }
}
