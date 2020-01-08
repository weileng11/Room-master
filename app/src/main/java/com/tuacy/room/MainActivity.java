package com.tuacy.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.tuacy.room.app.BaseActivity;
import com.tuacy.room.database.entities.Book;
import com.tuacy.room.database.entities.User;

import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

//原文地址:https://www.jianshu.com/p/3e358eb9ac43
//https://blog.csdn.net/suyimin2010/article/details/79753812
public class MainActivity extends BaseActivity {

	int index=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		index=0;
		//插入数据
		findViewById(R.id.button_add_user).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				User user = new User();
				user.setPhone("18988195061");
				user.setAddress("珠海");
				user.setName("tuacy"+index);
				//				user.setAge("28");
				List<Long> ids = mAppDatabase.userDao().insert(user);
				if (ids != null) {
					for (Long id : ids) {
						Log.d("tuacy", "id = " + id);
					}
				}
				index++;
			}
		});

		//查询记录
		findViewById(R.id.button_get_user).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAppDatabase.userDao()
							.loadUser()
							.subscribeOn(Schedulers.io())
							.observeOn(AndroidSchedulers.mainThread())
							.subscribe(new Consumer<List<User>>() {
								@Override
								public void accept(List<User> entities) {
									if (entities != null) {
										for (User user : entities) {
											Log.d("tuacy", user.toString());
										}
									}

								}
							});
			}
		});

		//查询单条记录
		findViewById(R.id.button_queryone_user).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name="tuacy0";
				User user=mAppDatabase.userDao().queryUserName(name);
				if(user!=null){
					Log.d("查询单条记录==", String.valueOf(user.getName()));
				}else{
					Log.d("无单条记录","");
				}
			}
		});

		//更新数据
		findViewById(R.id.button_update_user).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				User user=mAppDatabase.userDao().queryUserName("tuacy0");
				user.setAge(15);
				user.setPhone("13828821555");
				int success=mAppDatabase.userDao().updateUsers(user);
				Log.d("更新成功==", String.valueOf(success));


				List<User> userCount=mAppDatabase.userDao().loadAllUsers();
				Log.d("查询总记录==", String.valueOf(userCount.size()));
				if(userCount!=null&&userCount.size()>0){
					for (User user2 : userCount) {
						Log.d("查询总记录集合", user2.toString());
					}
				}
			}
		});

		//删除记录
		findViewById(R.id.button_delete_user).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name="tuacy0";
				User user=mAppDatabase.userDao().queryUserName(name);
				int success=mAppDatabase.userDao().deleteUsers(user);
				if(success>0){
					Log.d("删除成功==", String.valueOf(success));
				}
				List<User> userCount=mAppDatabase.userDao().loadAllUsers();
				Log.d("查询总记录==", String.valueOf(userCount.size()));
				if(userCount!=null&&userCount.size()>0){
					for (User user2 : userCount) {
						Log.d("查询总记录集合", user2.toString());
					}
				}
			}
		});

		findViewById(R.id.button_add_book).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Book book = new Book();
				book.setName("Android入门到精通");
				book.setTime(new Date());
				book.setUserId(1L);
				List<Long> ids = mAppDatabase.bookDao().insert(book);
				if (ids != null) {
					for (Long id : ids) {
						Log.d("tuacy", "id = " + id);
					}
				}
			}
		});

		findViewById(R.id.button_get_book).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mAppDatabase.bookDao()
							.load()
							.subscribeOn(Schedulers.io())
							.observeOn(AndroidSchedulers.mainThread())
							.subscribe(new Consumer<List<Book>>() {
								@Override
								public void accept(List<Book> entities) {
									if (entities != null) {
										for (Book book : entities) {
											Log.d("tuacy", book.toString());
										}
									}

								}
							});
			}
		});
	}
}
