package com.zillion.delhibelly.liftsManager;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zillion.delhibelly.liftsManager.Helpers.CameraHandler;

public class Form extends AppCompatActivity {


    public final static String DEBUG_TAG = "ChildBasics Act";
    private static int REQUEST_PICTURE = 1;
    private static int REQUEST_CROP_PICTURE = 2;
    private static int REQUEST_CLICK_PICTURE = 3;
    private static int REQUEST_CROP_PICTURE_FROM_CAMERA = 4;
    public Bundle getBundle = null;
    private String clicked;
    private LinearLayout parent_root;
    private Camera camera;
    private CameraHandler cameraHandler;
    private boolean backButtonDisabled = false;
    private ImageView camBt;
    private int cameraId = 0;
    private byte[] imageCaptured;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getBundle = this.getIntent().getExtras();
        int x = getIntent().getExtras().getInt("selected");
        switch (x) {
            case 1:
                setContentView(com.zillion.delhibelly.liftsManager.R.layout.form_ac);
                break;
            case 2:
                setContentView(com.zillion.delhibelly.liftsManager.R.layout.form_computer);
                break;
            case 3:
                setContentView(com.zillion.delhibelly.liftsManager.R.layout.form_washing);
                break;
            case 4:
                setContentView(com.zillion.delhibelly.liftsManager.R.layout.form_tv);
                break;
            case 5:
                setContentView(com.zillion.delhibelly.liftsManager.R.layout.form_furniture);
                break;
            case 6:
                setContentView(com.zillion.delhibelly.liftsManager.R.layout.form_refrigerator);
                break;
            case 7:
                setContentView(com.zillion.delhibelly.liftsManager.R.layout.form_ac);
                break;
        }

        cameraHandler = new CameraHandler(this, new CameraHandler.CameraHandlerDone() {
            @Override
            public void onCropped() {
                backButtonDisabled = false;
            }
        });


        /******************************************************************************************************** */

        try {
            // do we have a camera?
            if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG).show();
            } else {
                cameraId = findFrontFacingCamera();
                if (cameraId < 0) {
                    Toast.makeText(this, "No front facing camera found.",
                            Toast.LENGTH_LONG).show();
                } else {
                    camera = Camera.open(cameraId);
                }
            }
        } catch (Exception e) {
            Log.i(DEBUG_TAG, "Error in camera");
        }
        /******************************************************************************************************** */

        camBt = (ImageView) findViewById(com.zillion.delhibelly.liftsManager.R.id.parent_child_basics_plus_add_pic);
        cameraHandler.setIvPicture(camBt);
        camBt.setOnClickListener(new View.OnClickListener() {
            /**
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                backButtonDisabled = false;
//                cameraHandler.showView();
                dispatchTakePictureIntent();
                /*
                camera.startPreview();
                camera.takePicture(null, null, new PhotoHandler(cT));
            */
            }
        });
        /******************************************************************************************************** */

    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                Log.d(DEBUG_TAG, "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    private void dispatchTakePictureIntent() {

        AlertDialog.Builder getImageFrom = new AlertDialog.Builder(this);
        getImageFrom.setTitle("Select:");
        final CharSequence[] opsChars = {"Camera", "Gallery"};
        getImageFrom.setItems(opsChars, new android.content.DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_CLICK_PICTURE);
                    }

                } else if (which == 1) {

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select picture"), REQUEST_PICTURE);

                }
                dialog.dismiss();
            }
        });
        getImageFrom.show();

    }


}


