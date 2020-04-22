package com.example.kenniyiyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView puanText,zamanText;
    int puan;
    ImageView imageView1,imageView2,imageview3,imageView4,imageView5;
    ImageView imageView6,imageView7,imageView8,imageView9;
    ImageView[] imageArray;
    Handler   handler;
    Runnable  runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        //initialize

        puanText   = (TextView)findViewById( R.id.puanText );
        zamanText  = (TextView)findViewById( R.id.zamanText );

        imageView1 = findViewById( R.id.imageView1 );
        imageView2 = findViewById( R.id.imageView2 );
        imageview3 = findViewById( R.id.imageView3 );
        imageView4 = findViewById( R.id.imageView4 );
        imageView5 = findViewById( R.id.imageView5 );
        imageView6 = findViewById( R.id.imageView6 );
        imageView7 = findViewById( R.id.imageView7 );
        imageView8 = findViewById( R.id.imageView8 );
        imageView9 = findViewById( R.id.imageView9 );

        imageArray = new ImageView[] {imageView1,imageView2,imageview3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        resimsakla();


        //Geri sayma
        new CountDownTimer( 10000, 1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                //herbir saymada yapılacak işlemler buraya  yazılacak.
                //Herbir geri sayışta zamanText'e sayılan değeri zaltarak gösterecektir.
                zamanText.setText("Zaman : "+ millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                // Sayma bittiğinde yapılacak işlemler buraya yazılacak.
                zamanText.setText( "Oyun Bitti." );
                handler.removeCallbacks( runnable );
                for(ImageView image : imageArray)
                {
                    image.setVisibility( View.INVISIBLE );
                }
                AlertDialog.Builder alert = new AlertDialog.Builder( MainActivity.this );
                alert.setTitle( "Tekrar Başla" );
                alert.setMessage( "Oyun Tekrardan Başlatmak İstediğinden Emin misin?" );
                alert.setPositiveButton( "Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = getIntent();
                        finish();
                        startActivity( intent );

                    }
                } );

                alert.setNegativeButton( "Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText( MainActivity.this,"Oyun Bitti!",Toast.LENGTH_SHORT ).show();

                    }
                } );
                alert.show();


            }
        }.start(); //CountDownTimer'ı başlat manasına gelir.
    }

    public void puanartır(View view) {
        puan++;
        //puan=puan+1;
        puanText.setText( " Puan : "+puan );
    }

    public void resimsakla()
    {
        handler  = new Handler(  );
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray)
                {
                    image.setVisibility( View.INVISIBLE );
                }

                //Rastgele sayı seçmek için Random fonksiyonu kullanılır.
                //int i = random.nextInt(9); fonksiyonu ile 0 ile 9 arasında rastgele bir sayı alır

                Random random = new Random( );
                int i = random.nextInt(9);
                imageArray[i].setVisibility( View.VISIBLE );

                handler.postDelayed( this,500 );

            }
        };

        handler.post( runnable );

    }
}
