package com.mgm_cen_57.coder.mgmcseuploads;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class BooksAndSyllabus extends AppCompatActivity {

    StorageReference mStorageRef;

    Uri mUri;
    FirebaseDatabase mFirebaseDatabase;//used for storing file links
    FirebaseStorage mFirebaseStorage;//for storing uploaded files
    ProgressDialog pd;
    TextView filestatus;
    String extension;
    Button btnselectfile,btnuploadfile,buttonfetch;
    String filePath="",fullname="",onlyname="";
    File f;
    String root,sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_and_syllabus);


        root=getIntent().getStringExtra("Root");
        sub=getIntent().getStringExtra("sub");




        btnselectfile=findViewById(R.id.buttonselectfile);
        btnuploadfile=findViewById(R.id.buttonUpload);

        filestatus=findViewById(R.id.textViewFileselectionstatus);

        pd=new ProgressDialog(BooksAndSyllabus.this);

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mFirebaseStorage=FirebaseStorage.getInstance();



        btnselectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(BooksAndSyllabus.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    selectPDF();
                }
                else
                {
                    ActivityCompat.requestPermissions(BooksAndSyllabus.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},9);

                }
            }
        });

        btnuploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUri!=null)
                    uploadfile(mUri);
                else
                    Toast.makeText(BooksAndSyllabus.this, "Please select file", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void uploadfile(Uri uri) {

        pd.setTitle("Uploading file");
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCancelable(false);
        pd.setProgress(0);
        pd.show();

        mStorageRef = FirebaseStorage.getInstance().getReference();
        //  mStorageRef.child("TE_CSE").child("BOOKS").child(fullname).putFile(uri).putFile(extension).

        mStorageRef.child(root).child(sub).child(fullname).putFile(uri).
                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String downloadUrl = taskSnapshot.getDownloadUrl().toString();
                        DatabaseReference rf=mFirebaseDatabase.getReference();

                        //   rf.child("TE_CSE").child("BOOKS").child(onlyname).setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() {

                        uploadData ud=new uploadData(downloadUrl,extension);
                        rf.child(root).child(sub).child(onlyname).setValue(ud).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(BooksAndSyllabus.this, "file uploaded", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }


                                else
                                {
                                    Toast.makeText(BooksAndSyllabus.this, "file not uploaded", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BooksAndSyllabus.this,e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("error",e.toString());

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        int currentprogress=(int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        pd.setProgress(currentprogress);
                        Log.d("error",taskSnapshot.toString());

                    }
                });

    }

    private void selectPDF() {

        Intent i=new Intent();
        i.setType("application/pdf");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,86);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==9&&grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            selectPDF();
        }
        else
        {
            Toast.makeText(this, "Please Grant the permission", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==86&&resultCode==RESULT_OK&&data!=null){

            mUri=data.getData();


//
//            File myfile=new File(data.getData().getPath());
//
//            path=myfile.getName();
//            //it contain your path of image..im using a temp string..
//            filename=path.substring(path.lastIndexOf("/")+1);
//
//


//            try{
//                //call the getPath uri with context and uri
//                //To get path from uri
//                 path = getPath1(MainActivity.this, mUri);
//                File file = new File(path);
//                 filename = file.getName();
//                Log.d( "Err","File Name: " + filename);
//            }catch(Exception e){
//                Log.d("Err", e.toString()+"");
//            }
//
//            Log.d("file",filename);

            try {
                filePath = PathUtil.getPath(getApplicationContext(), mUri);

                f=new File(filePath);
                fullname=f.getName();
                extension= fullname.substring(fullname.lastIndexOf("."));

                Log.d("extension",extension);

                onlyname=removeExtension(fullname);
                filestatus.setText(fullname);

            }
            catch (Exception e){}

        }
        else
        {
            Toast.makeText(this, "Please select file", Toast.LENGTH_SHORT).show();
        }
    }
    public static String removeExtension(String str)
    {
        // Handle null case specially.

        if (str == null) {
            return null;}

        // Get position of last '.'.

        int pos = str.lastIndexOf(".");

        // If there wasn't any '.' just return the string as is.

        if (pos == -1) return str;

        // Otherwise return the string, up to the dot.

        return str.substring(0, pos);
    }
}
