package com.android.listpoplibrary;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.listpoplibrary.adapter.PopStringImgesListAdapter;
import com.android.listpoplibrary.adapter.PopStringImgesListAdapter_fillScreen;
import com.android.listpoplibrary.adapter.PopStringListAdapter;
import com.android.listpoplibrary.adapter.PopStringListAdapter_fillScreen;
import com.android.listpoplibrary.model.ListPopModel;
import com.android.listpoplibrary.view.WidthListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radio on 2017/11/1.
 */

public class ListPopWindowHelp {
    public interface onListPopItemClickListener{
        void onClick(int position);
    }
    private static ListPopWindowHelp listPopWindowHelp;
    private PopupWindow stringPopupWindowNoBackground;
    private PopupWindow stringPopupWindowWithBackground;

    private PopupWindow stringImagePopupWindowNoBackground;
    private PopupWindow stringImagePopupWindowWithBackground;

    private PopupWindow stringPopupWindowNoBackground_fillScreen;
    private PopupWindow stringPopupWindowWithBackground_fillScreen;

    private PopupWindow stringImagePopupWindowNoBackground_fillScreen;
    private PopupWindow stringImagePopupWindowWithBackground_fillScreen;

    private PopStringListAdapter stringAdapter;
    private PopStringImgesListAdapter stringImageAdapter;

    private PopStringListAdapter_fillScreen stringAdapter_fillScreen;
    private PopStringImgesListAdapter_fillScreen stringImageAdapter_fillScreen;
    private List<String> list_strings;
    private List<ListPopModel> list_string_images;
    public static ListPopWindowHelp getInStance(){
        if (listPopWindowHelp==null){
            synchronized (ListPopWindowHelp.class){
                if (listPopWindowHelp==null){
                    listPopWindowHelp=new ListPopWindowHelp();
                }
            }
        }
        return listPopWindowHelp;
    }
    public void showStringPopNoBg(List<String> list,View view,final Activity context,final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(context, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stringPopupWindowNoBackground==null){
        View contentview = LayoutInflater.from(context).inflate(R.layout.popwindow_list, null);
        WidthListView pop_listview=(WidthListView)contentview.findViewById(R.id.pop_listview);
         list_strings=new ArrayList<>();
         stringAdapter=new PopStringListAdapter(list_strings,context);
        pop_listview.setAdapter(stringAdapter);
            list_strings.addAll(list);
            stringAdapter.setNotify(context);
            stringPopupWindowNoBackground = new PopupWindow(contentview, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
            stringPopupWindowNoBackground.setBackgroundDrawable(new ColorDrawable(0) );
            stringPopupWindowNoBackground.setOutsideTouchable(true);
            stringPopupWindowNoBackground.setAnimationStyle(android.R.style.Animation_Dialog);
            stringPopupWindowNoBackground.setTouchable(true);
            stringPopupWindowNoBackground.setFocusable(true);
            stringPopupWindowNoBackground.showAsDropDown(view,0,0);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                stringPopupWindowNoBackground.dismiss();
                if (onListPopItemClickListener!=null){
                onListPopItemClickListener.onClick(i);}
            }
        });
        }else{
            if (!stringPopupWindowNoBackground.isShowing()){
                list_strings.clear();
                list_strings.addAll(list);
                stringAdapter.setNotify(context);
                ((WidthListView)stringPopupWindowNoBackground.getContentView().findViewById(R.id.pop_listview))
                        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                stringPopupWindowNoBackground.dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);}
                            }
                        });
                stringPopupWindowNoBackground.showAsDropDown(view,0,0);
            }
        }
    }
    public void showStringPopNoBg_fillScreen(List<String> list,View view,final Activity context,final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(context, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stringPopupWindowNoBackground_fillScreen==null){
            View contentview = LayoutInflater.from(context).inflate(R.layout.popwindow_list_fillparent, null);
            ListView pop_listview=(ListView)contentview.findViewById(R.id.pop_listview);
            list_strings=new ArrayList<>();
            stringAdapter_fillScreen=new PopStringListAdapter_fillScreen(list_strings,context);
            pop_listview.setAdapter(stringAdapter_fillScreen);
            list_strings.addAll(list);
            stringAdapter_fillScreen.setNotify(context);
            stringPopupWindowNoBackground_fillScreen = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringPopupWindowNoBackground_fillScreen.setBackgroundDrawable(new ColorDrawable(0) );
            stringPopupWindowNoBackground_fillScreen.setOutsideTouchable(true);
            stringPopupWindowNoBackground_fillScreen.setAnimationStyle(android.R.style.Animation_Dialog);
            stringPopupWindowNoBackground_fillScreen.setTouchable(true);
            stringPopupWindowNoBackground_fillScreen.setFocusable(true);
            stringPopupWindowNoBackground_fillScreen.showAsDropDown(view,0,0);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringPopupWindowNoBackground_fillScreen.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
        }else{
            if (!stringPopupWindowNoBackground_fillScreen.isShowing()){
                list_strings.clear();
                list_strings.addAll(list);
                stringAdapter_fillScreen.setNotify(context);
                ((ListView)stringPopupWindowNoBackground_fillScreen.getContentView().findViewById(R.id.pop_listview))
                        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                stringPopupWindowNoBackground_fillScreen.dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);}
                            }
                        });
                stringPopupWindowNoBackground_fillScreen.showAsDropDown(view,0,0);
            }
        }
    }

    public void showStringPopHasBg( List<String> list,View view,final Activity context,final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(context, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stringPopupWindowWithBackground==null){
            View contentview = LayoutInflater.from(context).inflate(R.layout.popwindow_list, null);
            WidthListView pop_listview=(WidthListView)contentview.findViewById(R.id.pop_listview);
            list_strings=new ArrayList<>();
            stringAdapter=new PopStringListAdapter(list_strings,context);
            pop_listview.setAdapter(stringAdapter);
            list_strings.addAll(list);
            stringAdapter.setNotify(context);
            stringPopupWindowWithBackground = new PopupWindow(contentview, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringPopupWindowWithBackground.setBackgroundDrawable(new ColorDrawable(0) );
            stringPopupWindowWithBackground.setOutsideTouchable(true);
            stringPopupWindowWithBackground.setAnimationStyle(android.R.style.Animation_Dialog);
            stringPopupWindowWithBackground.setTouchable(true);
            stringPopupWindowWithBackground.setFocusable(true);
            stringPopupWindowWithBackground.showAsDropDown(view,0,0);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringPopupWindowWithBackground.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = context.getWindow().getAttributes();
            lp.alpha = 0.7f;
            context.getWindow().setAttributes(lp);
            stringPopupWindowWithBackground.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                    lp.alpha = 1f;
                    context.getWindow().setAttributes(lp);
                }
            });
        }else{
            if (!stringPopupWindowWithBackground.isShowing()){
                list_strings.clear();
                list_strings.addAll(list);
                stringAdapter.setNotify(context);
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 0.7f;
                context.getWindow().setAttributes(lp);
                stringPopupWindowWithBackground.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                        lp.alpha = 1f;
                        context.getWindow().setAttributes(lp);
                    }
                });
                ((WidthListView)stringPopupWindowWithBackground.getContentView().findViewById(R.id.pop_listview))
                        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                stringPopupWindowWithBackground.dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);}
                            }
                        });
                stringPopupWindowWithBackground.showAsDropDown(view,0,0);
            }
        }
    }
    public void showStringPopHasBg_fillScreen( List<String> list,View view,final Activity context,final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(context, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stringPopupWindowWithBackground_fillScreen==null){
            View contentview = LayoutInflater.from(context).inflate(R.layout.popwindow_list_fillparent, null);
            ListView pop_listview=(ListView)contentview.findViewById(R.id.pop_listview);
            list_strings=new ArrayList<>();
            stringAdapter_fillScreen=new PopStringListAdapter_fillScreen(list_strings,context);
            pop_listview.setAdapter(stringAdapter_fillScreen);
            list_strings.addAll(list);
            stringAdapter_fillScreen.setNotify(context);
            stringPopupWindowWithBackground_fillScreen = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringPopupWindowWithBackground_fillScreen.setBackgroundDrawable(new ColorDrawable(0) );
            stringPopupWindowWithBackground_fillScreen.setOutsideTouchable(true);
            stringPopupWindowWithBackground_fillScreen.setAnimationStyle(android.R.style.Animation_Dialog);
            stringPopupWindowWithBackground_fillScreen.setTouchable(true);
            stringPopupWindowWithBackground_fillScreen.setFocusable(true);
            stringPopupWindowWithBackground_fillScreen.showAsDropDown(view,0,0);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringPopupWindowWithBackground_fillScreen.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = context.getWindow().getAttributes();
            lp.alpha = 0.7f;
            context.getWindow().setAttributes(lp);
            stringPopupWindowWithBackground_fillScreen.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                    lp.alpha = 1f;
                    context.getWindow().setAttributes(lp);
                }
            });
        }else{
            if (!stringPopupWindowWithBackground_fillScreen.isShowing()){
                list_strings.clear();
                list_strings.addAll(list);
                stringAdapter_fillScreen.setNotify(context);
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 0.7f;
                context.getWindow().setAttributes(lp);
                stringPopupWindowWithBackground_fillScreen.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                        lp.alpha = 1f;
                        context.getWindow().setAttributes(lp);
                    }
                });
                ((ListView)stringPopupWindowWithBackground_fillScreen.getContentView().findViewById(R.id.pop_listview))
                        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                stringPopupWindowWithBackground_fillScreen.dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);
                                }
                            }
                        });
                stringPopupWindowWithBackground_fillScreen.showAsDropDown(view,0,0);
            }
        }
    }
    public void showStringAndImagePopNoBg(List<ListPopModel> list, View view, final Activity context, final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(context, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stringImagePopupWindowNoBackground==null){
            View contentview = LayoutInflater.from(context).inflate(R.layout.popwindow_list, null);
            WidthListView pop_listview=(WidthListView)contentview.findViewById(R.id.pop_listview);
            list_string_images=new ArrayList<>();
            stringImageAdapter=new PopStringImgesListAdapter(list_string_images,context);
            pop_listview.setAdapter(stringImageAdapter);
            list_string_images.addAll(list);
            stringImageAdapter.setNotify(context);
            stringImagePopupWindowNoBackground = new PopupWindow(contentview, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringImagePopupWindowNoBackground.setBackgroundDrawable(new ColorDrawable(0) );
            stringImagePopupWindowNoBackground.setOutsideTouchable(true);
            stringImagePopupWindowNoBackground.setAnimationStyle(android.R.style.Animation_Dialog);
            stringImagePopupWindowNoBackground.setTouchable(true);
            stringImagePopupWindowNoBackground.setFocusable(true);
            stringImagePopupWindowNoBackground.showAsDropDown(view,0,0);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringImagePopupWindowNoBackground.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
        }else{
            if (!stringImagePopupWindowNoBackground.isShowing()){
                list_string_images.clear();
                list_string_images.addAll(list);
                stringImageAdapter.setNotify(context);
                ((WidthListView)stringImagePopupWindowNoBackground.getContentView().findViewById(R.id.pop_listview))
                        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                stringImagePopupWindowNoBackground.dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);
                                }
                            }
                        });
                stringImagePopupWindowNoBackground.showAsDropDown(view,0,0);
            }
        }
    }
    public void showStringAndImagePopNoBg_fillScreen(List<ListPopModel> list, View view, final Activity context, final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(context, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stringImagePopupWindowNoBackground_fillScreen==null){
            View contentview = LayoutInflater.from(context).inflate(R.layout.popwindow_list_fillparent, null);
            ListView pop_listview=(ListView)contentview.findViewById(R.id.pop_listview);
            list_string_images=new ArrayList<>();
            stringImageAdapter_fillScreen=new PopStringImgesListAdapter_fillScreen(list_string_images,context);
            pop_listview.setAdapter(stringImageAdapter_fillScreen);
            list_string_images.addAll(list);
            stringImageAdapter_fillScreen.setNotify(context);
            stringImagePopupWindowNoBackground_fillScreen = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringImagePopupWindowNoBackground_fillScreen.setBackgroundDrawable(new ColorDrawable(0) );
            stringImagePopupWindowNoBackground_fillScreen.setOutsideTouchable(true);
            stringImagePopupWindowNoBackground_fillScreen.setAnimationStyle(android.R.style.Animation_Dialog);
            stringImagePopupWindowNoBackground_fillScreen.setTouchable(true);
            stringImagePopupWindowNoBackground_fillScreen.setFocusable(true);
            stringImagePopupWindowNoBackground_fillScreen.showAsDropDown(view,0,0);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringImagePopupWindowNoBackground_fillScreen.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
        }else{
            if (!stringImagePopupWindowNoBackground_fillScreen.isShowing()){
                list_string_images.clear();
                list_string_images.addAll(list);
                stringImageAdapter_fillScreen.setNotify(context);
                ((ListView)stringImagePopupWindowNoBackground_fillScreen.getContentView().findViewById(R.id.pop_listview))
                        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                stringImagePopupWindowNoBackground_fillScreen.dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);
                                }
                            }
                        });
                stringImagePopupWindowNoBackground_fillScreen.showAsDropDown(view,0,0);
            }
        }
    }
    public void showStringAndImagePopHasBg(List<ListPopModel> list, View view, final Activity context, final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(context, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stringImagePopupWindowWithBackground==null){
            View contentview = LayoutInflater.from(context).inflate(R.layout.popwindow_list, null);
            WidthListView pop_listview=(WidthListView)contentview.findViewById(R.id.pop_listview);
            list_string_images=new ArrayList<>();
            stringImageAdapter=new PopStringImgesListAdapter(list_string_images,context);
            pop_listview.setAdapter(stringImageAdapter);
            list_string_images.addAll(list);
            stringImageAdapter.setNotify(context);
            stringImagePopupWindowWithBackground = new PopupWindow(contentview, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringImagePopupWindowWithBackground.setBackgroundDrawable(new ColorDrawable(0) );
            stringImagePopupWindowWithBackground.setOutsideTouchable(true);
            stringImagePopupWindowWithBackground.setAnimationStyle(android.R.style.Animation_Dialog);
            stringImagePopupWindowWithBackground.setTouchable(true);
            stringImagePopupWindowWithBackground.setFocusable(true);
            stringImagePopupWindowWithBackground.showAsDropDown(view,0,0);
            pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    stringImagePopupWindowWithBackground.dismiss();
                    if (onListPopItemClickListener!=null){
                        onListPopItemClickListener.onClick(i);}
                }
            });
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = context.getWindow().getAttributes();
            lp.alpha = 0.7f;
            context.getWindow().setAttributes(lp);
            stringImagePopupWindowWithBackground.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                    lp.alpha = 1f;
                    context.getWindow().setAttributes(lp);
                }
            });
        }else{
            if (!stringImagePopupWindowWithBackground.isShowing()){
                list_string_images.clear();
                list_string_images.addAll(list);
                stringImageAdapter.setNotify(context);
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 0.7f;
                context.getWindow().setAttributes(lp);
                stringImagePopupWindowWithBackground.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                        lp.alpha = 1f;
                        context.getWindow().setAttributes(lp);
                    }
                });
                ((WidthListView)stringImagePopupWindowWithBackground.getContentView().findViewById(R.id.pop_listview))
                        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                stringImagePopupWindowWithBackground.dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);
                                }
                            }
                        });
                stringImagePopupWindowWithBackground.showAsDropDown(view,0,0);
            }
        }
    }
    public void showStringAndImagePopHasBg_fillScreen(List<ListPopModel> list, View view, final Activity context, final onListPopItemClickListener onListPopItemClickListener){
        if (list==null){
            Toast.makeText(context, "数据为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stringImagePopupWindowWithBackground_fillScreen==null){
            View contentview = LayoutInflater.from(context).inflate(R.layout.popwindow_list_fillparent, null);
            ListView pop_listview=(ListView)contentview.findViewById(R.id.pop_listview);
            list_string_images=new ArrayList<>();
            stringImageAdapter_fillScreen=new PopStringImgesListAdapter_fillScreen(list_string_images,context);
            pop_listview.setAdapter(stringImageAdapter_fillScreen);
            list_string_images.addAll(list);
            stringImageAdapter_fillScreen.setNotify(context);
            stringImagePopupWindowWithBackground_fillScreen = new PopupWindow(contentview, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            stringImagePopupWindowWithBackground_fillScreen.setBackgroundDrawable(new ColorDrawable(0) );
            stringImagePopupWindowWithBackground_fillScreen.setOutsideTouchable(true);
            stringImagePopupWindowWithBackground_fillScreen.setAnimationStyle(android.R.style.Animation_Dialog);
            stringImagePopupWindowWithBackground_fillScreen.setTouchable(true);
            stringImagePopupWindowWithBackground_fillScreen.setFocusable(true);
            stringImagePopupWindowWithBackground_fillScreen.showAsDropDown(view,0,0);
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
            WindowManager.LayoutParams lp = context.getWindow().getAttributes();
            lp.alpha = 0.7f;
            context.getWindow().setAttributes(lp);
            stringImagePopupWindowWithBackground_fillScreen.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                    lp.alpha = 1f;
                    context.getWindow().setAttributes(lp);
                }
            });
        }else{
            if (!stringImagePopupWindowWithBackground_fillScreen.isShowing()){
                list_string_images.clear();
                list_string_images.addAll(list);
                stringImageAdapter_fillScreen.setNotify(context);
                WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                lp.alpha = 0.7f;
                context.getWindow().setAttributes(lp);
                stringImagePopupWindowWithBackground_fillScreen.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
                        lp.alpha = 1f;
                        context.getWindow().setAttributes(lp);
                    }
                });
                ((ListView)stringImagePopupWindowWithBackground_fillScreen.getContentView().findViewById(R.id.pop_listview))
                        .setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                stringImagePopupWindowWithBackground_fillScreen.dismiss();
                                if (onListPopItemClickListener!=null){
                                    onListPopItemClickListener.onClick(i);
                                }
                            }
                        });
                stringImagePopupWindowWithBackground_fillScreen.showAsDropDown(view,0,0);
            }
        }
    }
}
