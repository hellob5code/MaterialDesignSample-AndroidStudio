package com.jp.materialdesignsample.domain.helper;

import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

public class ActiveAndroidDatabaseHelper {

    public static <T extends Model> T getItem(Class<T> table, long id) {
        T result = new Select()
                .from(table)
                .where("Id = ?", id)
                .executeSingle();
        return result;
    }

    public static <T extends Model> List<T> getList(Class<T> table) {
        List<T> result = new Select()
                .from(table)
                .execute();

        return result;
    }

    public static <T extends Model> long saveItem(T item) {
        return item.save();
    }

    public static <T extends Model> T removeItem(Class<T> table, long id) {
        T item = T.load(table, id);
        if (item != null) {
            item.delete();
            return item;
        }
        return null;
    }

    public static <T extends Model> boolean removeItem(Class<T> table, T item) {
        return removeItem(table, item.getId()) != null;
    }

    public static <T extends Model> List<T> removeAll(Class<T> table) {
        return new Delete().from(table).execute();
    }

}
