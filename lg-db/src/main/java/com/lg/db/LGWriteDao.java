package com.lg.db;


import com.lg.model.PositionDetail;

import java.io.IOException;


/**
 * Created by dutc on 2016/11/11.
 */
public interface LGWriteDao {
    void WritePositionDetail(PositionDetail p)throws IOException;
}
