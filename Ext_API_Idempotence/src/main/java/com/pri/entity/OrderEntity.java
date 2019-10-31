package com.pri.entity;

/**
 * className:  OrderEntity <BR>
 * description: <BR>
 * remark: 参考文献：每特教育 <BR>
 * author:  ChenQi <BR>
 * createDate:  2019-10-31 23:57 <BR>
 */
public class OrderEntity {

	private int id;
	private String orderName;
	private String orderDes;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the orderName
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * @param orderName
	 *            the orderName to set
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * @return the orderDes
	 */
	public String getOrderDes() {
		return orderDes;
	}

	/**
	 * @param orderDes
	 *            the orderDes to set
	 */
	public void setOrderDes(String orderDes) {
		this.orderDes = orderDes;
	}

}
