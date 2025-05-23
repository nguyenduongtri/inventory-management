package inventory.model;
// Generated Feb 7, 2025, 12:28:04 PM by Hibernate Tools 3.6.2.Final

import java.math.BigDecimal;
import java.util.Date;

/**
 * ProductInStock generated by hbm2java
 */
public class ProductInStock implements java.io.Serializable {

	private Integer id;
	private ProductInfo productInfo;
	private int qty;
	private BigDecimal price;
	private int activeFlag;
	private Date createDate;
	private Date updateDate;

	public ProductInStock() {
	}

	public ProductInStock(ProductInfo productInfo, int qty, BigDecimal price, int activeFlag, Date createDate,
			Date updateDate) {
		this.productInfo = productInfo;
		this.qty = qty;
		this.price = price;
		this.activeFlag = activeFlag;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProductInfo getProductInfo() {
		return this.productInfo;
	}

	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
