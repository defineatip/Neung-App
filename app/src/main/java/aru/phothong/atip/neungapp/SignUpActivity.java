package aru.phothong.atip.neungapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {
    //Explicit
    private EditText nameEditText,surnameEditText,userEditText, passwordEditText;
    private ImageView imageView;
    private String nameString,surnameString,userString,passwordString, imageString;
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


    }//Main Method

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
        }

    }// clickSignUpsign


}//Main Class
