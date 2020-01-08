package com.tuacy.room.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

//定义转换类，@TypeConverter注解定义转换的方法
public class Converters {

	@TypeConverter
	public static Date fromTimestamp(Long value) {
		return value == null ? null : new Date(value);
	}

	@TypeConverter
	public static Long dateToTimestamp(Date date) {
		return date == null ? null : date.getTime();
	}
}
