package aru.phothong.atip.neungapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.jibble.simpleftp.SimpleFTP;

import java.io.File;

public class SignUpActivity extends AppCompatActivity {
    //Explicit
    private EditText nameEditText,surnameEditText,userEditText, passwordEditText;
    private ImageView imageView;
    private String nameString,surnameString,userString,
            passwordString, imageString,imagePathString,imageNameString;
    private boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        imageView = (ImageView) findViewById(R.id.imageView);

        //Image Controller
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "โปรดเลือกรูปภาพ"), 0);


            }//onClick
        });


    }//Main Method


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode==0)&&(resultCode==RESULT_OK)) {

            aBoolean = false;

            //Show CHoose Image on ImageView
            Uri uri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory
                        .decodeStream(getContentResolver()
                                .openInputStream(uri));
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();

            }
            // Find Path and Name of Image Choosed
            imagePathString = myFindPath(uri);
            Log.d("NeungV1", "imagePathString==>" + imagePathString);
            imageNameString = imagePathString.substring(imagePathString.lastIndexOf("/"));
            Log.d("NeungV1", "imageNameString ==>" + imageNameString);
        }//if

    }//onActivityResult

    private String myFindPath(Uri uri) {
        String strResulte = null;

        String[] strings = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri,strings,
                null,null,null);
        if (cursor != null) {

            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            strResulte = cursor.getString(index);
        } else {
            strResulte = uri.getPath();
        }

        return strResulte;
    }

    public void clicksignUpsign(View view) {
        //get Value From Edit Text
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        //Check Space
        if (nameString.equals("")|| surnameString.equals("")||
                userString.equals("")|| passwordString.equals("")){
            //Have Space
            MyAlert myAlert = new MyAlert(this, "มีช่องว่าง", "กรุณากรอกทุกช่อง ด้วยครับ");
            myAlert.myDialog();
        } else if (aBoolean) {
            //Non choose image
            MyAlert myAlert = new MyAlert(this,"ยังไม่ได้เลือกรูป",
                    "กรุณาคลิกที่รูปภาพ เพื่อเลือกรูป");
            myAlert.myDialog();

        } else {
            //Chonose image Finish
            uploadImageToServer();




        }

    }// clickSignUpsign

    private void uploadImageToServer() {

        //Change Police
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        try {
            SimpleFTP simpleFTP = new SimpleFTP();
            simpleFTP.connect("ftp.swiftcodingthai.com", 21,
                    "one@swiftcodingthai.com","Abc12345 ");
            simpleFTP.bin();
            simpleFTP.cwd("Image");
            simpleFTP.stor(new File(imagePathString));
            simpleFTP.disconnect();
            Toast.makeText(this, "Save Image Finish",Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            e.printStackTrace();
        }


        //uploadimageToServer

    }


}//Main Class
