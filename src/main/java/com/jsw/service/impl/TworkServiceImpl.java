package com.jsw.service.impl;

import com.jsw.dto.EchartDto;
import com.jsw.entity.Twork;
import com.jsw.service.TworkService;
import com.jsw.util.Collections3;
import com.jsw.util.collect.ListUtils;
import com.jsw.util.collect.MapUtils;
import com.jsw.util.lang.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TworkServiceImpl extends BaseService<Twork> implements TworkService {

    @Value(value = "${nari.normal.worktime}")
    private Double nariWorkTime;

    /**
     * 统计每天的加班时长
     */
    @Override
    public void updateOverTime(){
        Example tworkExample=new Example(Twork.class);
//        tworkExample.or().andEqualTo("workDate", new Date());
        List<Twork> list = selectByExample(tworkExample);
        if(!Collections3.isEmpty(list)){
            list.stream().filter(t-> t.getStartTime()!=null && t.getEndTime() != null).forEach(t->{
                double diff = DateUtils.getOverTime(t.getStartTime(), t.getEndTime());
                t.setOverTime(diff);

                BigDecimal b = new BigDecimal(diff);
                b = b.subtract(new BigDecimal(nariWorkTime)).setScale(2, BigDecimal.ROUND_HALF_UP);
                t.setHardTime(b.doubleValue());
                updateAll(t);
            });
        }
    }


    public EchartDto echarts(){
        EchartDto dto = new EchartDto();

        List<List<Object>> data1 = ListUtils.newLinkedList();
        List<List<Object>> data2 = ListUtils.newLinkedList();
        List<String> labels = Arrays.asList("工作时长", "加班时长");

        List<Date> dates = DateUtils.lastMonthDays();
        Date endDate   = dates.get(0);
        Date startDate = dates.get(dates.size()-1);
        Example tworkExample=new Example(Twork.class);
        tworkExample.or().andGreaterThanOrEqualTo("workDate", startDate);
        tworkExample.or().andLessThanOrEqualTo("workDate", endDate);
        List<Twork> list = selectByExample(tworkExample);

        if(!Collections3.isEmpty(list)){
            //统计每天加班时长，封装map key为时间戳 value 为上班时长
            Map<Long, Double> overTimemaps = MapUtils.newLinkedHashMap();
            Map<Long, Double> hardTimemaps = MapUtils.newLinkedHashMap();
            list.stream().forEach(t->{
                overTimemaps.put(t.getWorkDate().getTime(), t.getOverTime());
                hardTimemaps.put(t.getWorkDate().getTime(), t.getHardTime());
            });

            dates.stream().forEach(t->{
                List<Object> ll = ListUtils.newLinkedList();
                ll.add(t.getTime());
                ll.add(overTimemaps.get(t.getTime())==null? 0 : overTimemaps.get(t.getTime()));

                List<Object> hh = ListUtils.newLinkedList();
                hh.add(t.getTime());
                hh.add(hardTimemaps.get(t.getTime())==null? 0 : hardTimemaps.get(t.getTime()));
                data1.add(ll);
                data2.add(hh);
            });
        }

        dto.setDataX(data1);
        dto.setDataY(data2);
        dto.setLabels(labels);
        return dto;
    }


}
