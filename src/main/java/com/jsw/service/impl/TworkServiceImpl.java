package com.jsw.service.impl;

import com.jsw.entity.Twork;
import com.jsw.service.TworkService;
import com.jsw.util.Collections3;
import com.jsw.util.lang.DateUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class TworkServiceImpl extends BaseService<Twork> implements TworkService {

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
                updateAll(t);
            });
        }
    }


}
