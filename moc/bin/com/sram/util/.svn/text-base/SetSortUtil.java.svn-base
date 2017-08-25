package com.sram.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import antlr.collections.impl.LList;

import com.sram.entity.OrderEntity;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015-4-3 下午03:49:26
 */
public class SetSortUtil {
	
	public static Set<? extends OrderEntity> sort(Set<? extends OrderEntity> orderEntities) {
		Iterator<? extends OrderEntity> iterator = orderEntities.iterator();
		List<OrderEntity> list=new ArrayList<OrderEntity>();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
        Collections.sort(list,new Comparator<OrderEntity>() {
        	 public int compare(OrderEntity o1, OrderEntity o2) {
                 if (o1 != null && o2 != null) {
                     if (o1.getOrder() > o2.getOrder()) {
                         return 1;
                     } else if (o1.getOrder() < o2.getOrder()) {
                         return -1;
                     }
                 }
                 return 0;
             }
		});

        Set<OrderEntity> set=new HashSet<OrderEntity>(); 
        for (OrderEntity orderEntity : list) {
			set.add(orderEntity);
		}
        return orderEntities;
	}
}
