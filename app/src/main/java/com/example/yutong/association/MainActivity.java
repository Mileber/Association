package com.example.yutong.association;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bmob.BmobProFile;
import com.bmob.btp.callback.DownloadListener;

import java.io.File;

import cn.bmob.v3.BmobUser;
import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AssociationListFragment.OnListFragmentInteractionListener,PracticeListFragment.OnListFragmentInteractionListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TextView username_toolbar;
    private TextView username_nav;
    private CircleImageView avatar;
    private String avaterPath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /*Intent intent = getIntent();
        user_name = intent.getStringExtra(LoginActivity.USER_NAME_LOGIN);*/

        /*String user_name = "宝宝";*/

        /*set font*/
        new LongOperation().execute((Void) null);
        username_toolbar = (TextView) findViewById(R.id.username_toolbar);
        /*username_toolbar.setText(user_name);*/
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/title.ttf");
        username_toolbar.setTypeface(face);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        username_nav = (TextView)header.findViewById(R.id.tv_username_nav);
        /*username_nav.setText(user_name);*/
        username_nav.setTypeface(face);

        avatar = (CircleImageView) header.findViewById(R.id.avatar);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.action_logout){
            Context context = getApplicationContext();
            BmobUser bmobUser = BmobUser.getCurrentUser(context);
            bmobUser.logOut(context);
            Intent intent  = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_activity) {

        } else if (id == R.id.nav_association) {

        } else if (id == R.id.nav_setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Association item) {
        //TODO

    }

    @Override
    public void onListFragmentInteraction(Practice item) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            /*return PlaceholderFragment.newInstance(position + 1);*/
            switch(position){
                case 0:
                    return RecommendationFragment.newInstance();
                case 1:
                    return AssociationListFragment.newInstance(1);
                case 2:
                    return PracticeListFragment.newInstance(1);
                case 3:
                    return SortFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "推荐";
                case 1:
                    return "社团";
                case 2:
                    return "活动";
                case 3:
                    return "分类";
            }
            return null;
        }
    }

    public void showAssociationDetails(View view){
        //TODO:title of the association
        Intent intent = new Intent(this, DetailAssociationActivity.class);
        startActivity(intent);
    }

    public class LongOperation extends AsyncTask<Void, Void, Boolean> {

        private String mUsername;
        /*private BmobFile mAvater;*/
        Context context = getApplicationContext();
        MyUser bmobUser = BmobUser.getCurrentUser(context,MyUser.class);


        @Override
        protected Boolean doInBackground(Void... params) {
            mUsername = bmobUser.getUsername();
            /*mAvater = bmobUser.getAvater();*/

            BmobProFile.getInstance(context).download(bmobUser.getAvaterPath(), new DownloadListener() {

                @Override
                public void onSuccess(String fullPath) {
                    // TODO Auto-generated method stub
                    Log.i("bmob", "下载成功：" + fullPath);
                    avaterPath = fullPath;
                }

                @Override
                public void onProgress(String localPath, int percent) {
                    // TODO Auto-generated method stub
                    Log.i("bmob", "download-->onProgress :" + percent);
                }

                @Override
                public void onError(int statuscode, String errormsg) {
                    // TODO Auto-generated method stub
                    Log.i("bmob", "下载出错：" + statuscode + "--" + errormsg);
                }
            });

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (success) {
                username_toolbar.setText(mUsername);
                username_nav.setText(mUsername);
                if(avaterPath == null){
                    Toast.makeText(context,"avater null",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(context,avaterPath,Toast.LENGTH_SHORT).show();
                /*File imgFile = new  File(avaterPath);

                if(imgFile.exists()){

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    Toast.makeText(context,imgFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();

                    *//*avatar.setImageBitmap(myBitmap);*//*
                }*/
                File sd = Environment.getExternalStorageDirectory();
                File image = new File(sd+avaterPath);
                /*BitmapFactory.Options bmOptions = new BitmapFactory.Options();*/
                Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath());
                avatar.setImageBitmap(bitmap);
                //TODO:图片无法显示
            } else {
                Context context = getApplicationContext();
                Toast.makeText(context, "刷新失败：", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
}
