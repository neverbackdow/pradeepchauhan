package com.example.pradeep.picslider;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pradeep.picslider.dummy.DummyContent;

import static android.R.attr.name;
import static android.R.attr.password;
import static android.R.attr.y;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener {


    private int PICK_IMAGE_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_main, new ItemFragment());
        ft.commit();
    }


    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    public void onThumbNailClicked() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_main, new BlankFragment());
        ft.addToBackStack(null);//use to go back to previous fragment window(home) when pressed back button
        ft.commit();
    }

    @Override
    public void blankFragmentInteraction(Uri uri) {

    }


    @Override
    public void selectFromGallery(int position) {
        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
// Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), position);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            DummyContent.DummyItem dummyItem = DummyContent.ITEMS.get(0);
            dummyItem.content = uri.toString();



        }
    }



}
