package com.jsw.service;

import com.jsw.dto.EchartDto;
import com.jsw.entity.Twork;

public interface TworkService extends IService<Twork>{

    void updateOverTime();

    EchartDto echarts();

}
