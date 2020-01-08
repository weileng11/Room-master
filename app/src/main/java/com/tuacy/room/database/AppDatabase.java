package com.tuacy.room.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.tuacy.room.database.daos.BookDao;
import com.tuacy.room.database.daos.UserDao;
import com.tuacy.room.database.entities.Book;
import com.tuacy.room.database.entities.User;

//@TypeConverters注解，告知数据库要依赖哪些转换类

//                        表名                  数据库版本     不添加会警告
@Database(entities = {User.class, Book.class}, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "UserDatabase.db";
    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getAppDatabase(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class,
                DB_NAME)
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2,MIGRATION_2_3, MIGRATION_3_4)
                .build();
    }

    /**
     * 数据库版本 1->2 user表格新增了age列
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE User ADD COLUMN age integer");
        }
    };

    /**
     * 数据库版本 2->3 新增book表格
     */
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `book` (`uid` INTEGER PRIMARY KEY autoincrement, `name` TEXT , `userId` INTEGER, 'time' INTEGER)");
        }
    };

    /**
     * 数据库版本 3->4 新增book表格
     */
    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE User ADD COLUMN sex String");
        }
    };

    public abstract UserDao userDao();

    public abstract BookDao bookDao();

}
