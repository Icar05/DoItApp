package com.gmail.ipinguin_linuxoid.doitapplication.mvp.ui.fragment.blank.base;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;

import com.gmail.ipinguin_linuxoid.doitapplication.MainActivity;
import com.gmail.ipinguin_linuxoid.doitapplication.R;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.AddImageDialogHelper;
import com.gmail.ipinguin_linuxoid.doitapplication.utils.ErrorHandlerHelper;
import com.gmail.ipinguin_linuxoid.doitapplication.dependency.LoadImageHelper;

import javax.inject.Inject;

/**
 * вся нагрузка по получению фоток
 */

public abstract class RequestImageFragment extends UtilFragment {


    protected   final int REQUEST_CAMERA = 1888;
    protected   final int SELECT_FILE = 1889;
    protected   String imageUrl = null;

    //помогает найти путь к картинке
    @Inject
    LoadImageHelper loadImageHelper;

    protected AlertDialog addImageDialog;
    protected AddImageDialogHelper helper;






    //наследник должен уметть показывать картинку
    public abstract void showImage(String imageUrl);





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new AddImageDialogHelper(getContext());
        addImageDialog = helper.showAddImageDialog(new AddImageDialogHelper.IAddImageDialogDelegate() {
            @Override
            public void onCameraWasChosen() {
                cameraIntent();
            }

            @Override
            public void onGaleryWasChosen() {
                galleryIntent();
            }
        });

        ((MainActivity)getActivity()).getActivityComponent().inject(this);

    }



    /*
     обработка результатов ответов от
     активности галереи
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                imageUrl = loadImageHelper.getRealPathFromURI(data, getContext());
            }
            else if (requestCode == REQUEST_CAMERA) {
                imageUrl = loadImageHelper.onCaptureImageResult(data);
            }


            if (imageUrl != null) {
                showImage(imageUrl);
            }else{
                ErrorHandlerHelper.onEasyError(getContext(), getString(R.string.can_not_get_photo));
            }
        }
    }




    /*
     запуск разных интентов для картинок
     */
    protected void galleryIntent() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECT_FILE);
    }

    protected void cameraIntent() {


            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ErrorHandlerHelper.onPermissionErrorCamera(getContext());
            }else{
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CAMERA);
            }

    }
}
