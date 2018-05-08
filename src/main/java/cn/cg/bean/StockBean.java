package cn.cg.bean;

/**
 * Created by ChenGeng on 2017/12/5.
 */
public class StockBean {

    /** 股票代码 */
    private String stockCode;

    /** 股票名称 */
    private String stockName;

    /** 记录的交易时间 */
    private String lastTimeDealTime;

    /** 获取的页数 */
    private int lastTimePageCount;

    /** 最后一条K线的生成时间 */
    private String lastKLineDealDate;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getLastTimeDealTime() {
        return lastTimeDealTime;
    }

    public void setLastTimeDealTime(String lastTimeDealTime) {
        this.lastTimeDealTime = lastTimeDealTime;
    }

    public int getLastTimePageCount() {
        return lastTimePageCount;
    }

    public void setLastTimePageCount(int lastTimePageCount) {
        this.lastTimePageCount = lastTimePageCount;
    }

    public String getLastKLineDealDate() {
        return lastKLineDealDate;
    }

    public void setLastKLineDealDate(String lastKLineDealDate) {
        this.lastKLineDealDate = lastKLineDealDate;
    }
}