package cn.cg.bean;

/**
 * Created by ChenGeng on 2018/1/18.
 */
public class TradeDetailBean implements Comparable<TradeDetailBean> {

    private String code;

    private String tradeDate;

    private String tradeTime;

    private float price;

    private int amt;

    private int sellFlag;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        this.tradeTime = tradeTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public int getSellFlag() {
        return sellFlag;
    }

    public void setSellFlag(int sellFlag) {
        this.sellFlag = sellFlag;
    }

    @Override
    public int compareTo(TradeDetailBean o) {
        return this.getTradeTime().compareTo(o.getTradeTime());
    }
}
