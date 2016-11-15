package com.lg.db;

import com.lg.model.PositionDetail;

import java.util.List;

/**
 * Created by dutc on 2016/11/11.
 */
public interface LGReportDao {
    List<PositionDetail> GetAllPosition();
}
