package cn.cg.bean;

/**
 * Created by ChenGeng on 2017/12/7.
 * 停牌股票
 */
public class TingPaiStockBean implements Comparable<TingPaiStockBean> {

    private String stockCode;

    private String stockName;

    private String stopDateTime;

    private String continueDateTime;

    private String type;

    private String reason;

    private String stopDate;

    private String continueDate;

    private String marketPlate;

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

    public String getStopDateTime() {
        return stopDateTime;
    }

    public void setStopDateTime(String stopDateTime) {
        this.stopDateTime = stopDateTime;
    }

    public String getContinueDateTime() {
        return continueDateTime;
    }

    public void setContinueDateTime(String continueDateTime) {
        this.continueDateTime = continueDateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getContinueDate() {
        return continueDate;
    }

    public void setContinueDate(String continueDate) {
        this.continueDate = continueDate;
    }

    public String getMarketPlate() {
        return marketPlate;
    }

    public void setMarketPlate(String marketPlate) {
        this.marketPlate = marketPlate;
    }

    @Override
    public String toString() {
        return "TingPaiStockBean{" +
                "stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", stopDateTime='" + stopDateTime + '\'' +
                ", continueDateTime='" + continueDateTime + '\'' +
                ", type='" + type + '\'' +
                ", reason='" + reason + '\'' +
                ", stopDate='" + stopDate + '\'' +
                ", continueDate='" + continueDate + '\'' +
                ", marketPlate='" + marketPlate + '\'' +
                '}';
    }

    @Override
    public int compareTo(TingPaiStockBean o) {
        return this.getStockCode().compareTo(o.getStockCode());
    }
}
