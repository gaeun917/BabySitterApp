package com.example.gaeunlee.babysitter.sitter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gaeunlee.babysitter.AboutActivity;
import com.example.gaeunlee.babysitter.PaymentInsertActivity;
import com.example.gaeunlee.babysitter.R;
import com.example.gaeunlee.babysitter.dto.Sitter;

import java.util.List;


public class SitterBookActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private List<Sitter> sitterLists = SitterDataProvider.sitterList;
    public static final String NAME = "name";
    private static final int DETAIL_REQUEST = 1111;

    private static String webUrl = "https://www.facebook.com";
    private static String email = "info@hbabysitter.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sitter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


        // --------------- Drawer-------------------------------------------------------
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);







//       --------------- List View --------------------------------------------------------------

//      xml아이템 리소스
        //  String[] items = getResources().getStringArray(R.array.child);

        SitterListAdapter adapter = new SitterListAdapter(
                SitterBookActivity.this, R.layout.list_item_sitter, sitterLists);

        // activity_ㅡmain에 content_main (listview) include
        ListView lv = (ListView) findViewById(R.id.listView_sitter);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(SitterBookActivity.this, SitterBookDetailActivity.class);

                        Sitter sitter = sitterLists.get(position);
                        intent.putExtra(NAME, sitter.getName());

                        startActivityForResult(intent, DETAIL_REQUEST);
                    }
                });
    }








    //       --------------- Drawer---------------------------------------------------------
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }






    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // Send an email
        String[] addresses = {email};

        switch (id) {

            case R.id.nav_my_child:

                return true;


            case R.id.nav_booking_history:


                return true;


            case R.id.nav_update_profile:


                return true;


            case R.id.nav_payment:

                // payment info가 DB에 있으면, PaymentInfoActivity 보여주고
                // 없으면 PaymentInsertActivity

                Intent intent = new Intent(this, PaymentInsertActivity.class);
                startActivity(intent);

                return true;


            case R.id.nav_about:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);

                return true;

            case R.id.nav_share:
                /* Go to the website */
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl));
                if (webIntent.resolveActivity(getPackageManager()) != null)
                    startActivity(webIntent);
                return true;

            case R.id.nav_send:

                Intent intent3 = new Intent(Intent.ACTION_SENDTO);
                intent3.setData(Uri.parse("mailto:"));
                intent3.putExtra(Intent.EXTRA_EMAIL, addresses);
                intent3.putExtra(Intent.EXTRA_SUBJECT, "Information request");
                intent3.putExtra(Intent.EXTRA_TEXT, "Please send some information!");
                startActivity(intent3);
                return true;


            case R.id.nav_logout:


                return true;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//       --------------- Drawer-------------------------------------------------------------------

}