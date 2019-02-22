package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.viewAddedImages;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.ImageDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto.ImageParamsDTO;
import com.denisimusIT.imageGalleryAndGIFGenerator.util.AppUtil;

import java.util.List;

import static com.denisimusIT.imageGalleryAndGIFGenerator.util.FileUtils.getImage;

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


        if (AppUtil.isStringNotEmpty(address)) {

            holder.addressTextView.setText(address);
        }


        if (AppUtil.isStringNotEmpty(weather)) {
            holder.weatherTextView.setText(weather);
        }

        getImage(smallImageUrlPath, holder.dashboardImageView); //TODO

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


    static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView dashboardImageView;

        public TextView addressTextView;

        public TextView weatherTextView;

        ViewHolder(View itemView) {
            super(itemView);

            dashboardImageView = itemView.findViewById(R.id.iv_dashboard_image);
            addressTextView = itemView.findViewById(R.id.tv_dashboard_address);
            weatherTextView = itemView.findViewById(R.id.tv_dashboard_weather);

        }
    }

}