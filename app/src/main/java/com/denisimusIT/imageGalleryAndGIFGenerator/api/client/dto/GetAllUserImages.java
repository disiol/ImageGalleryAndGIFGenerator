package com.denisimusIT.imageGalleryAndGIFGenerator.api.client.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class GetAllUserImages {
    public GetAllUserImages(){}

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
        return "GetAllUserImages{" +
                "imageDTOList=" + imageDTOList +
                '}';
    }
}
