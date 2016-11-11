package com.lg.model;

import java.util.List;

/**
 * Created by dutc on 2016/11/8.
 */
public class LagouContent {
    private int totalCount;
    private List<PositionResult> positionResult;

    public List<PositionResult> getPositionResult() {
        return positionResult;
    }

    public void setPositionResult(List<PositionResult> positionResult) {
        this.positionResult = positionResult;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
