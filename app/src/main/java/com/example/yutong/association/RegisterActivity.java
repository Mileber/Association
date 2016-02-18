package com.example.yutong.association;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.camera.CropImageIntentBuilder;
import com.bmob.BTPFileResponse;
import com.bmob.BmobProFile;
import com.bmob.btp.callback.UploadListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {

    public final static String USER_NAME = "com.example.yutong.association.USERNAME";

    private String avaterPath = null;
    private String avaterName;
    private EditText mNameView;
    private EditText mEmailAddress;
    private EditText mPasswordView;
    private boolean gender = true;
    private String timeStamp;

    private static int CAMERA_REQUEST_CODE = 1;
    private static int GALLERY_REQUEST_CODE = 2;
    private static int CROP_REQUEST_CODE = 3;

    private UserRegisterTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("注册");

        mNameView = (EditText)findViewById(R.id.register_name);
        mEmailAddress = (EditText)findViewById(R.id.register_email);
        mPasswordView = (EditText)findViewById(R.id.register_password);
    }

    public void onRadioButtonClickedRegister(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton_register_man:
                if (checked)
                    gender = true;
                    break;
            case R.id.radioButton_register_woman:
                if (checked)
                    gender = false;
                    break;
        }
    }

    public void submitRegister(View view){
        // Reset errors.
        mEmailAddress.setError(null);
        mPasswordView.setError(null);
        mNameView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailAddress.getText().toString();
        String password = mPasswordView.getText().toString();
        String name = mNameView.getText().toString();


        boolean cancel = false;
        View focusView_register = null;

        if (TextUtils.isEmpty(name)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView_register = mNameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView_register = mPasswordView;
            cancel = true;
        }else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView_register = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailAddress.setError(getString(R.string.error_field_required));
            focusView_register = mEmailAddress;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailAddress.setError(getString(R.string.error_invalid_email));
            focusView_register = mEmailAddress;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView_register.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new UserRegisterTask(name,email, password);
            mAuthTask.execute((Void) null);
        }
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }

    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final String mName;

        UserRegisterTask(String name, String email, String password) {
            mName = name;
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            Context context = getApplicationContext();
            final MyUser bu = new MyUser();
            bu.setUsername(mName);
            bu.setPassword(mPassword);
            bu.setEmail(mEmail);
            bu.setSex(gender);

            if(avaterPath != null){
                BTPFileResponse response = BmobProFile.getInstance(context).upload(avaterPath, new UploadListener() {

                    @Override
                    public void onSuccess(String fileName,String url,BmobFile file) {
                        bu.setAvaterPath(fileName);
                        Context context = getApplicationContext();
                        Toast.makeText(context, "文件名"+fileName+"文件类型"+"file", Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, bu.getAvaterPath(), Toast.LENGTH_SHORT).show();
                        // TODO Auto-generated method stub
                        // fileName ：文件名（带后缀）
                        // url        ：文件地址
                        // file        :BmobFile文件类型，`V3.4.1版本`开始提供，用于兼容新旧文件服务。
                    }

                    @Override
                    public void onProgress(int progress) {
                        // TODO Auto-generated method stub
                        Log.i("bmob","onProgress :"+progress);
                    }

                    @Override
                    public void onError(int statuscode, String errormsg) {
                        // TODO Auto-generated method stub
                        Log.i("bmob","文件上传失败："+errormsg);
                    }
                });
            }

            bu.signUp(context, new SaveListener() {
                @Override
                public void onSuccess() {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int code, String msg) {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "注册失败：" + msg, Toast.LENGTH_SHORT).show();
                }
            });

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    public void editUserImage(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create(); //Read Update
        alertDialog.setTitle("设置头像");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                "摄像头", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.CHINA).format(new Date());
                        startActivityForResult(intent, CAMERA_REQUEST_CODE);
                    }
                });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                "图库", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.CHINA).format(new Date());
                        intent.setType("image/*");
                        startActivityForResult(intent, GALLERY_REQUEST_CODE);
                    }
                });
        alertDialog.show();

    }

    private Uri saveBitmap(Bitmap bitmap){
        File img = new File(getFilesDir(), "avater_"+timeStamp+".jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(img);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        File img = new File(getFilesDir(), "avater_"+timeStamp+".jpg");
        if(requestCode == CAMERA_REQUEST_CODE){
            if(data == null){
                return;
            }
            Bundle extras = data.getExtras();
            if(extras != null){
                Bitmap bm = extras.getParcelable("data");
                Uri uri = saveBitmap(bm);
                CropImageIntentBuilder cropImage2 = new CropImageIntentBuilder(150, 150, uri);
                cropImage2.setOutlineColor(0xFF03A9F4);
                cropImage2.setSourceImage(uri);
                cropImage2.setOutputQuality(100);
                startActivityForResult(cropImage2.getIntent(this), CROP_REQUEST_CODE);
            }
        }else if (requestCode == GALLERY_REQUEST_CODE) {
            if(data == null){
                return;
            }
            // When the user is done picking a picture, let's start the CropImage Activity,
            // setting the output image file and size to 200x200 pixels square.
            Uri croppedImage = Uri.fromFile(img);

            CropImageIntentBuilder cropImage = new CropImageIntentBuilder(150, 150, croppedImage);
            cropImage.setOutlineColor(0xFF03A9F4);
            cropImage.setSourceImage(data.getData());
            cropImage.setOutputQuality(90);
            startActivityForResult(cropImage.getIntent(this), CROP_REQUEST_CODE);

        } else if (requestCode == CROP_REQUEST_CODE) {
            if(data == null){
                return;
            }
            // When we are done cropping, display it in the ImageView.
            avaterPath = img.getAbsolutePath();
            ImageView imageView = (ImageView)findViewById(R.id.register_userImg);
            imageView.setImageBitmap(BitmapFactory.decodeFile(img.getAbsolutePath()));
        }
    }
}
