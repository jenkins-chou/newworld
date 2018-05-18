package com.jenkins.newworld.adapter.common;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jenkins.newworld.R;

import org.raphets.roundimageview.RoundImageView;

import java.util.ArrayList;

/**
 * Created by zhouzhenjian on 2018/3/20.
 *
 * 通用listview adapter
 */

public abstract class ListViewAdapter<T> extends BaseAdapter implements View.OnClickListener{
    private ArrayList<T> datas; //泛型数据集
    private int layout;//布局id
    public ListViewAdapter(){}
    public ListViewAdapter(ArrayList<T> datas , int layout){
        this.datas = datas;
        this.layout = layout;
    }
    @Override
    public int getCount(){
        return datas !=null ? datas.size():0;
    }
    @Override
    public T getItem(int position){
        return datas.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View concertView, ViewGroup parent){
        ViewHolder holder = ViewHolder.bind(parent.getContext(),concertView,parent,layout,position);
        bindView(holder,getItem(position));

        return holder.getItemView();
    }

    @Override
    public void onClick(View view) {

    }

    public abstract void bindView(ViewHolder holder, T obj);

    public void add(T data){
        if(datas ==null){
            datas = new ArrayList<>();
        }
        datas.add(data);
        notifyDataSetChanged();
    }

    public void add(int position,T data){
        if(datas==null){
            datas = new ArrayList<>();
        }
        datas.add(position,data);
        notifyDataSetChanged();
    }
    public void addList(ArrayList<T> data){
        if(datas ==null){
            datas = new ArrayList<>();
        }
        ArrayList<T> list = (ArrayList<T>)data;
        for(T model:list){
            datas.add((T)model);
        }
        notifyDataSetChanged();
    }
    public void remove(T data){
        if(datas!=null){
            datas.remove(data);
        }
        notifyDataSetChanged();
    }
    public void remove(int position){
        if(datas!=null){
            datas.remove(position);
        }
        notifyDataSetChanged();
    }
    public void clear(){
        if (datas!=null){
            datas.clear();
            System.out.print("datas 已经清空了："+datas.size());
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder{
        private SparseArray<View> views;
        private View item;
        private int position;
        private Context context;

        public ViewHolder(Context context, ViewGroup parent, int layout) {
            views = new SparseArray<>();
            this.context = context;
            View convertView = LayoutInflater.from(context).inflate(layout,parent,false);
            convertView.setTag(this);
            item = convertView;
        }

        //绑定viewholder与item
        public static ViewHolder bind(Context context, View convertView, ViewGroup parent, int layout, int position){
            ViewHolder holder;
            if(convertView ==null){
                holder = new ViewHolder(context,parent,layout);
            }else{
                holder =(ViewHolder)convertView.getTag();
                holder.item = convertView;
            }
            holder.position = position;
            return holder;
        }
        @SuppressWarnings("unchecked")
        public <T extends View>T getView(int id){
            T t = (T)views.get(id);
            if(t==null){
                t = (T)item.findViewById(id);
                views.put(id,t);
            }
            return t;
        }

        public View getItemView(){
            return item;
        }

        public int getItemPosition(){
            return position;
        }
        public ViewHolder setText(int id,CharSequence text){
            View view = getView(id);
            if(view instanceof TextView){
                ((TextView)view).setText(text);
            }
            return this;
        }
        public ViewHolder setImage(int id,int drawableRes){
            View view = getView(id);
            if(view instanceof ImageView){
                ((ImageView)view).setImageResource(drawableRes);
            }else{
                view.setBackgroundResource(drawableRes);
            }
            return this;
        }
        public ViewHolder setImage(int id,String url){
            View view = getView(id);
            Glide.with(context).load(url).error(R.drawable.img_default).into((ImageView)view);
            if(view instanceof ImageView){
                Glide.with(context).load(url).error(R.drawable.img_default).into((ImageView)view);
            }else{
                Glide.with(context).load(url).error(R.drawable.img_default).into((ImageView)view);
            }
            return this;
        }
        public ViewHolder setRoundImage(int id,String url){
            View view = getView(id);
            if(view instanceof RoundImageView){
                Glide.with(context).load(url).error(R.mipmap.frag_live_start_bg).into((RoundImageView)view);
            }else{
                Glide.with(context).load(url).error(R.mipmap.frag_live_start_bg).into((RoundImageView)view);
            }
            return this;
        }

        public ViewHolder setOnClickListener(int id,View.OnClickListener listener){
            getView(id).setOnClickListener(listener);
            return this;
        }

        public ViewHolder setVisibility(int id,int visiable){
            getView(id).setVisibility(visiable);
            return this;
        }
        /**
         * 设置标签
         */
        public ViewHolder setTag(int id, Object obj) {
            getView(id).setTag(obj);
            return this;
        }
    }
}
