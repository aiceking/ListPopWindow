package com.android.listpopwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.listpoplibrary.ListPopWindowManager;
import com.android.listpoplibrary.model.PopWindowType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StringActivity extends AppCompatActivity {
    @BindView(R.id.btn_string_nobackground)
    Button btnStringNobackground;
    @BindView(R.id.btn_string_withbackground)
    Button btnStringWithbackground;
    @BindView(R.id.btn_string_nobackground_fillscreen)
    Button btnStringNobackgroundFillscreen;
    @BindView(R.id.btn_string_withbackground_fillscreen)
    Button btnStringWithbackgroundFillscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_string_nobackground, R.id.btn_string_withbackground, R.id.btn_string_nobackground_fillscreen, R.id.btn_string_withbackground_fillscreen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_string_nobackground:
                final List<String> list = new ArrayList<>();
                list.add("测试 1");
                list.add("测试 2");
                list.add("测试 3");
                list.add("测试 4");
                list.add("测试===== 5");
                list.add("测试===== 6");
                list.add("测试===== 7");
                list.add("测试===== 8");
                ListPopWindowManager.getInstance().showStringPopWindow(PopWindowType.String_Nobg,list, btnStringNobackground, this,
                        new ListPopWindowManager.onListPopItemClickListener() {
                            @Override
                            public void onClick(int position) {
                                Toast.makeText(StringActivity.this, list.get(position)+"", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.btn_string_withbackground:
                final List<String> list2 = new ArrayList<>();
                list2.add("测试 1");
                list2.add("测试 2");
                list2.add("测试 3");
                list2.add("测试 4");
                list2.add("测试===== 5");
                list2.add("测试===== 6");
                list2.add("测试===== 7");
                list2.add("测试===== 8");
                ListPopWindowManager.getInstance().showStringPopWindow(PopWindowType.String_Hasbg,list2, btnStringWithbackground, this,
                        new ListPopWindowManager.onListPopItemClickListener() {
                            @Override
                            public void onClick(int position) {
                                Toast.makeText(StringActivity.this, list2.get(position)+"", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.btn_string_nobackground_fillscreen:
                final List<String> list3 = new ArrayList<>();
                list3.add("测试 1");
                list3.add("测试 2");
                list3.add("测试 3");
                list3.add("测试 4");
                list3.add("测试===== 5");
                list3.add("测试===== 6");
                list3.add("测试===== 7");
                list3.add("测试===== 8");
                ListPopWindowManager.getInstance().showStringPopWindow(PopWindowType.String_Nobg_fill,list3, btnStringNobackgroundFillscreen, this,
                        new ListPopWindowManager.onListPopItemClickListener() {
                            @Override
                            public void onClick(int position) {
                                Toast.makeText(StringActivity.this, list3.get(position)+"", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            case R.id.btn_string_withbackground_fillscreen:
                final List<String> list4 = new ArrayList<>();
                list4.add("测试 1");
                list4.add("测试 2");
                list4.add("测试 3");
                list4.add("测试 4");
                list4.add("测试===== 5");
                list4.add("测试===== 6");
                list4.add("测试===== 7");
                list4.add("测试===== 8");
                ListPopWindowManager.getInstance().showStringPopWindow(PopWindowType.String_Hasbg_fill,list4, btnStringWithbackgroundFillscreen, this,
                        new ListPopWindowManager.onListPopItemClickListener() {
                            @Override
                            public void onClick(int position) {
                                Toast.makeText(StringActivity.this, list4.get(position)+"", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ListPopWindowManager.getInstance().onDestoryPopWindow(this);
    }
}
