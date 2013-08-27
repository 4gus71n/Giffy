
package com.kimboo.giffy.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.kimboo.giffy.model.Model;

public abstract class DaoAdapter<T extends Model> extends
        BaseAdapter {
    RuntimeExceptionDao<T, Integer> dao;
    Context context;

    public DaoAdapter(Context context, RuntimeExceptionDao<T, Integer> dao) {
        setContext(context);
        setDao(dao);
    }

    private void setDao(RuntimeExceptionDao<T, Integer> dao) {
        this.dao = dao;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    @Override
    public int getCount() {
        return dao.queryForAll().size();
    }

    @Override
    public Object getItem(int arg0) {
        return dao.queryForAll().get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return dao.queryForAll().get(arg0).getIdLocal();
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        return getView(dao.queryForAll().get(arg0), arg1, arg2);
    }

    public abstract View getView(T model, View convertView, ViewGroup parent);
}
