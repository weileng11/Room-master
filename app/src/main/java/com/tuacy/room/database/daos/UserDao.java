package com.tuacy.room.database.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.tuacy.room.database.entities.Book;
import com.tuacy.room.database.entities.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * /**
 *  *    1. OnConflictStrategy.REPLACE：冲突策略是取代旧数据同时继续事务。
 *  *
 *  *     2. OnConflictStrategy.ROLLBACK：冲突策略是回滚事务。
 *  *
 *  *        3. OnConflictStrategy.ABORT：冲突策略是终止事务。
 *  *
 *  *        4. OnConflictStrategy.FAIL：冲突策略是事务失败。
 *  *
 *  *        5. OnConflictStrategy.IGNORE：冲突策略是忽略冲突。
 *  *
 *  * 作者：tuacy
 *  * 链接：https://www.jianshu.com/p/3e358eb9ac43
 *
 *  //Update(更新)
 *
 * @author PC*/

@Dao
public interface UserDao {

	//插入集合
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	List<Long> insert(User... users);

	//Rxjava查询集合
	@Query("SELECT * from user")
	Flowable<List<User>> loadUser();

	//查询集合
	@Query("SELECT * from user")
	List<User> loadAllUsers();

	//根据某一项查询对应的列
	@Query("SELECT * from user WHERE username == :name")
	User queryUserName(String name);

	//根据某一项查询查询集合
	@Query("SELECT * from user WHERE user_age > :minAge")
	List<User> loadAllUsersOlderThan(int minAge);

	//更新某一项的值
	@Update(onConflict = OnConflictStrategy.REPLACE)
	int updateUsers(User... users);

	//删除某一项
	@Delete
	int deleteUsers(User... users);

	//删全部
	@Query("DELETE FROM user")
	void deleteAll();

	//根据某一项查询
	@Query("SELECT * from user WHERE user_age > :minAge LIMIT 5")
	Cursor loadRawUsersOlderThan(int minAge);

//	@Query("SELECT * FROM book "
//			+ "INNER JOIN loan ON loan.book_id = book.id "
//			+ "INNER JOIN user ON user.id = loan.user_id "
//			+ "WHERE user.name LIKE :userName")
//	public List<Book> findBooksBorrowedByNameSync(String userName);
}
