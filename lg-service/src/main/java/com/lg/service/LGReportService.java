package com.lg.service;

import com.lg.db.LGReportDao;
import com.lg.model.PositionDetail;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dutc on 2016/11/14.
 */
@Service("reportService")
public class LGReportService {
    @Resource(name="redisReportDao")
    private LGReportDao dao;

    public List<PositionDetail> getAllPosition(){
        return dao.GetAllPosition();

    }
}
