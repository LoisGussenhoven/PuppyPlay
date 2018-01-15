package com.example.loisgussenhoven.puppyplay;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.loisgussenhoven.puppyplay.datalayer.DBObject;
import com.example.loisgussenhoven.puppyplay.entity.Dog;
import com.example.loisgussenhoven.puppyplay.entity.FriendSession;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SocialActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView scannerView;
    TextView tvDogNameSelf;
    TextView tvDogName;
    TextView tvDogName2;
    TextView tvOwnerName;
    ImageView ivLayerOther1;
    ImageView ivLayerOther2;
    ImageView ivLayerOther3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        init();
    }

    private void init(){
        tvDogNameSelf = findViewById(R.id.social_tv_name_self);
        tvDogNameSelf.setText(Manager.yourDog.getName());

        tvDogName = findViewById(R.id.social_tv_dog_name_other);
        tvDogName2 = findViewById(R.id.social_tv_name_other);
        tvOwnerName = findViewById(R.id.social_tv_owner_name_other);
        ivLayerOther1 = findViewById(R.id.social_iv_dog_other_1);
        ivLayerOther2 = findViewById(R.id.social_iv_dog_other_2);
        ivLayerOther3 = findViewById(R.id.social_iv_dog_other_3);

        ImageView layer = findViewById(R.id.social_iv_dog_self_1);
        layer.setColorFilter(Color.parseColor("#" + Manager.yourDog.getColor1()), PorterDuff.Mode.MULTIPLY );
        ImageView layer2 = findViewById(R.id.social_iv_dog_self_2);
        layer2.setColorFilter(Color.parseColor("#" + Manager.yourDog.getColor2()), PorterDuff.Mode.MULTIPLY );

        ImageButton ibQr = findViewById(R.id.social_ib_qr);
        ibQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeQrCode(
                        Manager.yourDog.getName() + "&&" +
                                Manager.yourDog.getNameOwner() + "&&" +
                                Manager.yourDog.getGender() + "&&" +
                                Manager.yourDog.getColor1() + "&&" +
                                Manager.yourDog.getColor2()
                );
            }
        });

        ImageButton ibScan = findViewById(R.id.social_ib_camera);
        ibScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan();
            }
        });

        Button btnBack = findViewById(R.id.social_btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(i);
            }
        });
    }

    private void update(String dog, String name, String color1, String color2){
        Log.i("UPDATE", dog + " " + name);

        tvDogName.setVisibility(View.VISIBLE);
        tvDogName2.setVisibility(View.VISIBLE);
        tvOwnerName.setVisibility(View.VISIBLE);
        ivLayerOther1.setVisibility(View.VISIBLE);
        ivLayerOther2.setVisibility(View.VISIBLE);
        ivLayerOther3.setVisibility(View.VISIBLE);

        ivLayerOther1.setColorFilter(Color.parseColor("#" + color1), PorterDuff.Mode.MULTIPLY );
        ivLayerOther2.setColorFilter(Color.parseColor("#" + color2), PorterDuff.Mode.MULTIPLY );

        tvDogName.setText(dog);
        tvDogName2.setText(dog);
        tvOwnerName.setText(name);

    }

    private void writeQrCode(String input){
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(input, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            showImage(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public void showImage(Bitmap bitmap) {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //nothing;
            }
        });

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();
    }

    void scan() {
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            scannerView.stopCamera();
        } catch (Exception e) {
        }
    }

    @Override
    public void handleResult(Result result) {
        String[] results  = result.getText().split("&&");

        Manager.yourDog.setSocial(100);

        Dog dog = new Dog(results[0], results[1], results[2], results[3], results[4]);

        Location loc = new Location("");
        loc.setLatitude(getIntent().getExtras().getDouble("LAT"));
        loc.setLongitude(getIntent().getExtras().getDouble("LONG"));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        FriendSession fs = new FriendSession(new Date(), sdf.format(new Date()), "0", dog,loc);

        DBObject.getInstanceOf().getDog().addDog(dog);
        DBObject.getInstanceOf().getSession().addSession(fs);

        scannerView.stopCamera();
        setContentView(R.layout.activity_social);

        init();

        update(results[0], results[1], results[3], results[4] );
    }
}
