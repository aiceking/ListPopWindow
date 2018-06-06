package com.android.listpoplibrary;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorRes;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.listpoplibrary.adapter.PopStringImgesListAdapter;
import com.android.listpoplibrary.adapter.PopStringImgesListAdapter_fillScreen;
import com.android.listpoplibrary.adapter.PopStringListAdapter;
import com.android.listpoplibrary.adapter.PopStringListAdapter_fillScreen;
import com.android.listpoplibrary.model.ListPopModel;
import com.android.listpoplibrary.model.PopWindowType;
import com.android.listpoplibrary.view.WidthListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by radio on 2017/11/1.
 */

public class ListPopWindowManager {
    public interface onListPopItemClickListener{
        void onClick(int position);
    }
    private @ColorRes int popWindowColor;
    private @ColorRes int textColor;

    public int getPopWindowColor() {
        return popWindowColor;
    }

    public void setPopWindowColor(int popWindowColor) {
        this.popWindowColor = popWindowColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    private static ListPopWindowManager listPopWindowManager;
    private HashMap<Activity,PopupWindow> hashMap_stringPopupWindowNoBackground;
    private HashMap<Activity,PopupWindow> hashMap_stringPopupWindowWithBackground;

    private HashMap<Activity,PopupWindow> hashMap_stringImagePopupWindowNoBackground;

    private HashMap<Activity,PopupWindow> hashMap_stringImagePopupWindowWithBackground;

    private HashMap<Activity,PopupWindow> hashMap_stringPopupWindowNoBackground_fillScreen;
    private HashMap<Activity,PopupWindow> hashMap_stringPopupWindowNoBackground_halfScreen;

    private HashMap<Activity,PopupWindow> hashMap_stringPopupWindowWithBackground_fillScreen;

    private HashMap<Activity,PopupWindow> hashMap_stringImagePopupWindowNoBackground_fillScreen;
    private HashMap<Activity,PopupWindow> hashMap_stringImagePopupWindowNoBackground_halfScreen;

    private HashMap<Activity,PopupWindow> hashMap_stringImagePopupWindowWithBackground_fillScreen;

    public void setShowImageListener(showImageListener showImageListener) {
        this.showImageListener = showImageListener;
    }

    public ListPopWindowManager.showImageListener getShowImageListener() {
        return showImageListener;
    }

    private showImageListener showImageListener;
    public interface showImageListener{
        void showImage(Context context,  String path, ImageView imageView);
    }
    public static ListPopWindowManager getInstance(){
        if (listPopWindowManager ==null){
            synchronized (ListPopWindowManager.class){
                if (listPopWindowManager ==null){
                    listPopWindowManager =new ListPopWindowManager();
                }
            }
        }
        return listPopWindowManager;
    }
    private ListPopWindowManager(){
         hashMap_stringPopupWindowNoBackground=new HashMap<>();
         hashMap_stringPopupWindowWithBackground=new HashMap<>();
         hashMap_stringImagePopupWindowNoBackground=new HashMap<>();
         hashMap_stringImagePopupWindowWithBackground=new HashMap<>();
         hashMap_stringPopupWindowNoBackground_fillScreen=new HashMap<>();
         hashMap_stringPopupWindowWithBackground_fillScreen=new HashMap<>();
         hashMap_stringImagePopupWindowNoBackground_fillScreen=new HashMap<>();
         hashMap_stringImagePopupWindowWithBackground_fillScreen=new HashMap<>();
        hashMap_stringPopupWindowNoBackground_halfScreen=new HashMap<>();
        hashMap_stringImagePopupWindowNoBackground_halfScreen=new HashMap<>();
    }
    public void showStringPopWindow(PopWindowType popWindowType,List<String> list,View view,final Activity activity,final onListPopItemClickListener onListPopItemClickListener){
        switch (popWindowType){
            case String_Nobg:
                showStringPopNoBg(list,view,activity,onListPopItemClickListener);
                break;
            case String_Nobg_fill:
                showStringPopNoBg_fillScreen(list,view,activity,onListPopItemClickListener);
                break;
            case String_Nobg_half:
                showStringPopNoBg_halfScreen(list,view,activity,onListPopItemClickListener);
                break;
            case String_Hasbg:
                showStringPopHasBg(list,view,activity,onListPopItemClickListener);
                break;
            case String_Hasbg_fill:
                showStringPopHasBg_fillScreen(list,view,activity,onListPopItemClickListener);
                break;

        }
    }
    public void showStringImagePopWindow(PopWindowType popWindowType,List<ListPopModel> list, View view, final Activity activity, final onListPopItemClickListener onListPopItemClickListener){
        switch (popWindowType){
            case String_Image_Nobg:
                showStringAndImagePopNoBg(list,view,activity,onListPopItemClickListener);
                break;
            case String_Image_Nobg_fill:
                showStringAndImagePopNoBg_fillScreen(list,view,activity,onListPopItemClickListener);
                break;
            case String_Image_Nobg_half:
                showStringAndImagePopNoBg_halfScreen(list,view,activity,onListPopItemClickListener);
                break;
            case String_Image_Hasbg:
                showStringAndImagePopHasBg(list,view,activity,onListPopItemClickListener);
                break;
            case String_Image_Hasbg_fill:
                showStringAndImagePopHasBg_fillScreen(list,view,activity,onListPopItemClickListener);
                break;
        }
    }
    private void showStringPopNoBg(List<String> list,View view,final Activity activity,final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(activity, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hashMap_stringPopupWindowNoBackground.containsKey(activity)){
            Log.v("pop=","new");
        View contentview = LayoutInflater.from(activity).inflate(R.layout.popwindow_list_library, null);
        WidthListView pop_listview=(WidthListView)contentview.findViewById(R.id.pop_listview);
        CardView card_popwindow=(CardView)contentview.findViewById(R.id.card_popwindow);
        if (listPopWindowManager.getPopWindowColor()!=0){
        card_popwindow.setCardBackgroundColor(activity.getResources().getColor(listPopWindowManager.getPopWindowColor()));}
            PopStringListAdapter  stringAdapter=new PopStringListAdapter(list,activity);
            pop_listview.setAdapter(stringAdapter);
            final PopupWindow stringPopupWindowNoBackground = new PopupWindow(contentview, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
            stringPopupWindowNoBackground.setBackgroundDrawable(new ColorDrawable(0) );
            stringPopupWindowNoBackground.setOutsideTouchable(true);
            stringPopupWindowNoBackground.setAnimationStyle(android.R.style.Animation_Dialog);
            stringPopupWindowNoBackground.setTouchable(true);
            stringPopupWindowNoBackground.setFocusable(true);
            stringPopupWindowNoBackground.showAsDropDown(view);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                stringPopupWindowNoBackground.dismiss();
                if (onListPopItemClickListener!=null){
                onListPopItemClickListener.onClick(i);}
            }
        });
            hashMap_stringPopupWindowNoBackground.put(activity,stringPopupWindowNoBackground);
        }else{
            if (!hashMap_stringPopupWindowNoBackground.get(activity).isShowing()){
                Log.v("pop=","old");

                WidthListView pop_listview=(WidthListView)hashMap_stringPopupWindowNoBackground.get(activity).getContentView().findViewById(R.id.pop_listview);

                PopStringListAdapter  stringAdapter=new PopStringListAdapter(list,activity);
                pop_listview.setAdapter(stringAdapter);
                pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                hashMap_stringPopupWindowNoBackground.get(activity).dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);}
                            }
                        });
                hashMap_stringPopupWindowNoBackground.get(activity).showAsDropDown(view);
            }
        }
    }
    private void showStringPopNoBg_fillScreen(List<String> list,View view,final Activity activity,final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(activity, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hashMap_stringPopupWindowNoBackground_fillScreen.containsKey(activity)){
            Log.v("pop=","new");

            View contentview = LayoutInflater.from(activity).inflate(R.layout.popwindow_list_fillparent_library, null);
            ListView pop_listview=(ListView)contentview.findViewById(R.id.pop_listview);
            CardView card_popwindow=(CardView)contentview.findViewById(R.id.card_popwindow);
            if (listPopWindowManager.getPopWindowColor()!=0){

                card_popwindow.setCardBackgroundColor(activity.getResources().getColor(listPopWindowManager.getPopWindowColor()));}
            PopStringListAdapter_fillScreen stringAdapter_fillScreen=new PopStringListAdapter_fillScreen(list,activity);
            pop_listview.setAdapter(stringAdapter_fillScreen);
            final PopupWindow stringPopupWindowNoBackground_fillScreen = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringPopupWindowNoBackground_fillScreen.setBackgroundDrawable(new ColorDrawable(0) );
            stringPopupWindowNoBackground_fillScreen.setOutsideTouchable(true);
            stringPopupWindowNoBackground_fillScreen.setAnimationStyle(android.R.style.Animation_Dialog);
            stringPopupWindowNoBackground_fillScreen.setTouchable(true);
            stringPopupWindowNoBackground_fillScreen.setFocusable(true);
            stringPopupWindowNoBackground_fillScreen.showAsDropDown(view);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringPopupWindowNoBackground_fillScreen.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            hashMap_stringPopupWindowNoBackground_fillScreen.put(activity,stringPopupWindowNoBackground_fillScreen);
        }else{
            if (!hashMap_stringPopupWindowNoBackground_fillScreen.get(activity).isShowing()){
                Log.v("pop=","odl");

                PopStringListAdapter_fillScreen stringAdapter_fillScreen=new PopStringListAdapter_fillScreen(list,activity);
                ListView pop_listview=(ListView)hashMap_stringPopupWindowNoBackground_fillScreen.get(activity).getContentView().findViewById(R.id.pop_listview);
                pop_listview.setAdapter(stringAdapter_fillScreen);
                pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                hashMap_stringPopupWindowNoBackground_fillScreen.get(activity).dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);}
                            }
                        });
                hashMap_stringPopupWindowNoBackground_fillScreen.get(activity).showAsDropDown(view);
            }
        }
    }
    private void showStringPopNoBg_halfScreen(List<String> list,View view,final Activity activity,final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(activity, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hashMap_stringPopupWindowNoBackground_halfScreen.containsKey(activity)){
            Log.v("pop=","new");

            View contentview = LayoutInflater.from(activity).inflate(R.layout.popwindow_list_fillparent_library, null);
            ListView pop_listview=(ListView)contentview.findViewById(R.id.pop_listview);
            CardView card_popwindow=(CardView)contentview.findViewById(R.id.card_popwindow);
            if (listPopWindowManager.getPopWindowColor()!=0){

                card_popwindow.setCardBackgroundColor(activity.getResources().getColor(listPopWindowManager.getPopWindowColor()));}
            PopStringListAdapter_fillScreen stringAdapter_fillScreen=new PopStringListAdapter_fillScreen(list,activity);
            pop_listview.setAdapter(stringAdapter_fillScreen);
            WindowManager wm = (WindowManager) activity
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            final PopupWindow stringPopupWindowNoBackground_fillScreen = new PopupWindow(contentview, width/2,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringPopupWindowNoBackground_fillScreen.setBackgroundDrawable(new ColorDrawable(0) );
            stringPopupWindowNoBackground_fillScreen.setOutsideTouchable(true);
            stringPopupWindowNoBackground_fillScreen.setAnimationStyle(android.R.style.Animation_Dialog);
            stringPopupWindowNoBackground_fillScreen.setTouchable(true);
            stringPopupWindowNoBackground_fillScreen.setFocusable(true);
            stringPopupWindowNoBackground_fillScreen.showAsDropDown(view);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringPopupWindowNoBackground_fillScreen.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            hashMap_stringPopupWindowNoBackground_halfScreen.put(activity,stringPopupWindowNoBackground_fillScreen);
        }else{
            if (!hashMap_stringPopupWindowNoBackground_halfScreen.get(activity).isShowing()){
                Log.v("pop=","odl");

                PopStringListAdapter_fillScreen stringAdapter_fillScreen=new PopStringListAdapter_fillScreen(list,activity);
                ListView pop_listview=(ListView)hashMap_stringPopupWindowNoBackground_halfScreen.get(activity).getContentView().findViewById(R.id.pop_listview);
                pop_listview.setAdapter(stringAdapter_fillScreen);
                pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        hashMap_stringPopupWindowNoBackground_halfScreen.get(activity).dismiss();
                        if (onListPopItemClickListener!=null){
                            onListPopItemClickListener.onClick(i);}
                    }
                });
                hashMap_stringPopupWindowNoBackground_halfScreen.get(activity).showAsDropDown(view);
            }
        }
    }
    private void showStringPopHasBg( List<String> list,View view,final Activity activity,final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(activity, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hashMap_stringPopupWindowWithBackground.containsKey(activity)){
            Log.v("pop=","new");

            View contentview = LayoutInflater.from(activity).inflate(R.layout.popwindow_list_library, null);
            WidthListView pop_listview=(WidthListView)contentview.findViewById(R.id.pop_listview);
            CardView card_popwindow=(CardView)contentview.findViewById(R.id.card_popwindow);
            if (listPopWindowManager.getPopWindowColor()!=0){

                card_popwindow.setCardBackgroundColor(activity.getResources().getColor(listPopWindowManager.getPopWindowColor()));}
            PopStringListAdapter stringAdapter=new PopStringListAdapter(list,activity);
            pop_listview.setAdapter(stringAdapter);
            final PopupWindow stringPopupWindowWithBackground = new PopupWindow(contentview, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringPopupWindowWithBackground.setBackgroundDrawable(new ColorDrawable(0) );
            stringPopupWindowWithBackground.setOutsideTouchable(true);
            stringPopupWindowWithBackground.setAnimationStyle(android.R.style.Animation_Dialog);
            stringPopupWindowWithBackground.setTouchable(true);
            stringPopupWindowWithBackground.setFocusable(true);
            stringPopupWindowWithBackground.showAsDropDown(view);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringPopupWindowWithBackground.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = 0.7f;
            activity.getWindow().setAttributes(lp);
            stringPopupWindowWithBackground.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                    lp.alpha = 1f;
                    activity.getWindow().setAttributes(lp);
                }
            });
            hashMap_stringPopupWindowWithBackground.put(activity,stringPopupWindowWithBackground);
        }else{
            if (!hashMap_stringPopupWindowWithBackground.get(activity).isShowing()){
                Log.v("pop=","odl");

                WidthListView pop_listview=(WidthListView)hashMap_stringPopupWindowWithBackground.get(activity).getContentView().findViewById(R.id.pop_listview);
                PopStringListAdapter stringAdapter=new PopStringListAdapter(list,activity);
                pop_listview.setAdapter(stringAdapter);
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 0.7f;
                activity.getWindow().setAttributes(lp);
                hashMap_stringPopupWindowWithBackground.get(activity).setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                        lp.alpha = 1f;
                        activity.getWindow().setAttributes(lp);
                    }
                });
                pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                hashMap_stringPopupWindowWithBackground.get(activity).dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);}
                            }
                        });
                hashMap_stringPopupWindowWithBackground.get(activity).showAsDropDown(view);
            }
        }
    }
    private void showStringPopHasBg_fillScreen( List<String> list,View view,final Activity activity,final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(activity, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hashMap_stringPopupWindowWithBackground_fillScreen.containsKey(activity)){
            Log.v("pop=","new");

            View contentview = LayoutInflater.from(activity).inflate(R.layout.popwindow_list_fillparent_library, null);
            ListView pop_listview=(ListView)contentview.findViewById(R.id.pop_listview);
            CardView card_popwindow=(CardView)contentview.findViewById(R.id.card_popwindow);
            if (listPopWindowManager.getPopWindowColor()!=0){

                card_popwindow.setCardBackgroundColor(activity.getResources().getColor(listPopWindowManager.getPopWindowColor()));}
            PopStringListAdapter_fillScreen stringAdapter_fillScreen=new PopStringListAdapter_fillScreen(list,activity);
            pop_listview.setAdapter(stringAdapter_fillScreen);
            final PopupWindow stringPopupWindowWithBackground_fillScreen = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringPopupWindowWithBackground_fillScreen.setBackgroundDrawable(new ColorDrawable(0) );
            stringPopupWindowWithBackground_fillScreen.setOutsideTouchable(true);
            stringPopupWindowWithBackground_fillScreen.setAnimationStyle(android.R.style.Animation_Dialog);
            stringPopupWindowWithBackground_fillScreen.setTouchable(true);
            stringPopupWindowWithBackground_fillScreen.setFocusable(true);
            stringPopupWindowWithBackground_fillScreen.showAsDropDown(view);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringPopupWindowWithBackground_fillScreen.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = 0.7f;
            activity.getWindow().setAttributes(lp);
            stringPopupWindowWithBackground_fillScreen.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                    lp.alpha = 1f;
                    activity.getWindow().setAttributes(lp);
                }
            });
            hashMap_stringPopupWindowWithBackground_fillScreen.put(activity,stringPopupWindowWithBackground_fillScreen);
        }else{
            if (!hashMap_stringPopupWindowWithBackground_fillScreen.get(activity).isShowing()){
                Log.v("pop=","odl");

                ListView pop_listview=(ListView)hashMap_stringPopupWindowWithBackground_fillScreen.get(activity).getContentView().findViewById(R.id.pop_listview);
                PopStringListAdapter_fillScreen stringAdapter_fillScreen=new PopStringListAdapter_fillScreen(list,activity);
                pop_listview.setAdapter(stringAdapter_fillScreen);
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 0.7f;
                activity.getWindow().setAttributes(lp);
                hashMap_stringPopupWindowWithBackground_fillScreen.get(activity).setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                        lp.alpha = 1f;
                        activity.getWindow().setAttributes(lp);
                    }
                });
                pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                hashMap_stringPopupWindowWithBackground_fillScreen.get(activity).dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);
                                }
                            }
                        });
                hashMap_stringPopupWindowWithBackground_fillScreen.get(activity).showAsDropDown(view);
            }
        }
    }
    private void showStringAndImagePopNoBg(List<ListPopModel> list, View view, final Activity activity, final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(activity, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hashMap_stringImagePopupWindowNoBackground.containsKey(activity)){
            Log.v("pop=","new");

            View contentview = LayoutInflater.from(activity).inflate(R.layout.popwindow_list_library, null);
            WidthListView pop_listview=(WidthListView)contentview.findViewById(R.id.pop_listview);
            CardView card_popwindow=(CardView)contentview.findViewById(R.id.card_popwindow);
            if (listPopWindowManager.getPopWindowColor()!=0){
                card_popwindow.setCardBackgroundColor(activity.getResources().getColor(listPopWindowManager.getPopWindowColor()));}
            PopStringImgesListAdapter stringImageAdapter=new PopStringImgesListAdapter(list,activity);
            pop_listview.setAdapter(stringImageAdapter);
            final PopupWindow stringImagePopupWindowNoBackground = new PopupWindow(contentview, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringImagePopupWindowNoBackground.setBackgroundDrawable(new ColorDrawable(0) );
            stringImagePopupWindowNoBackground.setOutsideTouchable(true);
            stringImagePopupWindowNoBackground.setAnimationStyle(android.R.style.Animation_Dialog);
            stringImagePopupWindowNoBackground.setTouchable(true);
            stringImagePopupWindowNoBackground.setFocusable(true);
            stringImagePopupWindowNoBackground.showAsDropDown(view);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringImagePopupWindowNoBackground.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            hashMap_stringImagePopupWindowNoBackground.put(activity,stringImagePopupWindowNoBackground);
        }else{
            if (!hashMap_stringImagePopupWindowNoBackground.get(activity).isShowing()){
                Log.v("pop=","odl");

                WidthListView pop_listview=(WidthListView)hashMap_stringImagePopupWindowNoBackground.get(activity).getContentView().findViewById(R.id.pop_listview);
                PopStringImgesListAdapter stringImageAdapter=new PopStringImgesListAdapter(list,activity);
                pop_listview.setAdapter(stringImageAdapter);
                pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                hashMap_stringImagePopupWindowNoBackground.get(activity).dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);
                                }
                            }
                        });
                hashMap_stringImagePopupWindowNoBackground.get(activity).showAsDropDown(view);
            }
        }
    }
    private void showStringAndImagePopNoBg_fillScreen(List<ListPopModel> list, View view, final Activity activity, final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(activity, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hashMap_stringImagePopupWindowNoBackground_fillScreen.containsKey(activity)){
            Log.v("pop=","new");

            View contentview = LayoutInflater.from(activity).inflate(R.layout.popwindow_list_fillparent_library, null);
            ListView pop_listview=(ListView)contentview.findViewById(R.id.pop_listview);
            CardView card_popwindow=(CardView)contentview.findViewById(R.id.card_popwindow);
            if (listPopWindowManager.getPopWindowColor()!=0){

                card_popwindow.setCardBackgroundColor(activity.getResources().getColor(listPopWindowManager.getPopWindowColor()));}
            PopStringImgesListAdapter_fillScreen stringImageAdapter_fillScreen=new PopStringImgesListAdapter_fillScreen(list,activity);
            pop_listview.setAdapter(stringImageAdapter_fillScreen);
            final PopupWindow stringImagePopupWindowNoBackground_fillScreen = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringImagePopupWindowNoBackground_fillScreen.setBackgroundDrawable(new ColorDrawable(0) );
            stringImagePopupWindowNoBackground_fillScreen.setOutsideTouchable(true);
            stringImagePopupWindowNoBackground_fillScreen.setAnimationStyle(android.R.style.Animation_Dialog);
            stringImagePopupWindowNoBackground_fillScreen.setTouchable(true);
            stringImagePopupWindowNoBackground_fillScreen.setFocusable(true);
            stringImagePopupWindowNoBackground_fillScreen.showAsDropDown(view);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringImagePopupWindowNoBackground_fillScreen.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            hashMap_stringImagePopupWindowNoBackground_fillScreen.put(activity,stringImagePopupWindowNoBackground_fillScreen);
        }else{
            if (!hashMap_stringImagePopupWindowNoBackground_fillScreen.get(activity).isShowing()){
                Log.v("pop=","odl");

                ListView pop_listview=(ListView)hashMap_stringImagePopupWindowNoBackground_fillScreen.get(activity).getContentView().findViewById(R.id.pop_listview);
                PopStringImgesListAdapter_fillScreen stringImageAdapter_fillScreen=new PopStringImgesListAdapter_fillScreen(list,activity);
                pop_listview.setAdapter(stringImageAdapter_fillScreen);
                pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                hashMap_stringImagePopupWindowNoBackground_fillScreen.get(activity).dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);
                                }
                            }
                        });
                hashMap_stringImagePopupWindowNoBackground_fillScreen.get(activity).showAsDropDown(view);
            }
        }
    }
    private void showStringAndImagePopNoBg_halfScreen(List<ListPopModel> list, View view, final Activity activity, final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(activity, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hashMap_stringImagePopupWindowNoBackground_halfScreen.containsKey(activity)){
            Log.v("pop=","new");

            View contentview = LayoutInflater.from(activity).inflate(R.layout.popwindow_list_fillparent_library, null);
            ListView pop_listview=(ListView)contentview.findViewById(R.id.pop_listview);
            CardView card_popwindow=(CardView)contentview.findViewById(R.id.card_popwindow);
            if (listPopWindowManager.getPopWindowColor()!=0){

                card_popwindow.setCardBackgroundColor(activity.getResources().getColor(listPopWindowManager.getPopWindowColor()));}
            PopStringImgesListAdapter_fillScreen stringImageAdapter_fillScreen=new PopStringImgesListAdapter_fillScreen(list,activity);
            pop_listview.setAdapter(stringImageAdapter_fillScreen);
            WindowManager wm = (WindowManager) activity
                    .getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            final PopupWindow stringImagePopupWindowNoBackground_fillScreen = new PopupWindow(contentview, width/2,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringImagePopupWindowNoBackground_fillScreen.setBackgroundDrawable(new ColorDrawable(0) );
            stringImagePopupWindowNoBackground_fillScreen.setOutsideTouchable(true);
            stringImagePopupWindowNoBackground_fillScreen.setAnimationStyle(android.R.style.Animation_Dialog);
            stringImagePopupWindowNoBackground_fillScreen.setTouchable(true);
            stringImagePopupWindowNoBackground_fillScreen.setFocusable(true);
            stringImagePopupWindowNoBackground_fillScreen.showAsDropDown(view);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringImagePopupWindowNoBackground_fillScreen.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            hashMap_stringImagePopupWindowNoBackground_halfScreen.put(activity,stringImagePopupWindowNoBackground_fillScreen);
        }else{
            if (!hashMap_stringImagePopupWindowNoBackground_halfScreen.get(activity).isShowing()){
                Log.v("pop=","odl");

                ListView pop_listview=(ListView)hashMap_stringImagePopupWindowNoBackground_halfScreen.get(activity).getContentView().findViewById(R.id.pop_listview);
                PopStringImgesListAdapter_fillScreen stringImageAdapter_fillScreen=new PopStringImgesListAdapter_fillScreen(list,activity);
                pop_listview.setAdapter(stringImageAdapter_fillScreen);
                pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        hashMap_stringImagePopupWindowNoBackground_halfScreen.get(activity).dismiss();
                        if (onListPopItemClickListener!=null){
                            onListPopItemClickListener.onClick(i);
                        }
                    }
                });
                hashMap_stringImagePopupWindowNoBackground_halfScreen.get(activity).showAsDropDown(view);
            }
        }
    }
    private void showStringAndImagePopHasBg(List<ListPopModel> list, View view, final Activity activity, final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(activity, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hashMap_stringImagePopupWindowWithBackground.containsKey(activity)){
            Log.v("pop=","new");

            View contentview = LayoutInflater.from(activity).inflate(R.layout.popwindow_list_library, null);
            WidthListView pop_listview=(WidthListView)contentview.findViewById(R.id.pop_listview);
            CardView card_popwindow=(CardView)contentview.findViewById(R.id.card_popwindow);
            if (listPopWindowManager.getPopWindowColor()!=0){

                card_popwindow.setCardBackgroundColor(activity.getResources().getColor(listPopWindowManager.getPopWindowColor()));}
            PopStringImgesListAdapter stringImageAdapter=new PopStringImgesListAdapter(list,activity);
            pop_listview.setAdapter(stringImageAdapter);
            final PopupWindow stringImagePopupWindowWithBackground = new PopupWindow(contentview, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringImagePopupWindowWithBackground.setBackgroundDrawable(new ColorDrawable(0) );
            stringImagePopupWindowWithBackground.setOutsideTouchable(true);
            stringImagePopupWindowWithBackground.setAnimationStyle(android.R.style.Animation_Dialog);
            stringImagePopupWindowWithBackground.setTouchable(true);
            stringImagePopupWindowWithBackground.setFocusable(true);
            stringImagePopupWindowWithBackground.showAsDropDown(view);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringImagePopupWindowWithBackground.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = 0.7f;
            activity.getWindow().setAttributes(lp);
            stringImagePopupWindowWithBackground.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                    lp.alpha = 1f;
                    activity.getWindow().setAttributes(lp);
                }
            });
            hashMap_stringImagePopupWindowWithBackground.put(activity,stringImagePopupWindowWithBackground);
        }else{
            if (!hashMap_stringImagePopupWindowWithBackground.get(activity).isShowing()){
                Log.v("pop=","odl");

                WidthListView pop_listview=(WidthListView)hashMap_stringImagePopupWindowWithBackground.get(activity).getContentView().findViewById(R.id.pop_listview);
                PopStringImgesListAdapter stringImageAdapter=new PopStringImgesListAdapter(list,activity);
                pop_listview.setAdapter(stringImageAdapter);
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 0.7f;
                activity.getWindow().setAttributes(lp);
                hashMap_stringImagePopupWindowWithBackground.get(activity).setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                        lp.alpha = 1f;
                        activity.getWindow().setAttributes(lp);
                    }
                });
                pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                hashMap_stringImagePopupWindowWithBackground.get(activity).dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);
                                }
                            }
                        });
                hashMap_stringImagePopupWindowWithBackground.get(activity).showAsDropDown(view);
            }
        }
    }
    private void showStringAndImagePopHasBg_fillScreen(List<ListPopModel> list, View view, final Activity activity, final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(activity, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hashMap_stringImagePopupWindowWithBackground_fillScreen.containsKey(activity)){
            Log.v("pop=","new");

            View contentview = LayoutInflater.from(activity).inflate(R.layout.popwindow_list_fillparent_library, null);
            ListView pop_listview=(ListView)contentview.findViewById(R.id.pop_listview);
            CardView card_popwindow=(CardView)contentview.findViewById(R.id.card_popwindow);
            if (listPopWindowManager.getPopWindowColor()!=0){
                card_popwindow.setCardBackgroundColor(activity.getResources().getColor(listPopWindowManager.getPopWindowColor()));}
            PopStringImgesListAdapter_fillScreen stringImageAdapter_fillScreen=new PopStringImgesListAdapter_fillScreen(list,activity);
            pop_listview.setAdapter(stringImageAdapter_fillScreen);
            final PopupWindow stringImagePopupWindowWithBackground_fillScreen = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringImagePopupWindowWithBackground_fillScreen.setBackgroundDrawable(new ColorDrawable(0) );
            stringImagePopupWindowWithBackground_fillScreen.setOutsideTouchable(true);
            stringImagePopupWindowWithBackground_fillScreen.setAnimationStyle(android.R.style.Animation_Dialog);
            stringImagePopupWindowWithBackground_fillScreen.setTouchable(true);
            stringImagePopupWindowWithBackground_fillScreen.setFocusable(true);
            stringImagePopupWindowWithBackground_fillScreen.showAsDropDown(view);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringImagePopupWindowWithBackground_fillScreen.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);
                    }
                }
            });
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.alpha = 0.7f;
            activity.getWindow().setAttributes(lp);
            stringImagePopupWindowWithBackground_fillScreen.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                    lp.alpha = 1f;
                    activity.getWindow().setAttributes(lp);
                }
            });
            hashMap_stringImagePopupWindowWithBackground_fillScreen.put(activity,stringImagePopupWindowWithBackground_fillScreen);
        }else{
            if (!hashMap_stringImagePopupWindowWithBackground_fillScreen.get(activity).isShowing()){
                Log.v("pop=","odl");

                ListView pop_listview=(ListView)hashMap_stringImagePopupWindowWithBackground_fillScreen.get(activity).getContentView().findViewById(R.id.pop_listview);
                PopStringImgesListAdapter_fillScreen stringImageAdapter_fillScreen=new PopStringImgesListAdapter_fillScreen(list,activity);
                pop_listview.setAdapter(stringImageAdapter_fillScreen);
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 0.7f;
                activity.getWindow().setAttributes(lp);
                hashMap_stringImagePopupWindowWithBackground_fillScreen.get(activity).setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                        lp.alpha = 1f;
                        activity.getWindow().setAttributes(lp);
                    }
                });
                pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                hashMap_stringImagePopupWindowWithBackground_fillScreen.get(activity).dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);
                                }
                            }
                        });
                hashMap_stringImagePopupWindowWithBackground_fillScreen.get(activity).showAsDropDown(view);
            }
        }
    }
    public PopupWindow showCommonPopWindow(View contentView,View rootView, final Activity activity,boolean hasBackground){
        PopupWindow popView = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popView.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popView.setOutsideTouchable(true);
        popView.setFocusable(true);
        popView.showAsDropDown(rootView);
        // 设置背景颜色变暗
        if (hasBackground){
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.7f;
        activity.getWindow().setAttributes(lp);
        popView.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
        }
        return popView;
    }
    public void onDestoryPopWindow(Activity activity){
        if (hashMap_stringPopupWindowNoBackground.containsKey(activity)){
            if (hashMap_stringPopupWindowNoBackground.get(activity).isShowing()){
                hashMap_stringPopupWindowNoBackground.get(activity).dismiss();
            }
            hashMap_stringPopupWindowNoBackground.remove(activity);
        }
        if (hashMap_stringPopupWindowWithBackground.containsKey(activity)){
            if (hashMap_stringPopupWindowWithBackground.get(activity).isShowing()){
                hashMap_stringPopupWindowWithBackground.get(activity).dismiss();
            }
            hashMap_stringPopupWindowWithBackground.remove(activity);
        }
        if (hashMap_stringImagePopupWindowNoBackground.containsKey(activity)){
            if (hashMap_stringImagePopupWindowNoBackground.get(activity).isShowing()){
                hashMap_stringImagePopupWindowNoBackground.get(activity).dismiss();
            }
            hashMap_stringImagePopupWindowNoBackground.remove(activity);
        }
        if (hashMap_stringImagePopupWindowWithBackground.containsKey(activity)){
            if (hashMap_stringImagePopupWindowWithBackground.get(activity).isShowing()){
                hashMap_stringImagePopupWindowWithBackground.get(activity).dismiss();
            }
            hashMap_stringImagePopupWindowWithBackground.remove(activity);
        }
        if (hashMap_stringPopupWindowNoBackground_fillScreen.containsKey(activity)){
            if (hashMap_stringPopupWindowNoBackground_fillScreen.get(activity).isShowing()){
                hashMap_stringPopupWindowNoBackground_fillScreen.get(activity).dismiss();
            }
            hashMap_stringPopupWindowNoBackground_fillScreen.remove(activity);
        }
        if (hashMap_stringPopupWindowWithBackground_fillScreen.containsKey(activity)){
            if (hashMap_stringPopupWindowWithBackground_fillScreen.get(activity).isShowing()){
                hashMap_stringPopupWindowWithBackground_fillScreen.get(activity).dismiss();
            }
            hashMap_stringPopupWindowWithBackground_fillScreen.remove(activity);
        }
        if (hashMap_stringImagePopupWindowNoBackground_fillScreen.containsKey(activity)){
            if (hashMap_stringImagePopupWindowNoBackground_fillScreen.get(activity).isShowing()){
                hashMap_stringImagePopupWindowNoBackground_fillScreen.get(activity).dismiss();
            }
            hashMap_stringImagePopupWindowNoBackground_fillScreen.remove(activity);
        }
        if (hashMap_stringImagePopupWindowWithBackground_fillScreen.containsKey(activity)){
            if (hashMap_stringImagePopupWindowWithBackground_fillScreen.get(activity).isShowing()){
                hashMap_stringImagePopupWindowWithBackground_fillScreen.get(activity).dismiss();
            }
            hashMap_stringImagePopupWindowWithBackground_fillScreen.remove(activity);
        }
        if (hashMap_stringPopupWindowNoBackground_halfScreen.containsKey(activity)){
            if (hashMap_stringPopupWindowNoBackground_halfScreen.get(activity).isShowing()){
                hashMap_stringPopupWindowNoBackground_halfScreen.get(activity).dismiss();
            }
            hashMap_stringPopupWindowNoBackground_halfScreen.remove(activity);
        }
        if (hashMap_stringImagePopupWindowNoBackground_halfScreen.containsKey(activity)){
            if (hashMap_stringImagePopupWindowNoBackground_halfScreen.get(activity).isShowing()){
                hashMap_stringImagePopupWindowNoBackground_halfScreen.get(activity).dismiss();
            }
            hashMap_stringImagePopupWindowNoBackground_halfScreen.remove(activity);
        }
    }
}
