package cn.org.unk.musicapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.org.unk.musicapp.AudioModel;
import cn.org.unk.musicapp.R;

import cn.org.unk.musicapp.MyApplication;
import cn.org.unk.musicapp.Util;
import cn.org.unk.musicapp.adapter.MyViewPagerAdapter;
import cn.org.unk.musicapp.fragment.BottomBar;
import cn.org.unk.musicapp.fragment.LocalList;
import cn.org.unk.musicapp.fragment.MyList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//https://cloud.tencent.com/developer/article/1513219
    RecyclerView recyclerView;
    TextView noMusicTextView;
    ArrayList<AudioModel> songsList = new ArrayList<>();

    ViewPager mViewPager;
    RadioGroup mRadioGroup;
    RadioButton localList;
    RadioButton myList;
    List<Fragment> mFragmentList;

    BottomBar bottomBar;


    private void initView() {
        mRadioGroup = findViewById(R.id.radioGroup);
        mViewPager = findViewById(R.id.viewPager);
        localList = findViewById(R.id.localList);
        myList = findViewById(R.id.myList);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new LocalList());
        mFragmentList.add(new MyList());
        bottomBar = (BottomBar)getSupportFragmentManager().findFragmentById(R.id.main_activity_BottomBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        System.out.println("MainActivity cerate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Util.scanAllMusic(getApplicationContext());

        getSupportFragmentManager();

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(myViewPagerAdapter);

        mRadioGroup.setOnCheckedChangeListener((RadioGroup radioGroup, int checkedId)->{
            switch (checkedId) {
                case R.id.localList:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.myList:
                    mViewPager.setCurrentItem(1);
                    break;
                default:
                    break;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    localList.setChecked(true);
                } else if (position == 1) {
                    myList.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        BottomBar bottomBar = new BottomBar();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.bottom_bar, bottomBar, "myFragment").commit();
//
//        View viewById = findViewById(R.id.bottom_bar);
//
//        viewById.setOnClickListener(v->{
//            System.out.println(1);
//        });
//
//        recyclerView = findViewById(R.id.recycler_view);
//        noMusicTextView = findViewById(R.id.no_songs_text);
//
        if(!checkPermission()){
            requestPermission();
            return;
        }

        bottomBar.flush();

//


//
//        if(songsList.size()==0){
//            noMusicTextView.setVisibility(View.VISIBLE);
//        }else{
//            //recyclerview
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(new MusicListAdapter(songsList,getApplicationContext()));
//        }

    }

    boolean checkPermission(){
        int i1 = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH);
        int result1 = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int i = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.BLUETOOTH_ADMIN);

        System.out.println("checkPermission");
        System.out.println(result1 == PackageManager.PERMISSION_GRANTED && i1 == PackageManager.PERMISSION_GRANTED && i == PackageManager.PERMISSION_GRANTED);
        if(result1 == PackageManager.PERMISSION_GRANTED && i1 == PackageManager.PERMISSION_GRANTED && i == PackageManager.PERMISSION_GRANTED ){
            return true;
        }else{
            return false;
        }
    }

    void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this,"READ PERMISSION IS REQUIRED,PLEASE ALLOW FROM SETTTINGS",Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.BLUETOOTH_ADMIN,Manifest.permission.BLUETOOTH},123);
    }

    @Override
    protected void onResume() {
        System.out.println("Main Activity onResume");
        bottomBar.flush();
        super.onResume();
    }
}