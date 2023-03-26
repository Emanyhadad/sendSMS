package com.example.contentproviderass4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.contentproviderass4.databinding.ActivityMainBinding;


import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    ActivityMainBinding binding;
ArrayList<Contacts> contentArrayList;
    public static String ExtraNumber ="Number";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding=ActivityMainBinding.inflate( getLayoutInflater() );
        setContentView( binding.getRoot() );

        contentArrayList=new ArrayList <>(  );

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // if permission isn't granted take it in thins activity
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_CONTACTS},
                    PERMISSION_REQUEST_CODE);
        }else {
            ReadContacts();
        }

    }
    @Override
    public void onRequestPermissionsResult
            ( int requestCode , @NonNull String[] permissions , @NonNull int[]
                    grantResults ) {
        super.onRequestPermissionsResult( requestCode , permissions ,
                grantResults );

        if ( requestCode==PERMISSION_REQUEST_CODE&&
                grantResults.length>0 ){
            ReadContacts();
        }

    }

    @SuppressLint( "Range" )
    private void ReadContacts( ) {
        contentArrayList = new ArrayList <>(  );
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        @SuppressLint( "Recycle" ) Cursor cursor = getContentResolver().query( uri,null,
                null,null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");


        if ( cursor.moveToFirst() ){
            do {
                @SuppressLint( "Range" ) long id = cursor.getLong
                        ( cursor.getColumnIndex( "_ID" ) );
                Uri uri1 = ContactsContract.Data.CONTENT_URI;
                @SuppressLint( "Recycle" ) Cursor cursor1 = getContentResolver().query( uri1,null,
                        ContactsContract.Data.
                                CONTACT_ID+"=?",
                        new String[]{String.valueOf( id )},null);

                String displayName;
//                String nickName = "";
                String homePhone;
                String mobilePhone = "";
                String workPhone;
//                String photoPath = String.valueOf( R.drawable.ic_profile );
//                byte[] photoByte = null;
//                String homeEmail = "";
//                String workEmail = "";
//                String companyName = "";
//                String title = "";
                StringBuilder contactNumbers = new StringBuilder( );
//                String contactEmailAddresses = "";
//                String contactOtherDetails = "";

            if ( cursor1.moveToFirst() ){
                displayName=cursor1.getString( cursor1.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME ) );
                do {
//                    if ( cursor1.getString( cursor1.getColumnIndex( "mimetype" ) ).
//                            equals(ContactsContract.CommonDataKinds.Nickname.
//                                    CONTENT_ITEM_TYPE  ) ){
//                        nickName=cursor1.getString( cursor1.getColumnIndex( "data1" ) );
//                        contactOtherDetails += nickName+ "\n ";
//                    }
                    if ( cursor1.getString( cursor1.getColumnIndex( "mimetype" ) ).
                            equals(ContactsContract.CommonDataKinds.Phone.
                                    CONTENT_ITEM_TYPE  ) ){
                        switch ( cursor1.getInt( cursor1.getColumnIndex( "data2" ) ) ){
                            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                homePhone = cursor1.getString( cursor1.getColumnIndex( "data1" ) );
                                contactNumbers.append( "Home Phone : " ).append( homePhone ).append( "\n" );
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                workPhone = cursor1.getString( cursor1.getColumnIndex( "data1" ) );
                                contactNumbers.append( "Work Phone : " ).append( workPhone ).append( "\n" );
                                break;
                            case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                mobilePhone = cursor1.getString( cursor1.getColumnIndex( "data1" ) );
                                contactNumbers.append( "Mobile Phone : " ).append( mobilePhone ).append( "\n" );
                                break;
                        }
                    }
//                    if ( cursor1.getString( cursor1.getColumnIndex( "mimetype" ) ).
//                            equals(ContactsContract.CommonDataKinds.Email.
//                                    CONTENT_ITEM_TYPE  ) ){
//                        switch ( cursor1.getInt( cursor1.getColumnIndex( "data2" ) ) ){
//                            case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
//                                homeEmail = cursor1.getString( cursor1.getColumnIndex( "data1" ) );
//                                contactNumbers += "Home Email : "+homeEmail + "\n";
//                                break;
//                            case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
//                                workEmail = cursor1.getString( cursor1.getColumnIndex( "data1" ) );
//                                contactNumbers += "Work Email : "+workEmail + "\n";
//                                break;
//                        }
//                    }
//                    if ( cursor1.getString( cursor1.getColumnIndex( "mimetype" ) ).
//                            equals(ContactsContract.CommonDataKinds.Organization.
//                                    CONTENT_ITEM_TYPE  ) ){
//                        companyName = cursor1.getString( cursor1.getColumnIndex( "data1" ) );
//                        contactOtherDetails += "Company Name : "+companyName + "\n";
//                        title = cursor1.getString( cursor1.getColumnIndex( "data4" ) );
//                        contactOtherDetails += "Title : " + title + "\n";
//                    }
//                    if ( cursor1.getString( cursor1.getColumnIndex( "mimetype" ) ).
//                            equals( ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE ) ){
//                        photoByte = cursor1.getBlob( cursor1.getColumnIndex( "data15" ));
//
//                        if ( photoByte != null ){
//                            Bitmap bitmap = BitmapFactory.decodeByteArray(
//                                    photoByte,0,photoByte.length );
//                            File cacheDirectory = getBaseContext().getCacheDir();
//                            File tmp = new File( cacheDirectory.getPath() +
//                                    "/_androhub" + id + ".png" );
//                            try {
//                                FileOutputStream fileOutputStream = new FileOutputStream(
//                                        tmp );
//                                bitmap.compress( Bitmap.CompressFormat.
//                                        PNG,100,fileOutputStream );
//                                fileOutputStream.flush();
//                                fileOutputStream.close();
//
//                            }catch ( Exception e ){
//                                //TODO : handle exception
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }
//
               }while ( cursor1.moveToNext() );
                populateDataIntoRV( contentArrayList );
                contentArrayList.add( new Contacts( displayName,mobilePhone) );

            populateDataIntoRV( contentArrayList );

            }


            }
            while ( cursor.moveToNext() );
        }
    }

    private void populateDataIntoRV(ArrayList<Contacts> contacts){
        binding.RV.setAdapter( new Adapter
                ( contacts , pos -> {
                   // Contacts contacts1 = contentArrayList.get( pos );

                    Intent intent = getIntent();
                    intent.putExtra( ExtraNumber,
                            String.valueOf( contentArrayList.get( pos  ).getPhoto() ) );
                    setResult( RESULT_OK,intent );
                    finish();

                } ) );
        binding.RV.setLayoutManager( new LinearLayoutManager
                ( getApplicationContext(), RecyclerView.VERTICAL,false ) );
        binding.RV.setHasFixedSize( true );
       // int b =binding.RV.getId();


    }
    }