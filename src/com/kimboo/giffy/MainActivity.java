
package com.kimboo.giffy;

import android.app.Activity;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.kimboo.giffy.db.DatabaseHelper;
import com.kimboo.giffy.model.Gif;
import com.kimboo.giffy.utils.DiskLruImageCache;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseHelper databaseHelper = null;

    private DiskLruImageCache diskCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Loding...");
        diskCache = new DiskLruImageCache(this, "cache", 1024 * 1024 * 30, CompressFormat.PNG, 70);
        setContentView(R.layout.activity_main);
        doSampleDatabaseStuff();
        GridView gifGrid = (GridView) findViewById(R.id.main_gridview);
      //  gifGrid.setAdapter(new GifGridAdapter(this, getHelper().getGifDao()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
         * You'll need this in your class to release the helper when done.
         */
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
        diskCache.close();
    }

    /**
     * You'll need this in your class to get the helper from the manager once
     * per class.
     */
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    
    /**
     * TODO: DELETE
     */
    protected void doSampleDatabaseStuff() {
        Gif simple = new Gif();
//        simple.setHash("party.gif");
//        getHelper().getGifDao().create(simple);
//        simple.setHash("goten.gif");
//        getHelper().getGifDao().create(simple);
//        simple.setHash("wonka.gif");
//        getHelper().getGifDao().create(simple);
//        simple.setHash("tape.gif");
//        getHelper().getGifDao().create(simple);
//        simple.setHash("redball.gif");
//        getHelper().getGifDao().create(simple);
//        simple.setHash("sixpack.gif");
//        getHelper().getGifDao().create(simple);
//        simple.setHash("coffee.gif");
//        getHelper().getGifDao().create(simple);
//        simple.setHash("fry.gif");
//        getHelper().getGifDao().create(simple);
//        simple.setHash("chipmunk.gif");
//        getHelper().getGifDao().create(simple);
//        simple.setHash("pie.gif");
//        getHelper().getGifDao().create(simple);
        
    }
//
//    class GifGridAdapter extends DaoAdapter<Gif> {
//        public GifGridAdapter(Context context, RuntimeExceptionDao<Gif, Integer> dao) {
//            super(context, dao);
//        }
//
//        @Override
//        public View getView(Gif model, View convertView, ViewGroup parent) {
//            TinyGifDecoderView view = new TinyGifDecoderView(getContext(), model.getHash(), diskCache);
//            view.setScaleType(TinyGifDecoderView.ScaleType.FIT_XY);
//            //TODO: Improve
//            view.setLayoutParams(new GridView.LayoutParams(150, 100));
//            view.setPadding(0, 0, 0, 0);
//            return view;
//        }
//
//    }
//
//    abstract class DaoAdapter<T extends Model> extends
//            BaseAdapter {
//        RuntimeExceptionDao<T, Integer> dao;
//        Context context;
//
//        public DaoAdapter(Context context, RuntimeExceptionDao<T, Integer> dao) {
//            setContext(context);
//            setDao(dao);
//        }
//
//        private void setDao(RuntimeExceptionDao<T, Integer> dao) {
//            this.dao = dao;
//        }
//
//        public void setContext(Context context) {
//            this.context = context;
//        }
//
//        public Context getContext() {
//            return this.context;
//        }
//        
//        @Override
//        public int getCount() {
//            return dao.queryForAll().size();
//        }
//
//        @Override
//        public Object getItem(int arg0) {
//            return dao.queryForAll().get(arg0);
//        }
//
//        @Override
//        public long getItemId(int arg0) {
//            return dao.queryForAll().get(arg0).getId();
//        }
//
//        @Override
//        public View getView(int arg0, View arg1, ViewGroup arg2) {
//            Log.d(TAG,"Loading item:"+arg0);
//            return getView(dao.queryForAll().get(arg0), arg1, arg2);
//        }
//
//        public abstract View getView(T model, View convertView, ViewGroup parent);
//    }
}
