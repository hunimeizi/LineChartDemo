package com.haolin.line.chart.sample.AAChartCoreLib.AAOptionsModel;


import com.haolin.line.chart.sample.AAChartCoreLib.AAChartConfiger.AASeriesElement;

public class AAWaterfall extends AASeriesElement {
    public String upColor;
    public String color;
    public Float borderWidth;
    public Object[] data;

    public AAWaterfall upColor(String prop) {
        upColor = prop;
        return this;
    }

    public AAWaterfall color(String prop) {
        color = prop;
        return this;
    }

    public AAWaterfall borderWidth(Float prop) {
        borderWidth = prop;
        return this;
    }

    public AAWaterfall data(Object[] prop) {
        data = prop;
        return this;
    }
}
