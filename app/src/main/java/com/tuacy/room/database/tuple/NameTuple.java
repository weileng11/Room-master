package com.tuacy.room.database.tuple;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

public class NameTuple {

	private String username;
	private String user_phone;

	public String getUsername() {
		return username;
	}

	public NameTuple setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public NameTuple setUser_phone(String user_phone) {
		this.user_phone = user_phone;
		return this;
	}

	@Dao
	public interface UserDao {
		@Query("SELECT username, user_phone FROM user")
		List<NameTuple> loadFullName();

	}
}
