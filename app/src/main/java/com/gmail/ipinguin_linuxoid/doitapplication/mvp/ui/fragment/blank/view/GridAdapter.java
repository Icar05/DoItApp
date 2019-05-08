package com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmail.ipinguin_linuxoid.doitapplication.R;
import com.gmail.ipinguin_linuxoid.doitapplication.mvp.model.repository.ImageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * простенький адаптер для грид, потом поменяю
 */
public class GridAdapter extends BaseAdapter {



    List<ImageEntity> data = new ArrayList<>();
    View emptyView;
    View parentView;

    public void setData(List<ImageEntity> data) {
        this.data = data;


        if(data.size() > 0){
            emptyView.setVisibility(View.GONE);
            parentView.setVisibility(View.VISIBLE);
            notifyDataSetChanged();
        }else{
            emptyView.setVisibility(View.VISIBLE);
            parentView.setVisibility(View.GONE);
        }


    }

    public void setParentView(View parentView) {
        this.parentView = parentView;
    }

    public void setEmptyView(LinearLayout emptyView) {
        this.emptyView = emptyView;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ImageEntity getItem(int position) {
        return  data.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }




    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;


        if (convertView == null) {

            mViewHolder = new ViewHolder();

            LayoutInflater vi = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.grid_item, parent, false);
            mViewHolder.iv_image = (SquareImageView) convertView.findViewById(R.id.iv_image);
            mViewHolder.weather = (TextView) convertView.findViewById(R.id.tv_weather);
            mViewHolder.address = (TextView) convertView.findViewById(R.id.tv_address);
            convertView.setTag(mViewHolder);




        } else {

            mViewHolder = (ViewHolder) convertView.getTag();

        }


        Glide.with(parent.getContext())
                .load(data.get(position).getImage_url())
                .asBitmap()
                .placeholder(R.drawable.default_img)
                .error(R.drawable.default_img)
                .into(mViewHolder.iv_image);




        mViewHolder.weather.setText(data.get(position).getWeather());
        mViewHolder.address.setText(data.get(position).getAddress());

        return convertView;
    }




   private static class ViewHolder {


        private SquareImageView iv_image;
        private TextView weather;
        private TextView address;

    }


}

