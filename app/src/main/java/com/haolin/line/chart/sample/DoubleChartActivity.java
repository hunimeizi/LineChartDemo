package com.haolin.line.chart.sample;

import android.app.Activity;
import android.os.Bundle;

import com.haolin.line.chart.sample.AAChartCoreLib.AAChartConfiger.AAChartModel;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartConfiger.AAChartView;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartConfiger.AAOptionsConstructor;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartConfiger.AASeriesElement;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartEnum.AAChartStackingType;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartEnum.AAChartSymbolStyleType;
import com.haolin.line.chart.sample.AAChartCoreLib.AAChartEnum.AAChartType;
import com.haolin.line.chart.sample.AAChartCoreLib.AAOptionsModel.AAOptions;
import com.haolin.line.chart.sample.AAChartCoreLib.AAOptionsModel.AAStyle;
import com.haolin.line.chart.sample.AAChartCoreLib.AATools.AAGradientColor;
import com.haolin.line.chart.sample.AAChartCoreLib.AATools.AALinearGradientDirection;

import java.util.Map;

/**
 * 创建人：lyb
 * 创建时间：2020/2/27 16:47
 * class:
 */
public class DoubleChartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_chart);
        AAChartView aaChartView = findViewById(R.id.AAChartView);
        aaChartView.aa_drawChartWithChartOptions(doubleChartOptions());
    }

    private AAOptions doubleChartOptions() {
        String[] categories = new String[]{"01/13", "01/14", "01/15", "01/16", "01/17", "01/18","01/19"};
        String categoryJSArrStr = javaScriptArrayStringWithJavaArray(categories);

        String xAxisLabelsFormatter =
                "function () {\n" +
                        "        return categoryJSArrStr[this.value];\n" +
                        "        }";
        xAxisLabelsFormatter = xAxisLabelsFormatter.replace("categoryJSArrStr",categoryJSArrStr);
        Map gradientColorDic1 = AAGradientColor.linearGradient(
                AALinearGradientDirection.ToBottom,
                "rgba(252,180,128,1)",//DodgerBlue, alpha 透明度 1
                "rgba(252,180,128,0.1)"//DodgerBlue, alpha 透明度 0.1
        );
        Map gradientColorDic11 = AAGradientColor.linearGradient(
                AALinearGradientDirection.ToBottom,
                "rgba(225,237,255,1)",//DodgerBlue, alpha 透明度 1
                "rgba(225,237,255,0.1)"//DodgerBlue, alpha 透明度 0.1
        );

        AASeriesElement element1 = new AASeriesElement()
                .lineWidth(5f)
                .fillOpacity(0.5f)
                .data(new Object[]{32,33,35,45,43,41,37})
                .lineWidth(2f)
                .color("rgba(255,102,0,1)")
                .fillColor(gradientColorDic1);

        AASeriesElement element2 = new AASeriesElement()
                .lineWidth(5f)
                .fillOpacity(0.5f)
                .data(new Object[]{22,23,26,30,32,21,12})
                .lineWidth(1f)
                .color("rgba(81,147,233,1)")
                .fillColor(gradientColorDic11);

        AAChartModel aaChartModel = new AAChartModel()
                .chartType(AAChartType.Area)
                .title("")
                .yAxisTitle("")//设置Y轴标题
                .yAxisGridLineWidth(0f)
                .yAxisLineWidth(1f)
                .dataLabelsEnabled(false)
                .markerRadius(0f)
                .series(new AASeriesElement[]{element1, element2})
                .stacking(AAChartStackingType.Normal)
                .markerSymbolStyle(AAChartSymbolStyleType.BorderBlank)
                .legendEnabled(false);

        String tooltipFormatter = "function () { return  '<b>'+categoryJSArrStr[this.x]+ '</b>' + '<br/>' +\"最高 \" + this.points[0].y + \"℃\" + '<br/>'+ \"最低 \"  + this.points[1].y + \"℃\"; }";
        AAOptions aaOptions = AAOptionsConstructor.configureChartOptions(aaChartModel);
        tooltipFormatter = tooltipFormatter.replace("categoryJSArrStr",categoryJSArrStr);
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
}
