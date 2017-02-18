package com.codinggyd.bean;

import java.io.Serializable;
public class PositionInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	 private Double lDate;
	 private String fundcode;
	 private Integer combiId;
	 private String secucode;
	 private String marketId;
	 private Double currentAmount;
	 private Double marketValue;
	 private Double buyAmout;
	 private Double buyBalance;
	 private Double saleAmout;
	 private Double saleBalance;
	 private Double bufFee;
	 private Double saleFee;
	 private Double onlineAmount;
	 private Double offlineAmout;
	 private Double turnInvest;
	 private String positionFlag;
	 private String price;
	 private String filename;
	 private Integer flag;
	public Double getlDate() {
		return lDate;
	}
	public void setlDate(Double lDate) {
		this.lDate = lDate;
	}
	public String getFundcode() {
		return fundcode;
	}
	public void setFundcode(String fundcode) {
		this.fundcode = fundcode;
	}
	public Integer getCombiId() {
		return combiId;
	}
	public void setCombiId(Integer combiId) {
		this.combiId = combiId;
	}
	public String getSecucode() {
		return secucode;
	}
	public void setSecucode(String secucode) {
		this.secucode = secucode;
	}
	public String getMarketId() {
		return marketId;
	}
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}
	public Double getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(Double currentAmount) {
		this.currentAmount = currentAmount;
	}
	public Double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}
	public Double getBuyAmout() {
		return buyAmout;
	}
	public void setBuyAmout(Double buyAmout) {
		this.buyAmout = buyAmout;
	}
	public Double getBuyBalance() {
		return buyBalance;
	}
	public void setBuyBalance(Double buyBalance) {
		this.buyBalance = buyBalance;
	}
	public Double getSaleAmout() {
		return saleAmout;
	}
	public void setSaleAmout(Double saleAmout) {
		this.saleAmout = saleAmout;
	}
	public Double getSaleBalance() {
		return saleBalance;
	}
	public void setSaleBalance(Double saleBalance) {
		this.saleBalance = saleBalance;
	}
	public Double getBufFee() {
		return bufFee;
	}
	public void setBufFee(Double bufFee) {
		this.bufFee = bufFee;
	}
	public Double getSaleFee() {
		return saleFee;
	}
	public void setSaleFee(Double saleFee) {
		this.saleFee = saleFee;
	}
	public Double getOnlineAmount() {
		return onlineAmount;
	}
	public void setOnlineAmount(Double onlineAmount) {
		this.onlineAmount = onlineAmount;
	}
	public Double getTurnInvest() {
		return turnInvest;
	}
	public void setTurnInvest(Double turnInvest) {
		this.turnInvest = turnInvest;
	}
	public String getPositionFlag() {
		return positionFlag;
	}
	public void setPositionFlag(String positionFlag) {
		this.positionFlag = positionFlag;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Double getOfflineAmout() {
		return offlineAmout;
	}
	public void setOfflineAmout(Double offlineAmout) {
		this.offlineAmout = offlineAmout;
	}
	 
}
