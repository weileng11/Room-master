package com.tuacy.room.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.graphics.Bitmap;

import com.tuacy.room.database.tuple.NameTuple;

import java.util.List;

/**
 * tableName：设置表名字。默认是类的名字。
 * indices：设置索引。
 * inheritSuperIndices：父类的索引是否会自动被当前类继承。
 * primaryKeys：设置主键。
 * foreignKeys：设置外键。
 *
 * 作者：tuacy
 * 链接：https://www.jianshu.com/p/3e358eb9ac43
 *
 * @Entity(primaryKeys = {"firstName",
 *                        "lastName"})  复合主键
 */

@Entity(tableName = "user")
public class User {

	//主键
	@PrimaryKey(autoGenerate = true)
	private long    uid;
	@ColumnInfo(name="username")
	private String  name;
	@ColumnInfo(name="user_address")
	private String  address;
	@ColumnInfo(name="user_phone")
	private String  phone;
	@ColumnInfo(name="user_age")
	private Integer age;

	@Ignore //制定某个字段不作为表中的一列需要添加@Ignore注解,该字段不会映射到User表中。
	Bitmap picture;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

//	@Entity(foreignKeys = @ForeignKey(entity = User.class,
//			parentColumns = "uid",
//			childColumns = "uid"))

	@Override
	public String toString() {
		return "User{" + "uid=" + uid + ", name='" + name + '\'' + ", address='" + address + '\'' + ", phone='" + phone + '\'' + ", age=" +
			   age + '}';
	}

//	@Embedded
//	public Address mAddress;
//
//	public class Address {
//		public String street;
//		public String state;
//		public String city;
//
//		@ColumnInfo(name = "post_code")
//		public int postCode;
//	}

}



