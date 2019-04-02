package com.denisimusIT.imageGalleryAndGIFGenerator.model.api.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GetAllUserImagesDTO {
    public GetAllUserImagesDTO() {
    }

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
