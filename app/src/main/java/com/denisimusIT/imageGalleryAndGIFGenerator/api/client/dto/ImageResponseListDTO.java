package com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ImageResponseListDTO {
//TODO

    public ImageResponseListDTO(){}

    @SerializedName("images")
    private List<ImageDTO> imageDTOList;

    public List<ImageDTO> getImageDTOList() {
        return imageDTOList;
    }

    public void setImageDTOList(List<ImageDTO> imageDTOList) {
        this.imageDTOList = imageDTOList;
    }

    @Override
    public String toString() {
        return "ImageResponseListDTO{" +
                "imageDTOList=" + imageDTOList +
                '}';
    }
}
