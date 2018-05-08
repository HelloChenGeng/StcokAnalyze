package cn.cg.thread;

import cn.cg.form.AddStockForm;
import cn.cg.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by ChenGeng on 2018/1/11.
 */
@Component
@Scope("prototype")
public class AddStockThread implements Runnable {

    AddStockForm form;

    @Autowired
    StockService service;

    @Override
    public void run() {
        service.addStock(form);
    }

    public AddStockForm getForm() {
        return form;
    }

    public void setForm(AddStockForm form) {
        this.form = form;
    }

}
