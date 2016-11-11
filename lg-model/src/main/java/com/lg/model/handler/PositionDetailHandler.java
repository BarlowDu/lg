package com.lg.model.handler;

import com.lg.model.PositionDetail;
import org.springframework.stereotype.Component;

/**
 * Created by dutc on 2016/11/10.
 */
@Component("detailHandler")
public class PositionDetailHandler {

    public void handle(PositionDetail model){
        if(model==null){
            return;
        }
        handleSalary(model);
        handleWorkYear(model);

    }
    private void handleSalary(PositionDetail model){
        String salary=model.getSalary();
        try {
            if (salary != null && salary.length() > 0) {
                salary=salary.toLowerCase();
                String[] ss = salary.split("-");
                if (ss.length > 1) {
                    model.setMinSalary(Integer.valueOf(ss[0].replace('k', ' ').trim()));
                    model.setMaxSalary(Integer.valueOf(ss[1].replace('k', ' ').trim()));
                }
            }
        } catch (Exception ex) {
            System.out.println(salary);
            System.out.println(ex.toString());
        }

    }

    private void handleWorkYear(PositionDetail model){
        String workYear=model.getWorkYear();
        try {
            if (workYear != null && workYear.length() > 0) {
                String[] ss = workYear.split("-");
                if (ss.length > 1) {
                    model.setMinWorkYear(Integer.valueOf(ss[0].replace('年', ' ').trim()));
                    model.setMaxWorkYear(Integer.valueOf(ss[1].replace('年', ' ').trim()));
                }
            }

        } catch (Exception ex) {
            System.out.println(workYear);
            System.out.println(ex.toString());
        }

    }
}
