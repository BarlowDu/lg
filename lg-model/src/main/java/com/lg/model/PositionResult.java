package com.lg.model;

import java.util.List;

/**
 * Created by dutc on 2016/11/8.
 */
public class PositionResult {

    private int totalCount;
    private List<PositionDetail> result;


    public List<PositionDetail> getResult() {
        return result;
    }

    public void setResult(List<PositionDetail> position) {
        this.result = position;
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
