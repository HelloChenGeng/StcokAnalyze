package cn.cg.bean;

import java.util.Objects;

/**
 * Created by ChenGeng on 2017/12/17.
 */
public class KLineDotBean {

    /** 当前编号 */
    long kDotNo;

    /** 股票代码 */
    String stockCode;

    /** k线类型 */
    int type;

    /** 交易日期 */
    String tradeDate;

    /** 交易时间 */
    String tradeTime;

    /** 周期开始时间 */
    String startTime;

    /** 周期结束时间 */
    String endTime;

    /** 开盘价 */
    float startPrice;

    /** 收盘价 */
    float endPrice;

    /** 成交量 */
    int amt;

    double ema12;

    double ema26;

    double dif;

    double dea;

    double macd;

    public long getkDotNo() {
        return kDotNo;
    }

    public void setkDotNo(long kDotNo) {
        this.kDotNo = kDotNo;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public float getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(float startPrice) {
        this.startPrice = startPrice;
    }

    public float getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(float endPrice) {
        this.endPrice = endPrice;
    }

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public double getEma12() {
        return ema12;
    }

    public void setEma12(double ema12) {
        this.ema12 = ema12;
    }

    public double getEma26() {
        return ema26;
    }

    public void setEma26(double ema26) {
        this.ema26 = ema26;
    }

    public double getDif() {
        return dif;
    }

    public void setDif(double dif) {
        this.dif = dif;
    }

    public double getDea() {
        return dea;
    }

    public void setDea(double dea) {
        this.dea = dea;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        if(tradeTime!=null && tradeTime.length()>8){
            tradeTime = tradeTime.substring(0, 6) + "00";
        }
        this.tradeTime = tradeTime;
    }
}
