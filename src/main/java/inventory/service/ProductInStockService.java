package inventory.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import inventory.dao.ProductInStockDAO;
import inventory.model.Invoice;
import inventory.model.Paging;
import inventory.model.ProductInStock;
import inventory.model.ProductInfo;

@Service
public class ProductInStockService {

	@Autowired
	private ProductInStockDAO<ProductInStock> productInStockDAO;
	private static final Logger log = Logger.getLogger(ProductInStockService.class);

	public List<ProductInStock> getAll(ProductInStock productInStock, Paging paging) {
		log.info("show all productInStock");
		StringBuilder queryStr = new StringBuilder();
		Map<String, Object> mapParams = new HashMap<>();
		if (productInStock != null && productInStock.getProductInfo() != null) {
			if (!StringUtils.isEmpty(productInStock.getProductInfo().getCategory().getName())) {
				queryStr.append(" and model.productInfo.category.name like :cateName");
				mapParams.put("cateName", "%" + productInStock.getProductInfo().getCategory().getName() + "%");
			}
			if (productInStock.getProductInfo().getCode() != null
					&& !StringUtils.isEmpty(productInStock.getProductInfo().getCode())) {
				queryStr.append(" and model.productInfo.code=:code");
				mapParams.put("code", productInStock.getProductInfo().getCode());
			}
			if (productInStock.getProductInfo().getName() != null
					&& !StringUtils.isEmpty(productInStock.getProductInfo().getName())) {
				queryStr.append(" and model.productInfo.name like :name");
				mapParams.put("name", "%" + productInStock.getProductInfo().getName() + "%");
			}
		}
		return productInStockDAO.findAll(queryStr.toString(), mapParams, paging);
	}

	public void saveOrUpdate(Invoice invoice) {
		log.info("product in stock");
		if (invoice != null) {
			String code = invoice.getProductInfo().getCode();
			ProductInStock product = productInStockDAO.findByProperty("productInfo.code", code).get(0);
			if (product != null) {
				log.info("update quantity=" + invoice.getQty() + " and price=" + invoice.getPrice());
				if (invoice.getType() == 2) {
					product.setQty(product.getQty() - invoice.getQty());
				} else {
					product.setQty(product.getQty() + invoice.getQty());
				}
				// type = 1 receipt, type = 2 issues
				if (invoice.getType() == 1) {
					product.setPrice(invoice.getPrice());
				}
				product.setUpdateDate(new Date());
				productInStockDAO.update(product);
			} else if (invoice.getType() == 1) {
				log.info("insert to stock quantity=" + invoice.getQty() + " and price=" + invoice.getPrice());
				product = new ProductInStock();
				ProductInfo productInfo = new ProductInfo();
				productInfo.setId(invoice.getProductInfo().getId());
				product.setProductInfo(productInfo);
				product.setActiveFlag(1);
				product.setQty(invoice.getQty());
				product.setPrice(invoice.getPrice());
				product.setCreateDate(new Date());
				product.setUpdateDate(new Date());
				productInStockDAO.save(product);
			}
		}
	}

}
