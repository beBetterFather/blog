package com.jsw.dto;

import java.util.List;
import java.util.Map;

public class EchartDto {

    private List<List<Object>>dataX;

    private List<List<Object>> dataY;


    private List<String> labels;

    public List<List<Object>> getDataX() {
        return dataX;
    }

    public void setDataX(List<List<Object>> dataX) {
        this.dataX = dataX;
    }

    public List<List<Object>> getDataY() {
        return dataY;
    }

    public void setDataY(List<List<Object>> dataY) {
        this.dataY = dataY;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
