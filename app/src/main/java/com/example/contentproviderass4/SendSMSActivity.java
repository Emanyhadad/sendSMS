package com.example.contentproviderass4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.contentproviderass4.databinding.ActivitySendSmsBinding;

public class SendSMSActivity extends AppCompatActivity {
    ActivitySendSmsBinding binding;
    private final int requestCodePermissions = 100;
    private final int requestCodeActivityResult=200;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding=ActivitySendSmsBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );

//        if ( ContextCompat.checkSelfPermission(this,
//                Manifest.permission.SEND_SMS)
//                != PackageManager.PERMISSION_GRANTED) {
//            // if permission isn't granted take it in thins activity
//            sendTheMessage( binding.tvNumber.getText().toString(),binding.tvNumber.getText().toString() );
//        }else {
//            ActivityCompat.requestPermissions(this,
//                new String[]{
//                        Manifest.permission.SEND_SMS},
//                100);
//        }

        binding.tvNumber.setOnClickListener( view -> {

            Intent intent = new Intent( SendSMSActivity.this,
                    ContactsActivity.class );
         //   startActivity( intent );
            startActivityForResult( intent,requestCodeActivityResult );

        } );

        binding.btnSend.setOnClickListener( view ->
               getPermissions() );

    }

    @Override
    protected void onActivityResult( int requestCode , int resultCode , @Nullable Intent data ) {
        super.onActivityResult( requestCode , resultCode , data );
        if(requestCode == requestCodeActivityResult && resultCode == RESULT_OK){
            assert data != null;
            binding.tvNumber.setText( data.getStringExtra( ContactsActivity.ExtraNumber ) );
        }

    }

    public void sendTheMessage() {
        String phoneNumber= binding.tvNumber.getText().toString();
        String message= binding.edText.getText().toString();

        if (!phoneNumber.isEmpty() && !message.isEmpty()){
            SmsManager
                    .getDefault()
                    .sendTextMessage(phoneNumber, null,
                            message,
                            null, null);
            Toast.makeText( this , "Sent!" , Toast.LENGTH_SHORT ).show( );
        }

    }


    @Override
    public void onRequestPermissionsResult( int requestCode , @NonNull String[] permissions , @NonNull int[] grantResults ) {
        super.onRequestPermissionsResult( requestCode , permissions , grantResults );
        if (requestCode == requestCodePermissions && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendTheMessage( );
        }else {
            Toast.makeText( this , "Permission Denied!" , Toast.LENGTH_SHORT ).show( );
        }
    }
    void  getPermissions(){
        if ( ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            // if permission isn't granted take it in thins activity
            sendTheMessage( );
        }else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.SEND_SMS},
                    requestCodePermissions);
        }
    }
}