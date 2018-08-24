package com.corporate.delivery.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.corporate.deliver.utils.Utils;
import com.corporate.delivery.dao.AbstractDao;
import com.corporate.delivery.dao.OrderHeaderDao;
import com.corporate.delivery.model.order.OrderHeader;

@Repository("orderHeaderDao")
public class OrderHeaderDaoImpl extends AbstractDao<Integer, OrderHeader> implements OrderHeaderDao {

	public List<OrderHeader> getOrderHeader(Integer userId) {
		
		Query query = this.getSession().createQuery("from OrderHeader up where up.userId = " + new Integer(userId));
		List<OrderHeader> list = query.list();
		//return list.subList(0, 10);
		return list;
	}

	public List<OrderHeader> getMerchantOrRestaurantOrders(Integer zipBustypeMerchantId) {
	    Criteria cr = getSession().createCriteria(OrderHeader.class);
		cr.add(Restrictions.eq("zipBustypeMerchantId", zipBustypeMerchantId));

		/*Date today = "2018-07-21"; */
		List<OrderHeader> result = new ArrayList<OrderHeader>();
		List<OrderHeader> list = cr.list();
		for(OrderHeader orderHeader : list){
			Date date = orderHeader.getOrderDate();
			boolean isToday = Utils.isSameDay(date, new Date());
			if(isToday) {
				result.add(orderHeader);
			}
		}
		return result;
	}
	
	public List<OrderHeader> getOrderHeaderDetail(Integer zipBustypeMerchantId, String fromdate, String todate){
		Criteria crit = getSession().createCriteria(OrderHeader.class);
		crit.add(Restrictions.eq("zipBustypeMerchantId", zipBustypeMerchantId));
		List<OrderHeader> result = new ArrayList<OrderHeader>();
		
		Date fromDate;
		//Date fromDate = new Date(fromdate) ;       
		
		Date toDate;
		//Date toDate = new Date(todate) ;
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
			fromDate = dateFormat.parse(fromdate);
			toDate= dateFormat.parse(todate);
			
		} catch( ParseException e) {
			throw new RuntimeException("Error in formatting date");
		}
		
		List<OrderHeader> list = crit.list();
		for(OrderHeader orderHeader : list){
			Date orderDate = orderHeader.getOrderDate();
		
			boolean isAfterDay = Utils.isAfterDay(orderDate, fromDate);
			boolean isBeforeDay = Utils.isBeforeDay(orderDate, toDate);
			
			if(isAfterDay && isBeforeDay) {
				result.add(orderHeader);
			}
			
		}	
		return result;
	}
	
	public void insert(OrderHeader orderHeader) {
		this.getSession().save(orderHeader);
	}
	
	public void updateOrderHeader(OrderHeader orderHeader) {
		this.getSession().update(orderHeader);
	}

	public void deleteOrderHeader(OrderHeader orderHeader) {
		this.getSession().delete(orderHeader);
	}

	public void deleteOrderHeader(Integer userId) {
		// TODO Auto-generated method stub		
	}

	/*public List<OrderHeader> getMerchantOrRestaurantOrders(Merchant merchant) {
		
		if(merchant.getMrType().equals(MerchantType.MERCHANT.getValue())) {
			Query query = this.getSession().createQuery("from OrderHeader up where up.zipBustypeMerchantId = " + new Integer(merchant.getId()));
			List<OrderHeader> list = query.list();
		} else {
			Query query = this.getSession().createQuery("from OrderHeader up where up.zipBustypeMerchantId = " + new Integer(merchant.getId()));
		}
		
		return null;
	}
	*/
	
	
	
	
}
