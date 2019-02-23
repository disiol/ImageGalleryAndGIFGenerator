package com.denisimusIT.imageGalleryAndGIFGenerator.screean.image.generateGIF;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.denisimusIT.imageGalleryAndGIFGenerator.R;

public class GifFragmentDialog extends DialogFragment {

    public static GifFragmentDialog newInstance() {
        return new GifFragmentDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View gifFragmentDialog = inflater.inflate(R.layout.gifimages_generation_fragment, null);

        ImageView gifImageView = (ImageView) gifFragmentDialog.findViewById(R.id.iv_gif_animation);

        GIFImagesGenerationParser gifImagesGenerationParser = new GIFImagesGenerationParser();
        gifImagesGenerationParser.loadGIF(this.getContext(),gifImageView);

        return gifFragmentDialog;
    }


    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

}