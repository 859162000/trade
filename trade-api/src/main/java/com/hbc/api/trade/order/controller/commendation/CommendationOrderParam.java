package com.hbc.api.trade.order.controller.commendation;

/**
 * 精品线路
 * @author Jongly Ran
 */
public class CommendationOrderParam {

	private String 	lineSubject;	// 精品线路（线路包车）主题（标题）
	private String 	lineDescription;// 精品线路（线路包车）描述
    private Integer orderGoodsType; // 1接 2 送 4次 5 大长途 6小长途 7市内包车 8固定线路产品 
    private String  goodNo;			// 商品ID
    private Integer orderType;		// 5为精品线路
    
    /**
     * 仅保留非精品线路的GDS下单参数
     */
    public void clearCommendationParam() {
    	lineSubject 	= null;
    	lineDescription = null;
    	orderGoodsType 	= null;
    	goodNo 			= null;
    	orderType 		= null;
    }
    
	/**
	 * @return the lineSubject
	 */
	public String getLineSubject() {
		return lineSubject;
	}
	/**
	 * @param lineSubject the lineSubject to set
	 */
	public void setLineSubject(String lineSubject) {
		this.lineSubject = lineSubject;
	}
	/**
	 * @return the lineDescription
	 */
	public String getLineDescription() {
		return lineDescription;
	}
	/**
	 * @param lineDescription the lineDescription to set
	 */
	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}
	/**
	 * @return the orderGoodsType
	 */
	public Integer getOrderGoodsType() {
		return orderGoodsType;
	}
	/**
	 * @param orderGoodsType the orderGoodsType to set
	 */
	public void setOrderGoodsType(Integer orderGoodsType) {
		this.orderGoodsType = orderGoodsType;
	}
	/**
	 * @return the goodNo
	 */
	public String getGoodNo() {
		return goodNo;
	}
	/**
	 * @param goodNo the goodNo to set
	 */
	public void setGoodNo(String goodNo) {
		this.goodNo = goodNo;
	}
	/**
	 * @return the orderType
	 */
	public Integer getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

    
}
