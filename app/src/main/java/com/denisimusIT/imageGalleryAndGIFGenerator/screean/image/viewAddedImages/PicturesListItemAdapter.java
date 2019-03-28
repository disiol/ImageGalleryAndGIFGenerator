package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.dto.ImageDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.dto.ImageParamsDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtil;

import java.util.List;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.Constants.LOG_TAG;
import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.setImage;

public class PicturesListItemAdapter extends RecyclerView.Adapter<PicturesListItemAdapter.ViewHolder> {


    public PicturesListItemAdapter(List<ImageDTO> imageDTOList) {
        this.imageDTOList = imageDTOList;
    }


    private List<ImageDTO> imageDTOList;


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ImageDTO imageDTO = imageDTOList.get(position);
        ImageParamsDTO imageParamsDTO = imageDTO.getImageParamsDTO();

        String address = imageParamsDTO.getAddress();
        String weather = imageParamsDTO.getWeather();
        String smallImageUrlPath = imageDTO.getSmallImageUrlPath();
        String bigImageUrlPath = imageDTO.getBigImageUrlPath();


        if (AppUtil.isStringNotEmpty(address)) {

            holder.addressTextView.setText(address);
        }


        if (AppUtil.isStringNotEmpty(weather)) {
            holder.weatherTextView.setText(weather);
        }

        setImage(smallImageUrlPath, holder.dashboardImageView);
        holder.dashboardImageView.setTag(smallImageUrlPath);

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pictures_list_card_image, parent, false);
        return new ViewHolder(view);
    }

    public void notifyAndShow() {
        notifyItemInserted(getItemCount());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageDTOList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView dashboardImageView;

        private TextView addressTextView;

        private TextView weatherTextView;

        private Context context;


        ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            dashboardImageView = itemView.findViewById(R.id.iv_dashboard_image);
            addressTextView = itemView.findViewById(R.id.tv_dashboard_address);
            weatherTextView = itemView.findViewById(R.id.tv_dashboard_weather);

            dashboardImageView.setOnClickListener(this);

        }

        public void onClick(View v) {
            startBigImageActivity();
        }

        private void startBigImageActivity() {
            Intent intent = new Intent(context.getApplicationContext(), BigPicherActivity.class);
            String dashboardImageViewTag = dashboardImageView.getTag().toString();

            Log.d(LOG_TAG, "dashboardImageViewTag:  " + dashboardImageViewTag);

            intent.putExtra("bigImageUrlPath", dashboardImageViewTag);
            context.startActivity(intent);
        }
    }

}