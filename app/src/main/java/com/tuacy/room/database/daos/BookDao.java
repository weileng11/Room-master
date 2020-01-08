package com.tuacy.room.database.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tuacy.room.database.entities.Book;

import java.util.List;

import io.reactivex.Flowable;

/**
 *    1. OnConflictStrategy.REPLACE：冲突策略是取代旧数据同时继续事务。
 *
 *     2. OnConflictStrategy.ROLLBACK：冲突策略是回滚事务。
 *
 *        3. OnConflictStrategy.ABORT：冲突策略是终止事务。
 *
 *        4. OnConflictStrategy.FAIL：冲突策略是事务失败。
 *
 *        5. OnConflictStrategy.IGNORE：冲突策略是忽略冲突。
 *
 * 作者：tuacy
 * 链接：https://www.jianshu.com/p/3e358eb9ac43
 */

@Dao
public interface BookDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	List<Long> insert(Book... books);

	@Query("SELECT * from book")
	Flowable<List<Book>> load();

}
