package com.github.commonutils;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 自定义小工具：map2javabean,UUID
 * @author Administrator
 *
 */
public class CommonUtils {
	/**
	 * 给出需要封装的map和接受bean的类对象
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T>T toBean(Map map,Class<T> clazz){
		try{
			/**
			 * 1.创建指定类型javabean对象
			 */
			T bean =clazz.newInstance();
			/**
			 * 2.把数据封装到javabean中
			 */
			BeanUtils.populate(bean, map);
			return bean;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 生成不重复32位长的大写字符串
	 * @return
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "").trim().toUpperCase();
	}
}
