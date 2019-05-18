package com.creativeshare.emdad.Activites_Fragments.Activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.creativeshare.emdad.Activites_Fragments.Fragments.Fragment_About_App;
import com.creativeshare.emdad.Activites_Fragments.Fragments.Fragment_Profile;
import com.creativeshare.emdad.Activites_Fragments.Fragments.Fragment_First_shipping_Transportation;
import com.creativeshare.emdad.Activites_Fragments.Fragments.Fragment_Home;
import com.creativeshare.emdad.Activites_Fragments.Fragments.Fragment_Main_Shipping_Transportation;
import com.creativeshare.emdad.Activites_Fragments.Fragments.Fragment_Third_shipping_Transportation;
import com.creativeshare.emdad.Activites_Fragments.Fragments.Fragment_main;
import com.creativeshare.emdad.Activites_Fragments.Fragments.Fragment_more;
import com.creativeshare.emdad.Activites_Fragments.Fragments.Fragment_seconed_shipping_Transportation;
import com.creativeshare.emdad.Language.Language;
import com.creativeshare.emdad.R;

public class Home_Activity extends AppCompatActivity {
    private int fragment_count = 0;
    private FragmentManager fragmentManager;

    private Fragment_Home fragment_home;
    private Fragment_main fragment_main;
    private Fragment_Profile fragment_profile;
    private Fragment_more fragment_more;
    private Fragment_About_App fragment_about_app;
    private Fragment_Main_Shipping_Transportation fragment_main_shipping_transportation;
    private Fragment_First_shipping_Transportation fragment_first_shipping_transportation;
    private Fragment_seconed_shipping_Transportation fragment_seconed_shipping_transportation;
    private Fragment_Third_shipping_Transportation fragment_third_shipping_transportation;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase, Language.getLanguage(newBase)));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = this.getSupportFragmentManager();
        setContentView(R.layout.activity_home_);
        if(savedInstanceState==null){
            DisplayFragmentHome();
            DisplayFragmentMain();
        }

    }

    public void DisplayFragmentHome() {

        fragment_count += 1;

        if (fragment_home == null) {
            fragment_home = Fragment_Home.newInstance();
        }

        if (fragment_home.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_home).commit();
        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_home, "fragment_home").addToBackStack("fragment_home").commit();
        }

    }
    public void DisplayFragmentMain() {

       /* if (fragment_orders != null && fragment_orders.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_orders).commit();
        }

        if (fragment_offers != null && fragment_offers.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_offers).commit();
        }*/
        if (fragment_profile != null && fragment_profile.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_profile).commit();
        }
        if (fragment_more != null && fragment_more.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_more).commit();
        }

        if (fragment_main == null) {
            fragment_main = Fragment_main.newInstance();
        }

        if (fragment_main.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_main).commit();
        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_main_child, fragment_main, "fragment_main").addToBackStack("fragment_main").commit();

        }
        if (fragment_home != null && fragment_home.isAdded()) {
            fragment_home.UpdateAHBottomNavigationPosition(0);
        }

    }
    public void DisplayFragmentProfile() {

       /* if (fragment_orders != null && fragment_orders.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_orders).commit();
        }

        if (fragment_offers != null && fragment_offers.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_offers).commit();
        }*/
        if (fragment_more != null && fragment_more.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_more).commit();
        }
        if (fragment_main != null && fragment_main.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_main).commit();
        }

        if (fragment_profile == null) {
            fragment_profile = Fragment_Profile.newInstance();
        }

        if (fragment_profile.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_profile).commit();
        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_main_child, fragment_profile, "fragment_profile").addToBackStack("fragment_profile").commit();

        }
        if (fragment_home != null && fragment_home.isAdded()) {
            fragment_home.UpdateAHBottomNavigationPosition(2);
        }

    }
    public void DisplayFragmentMore() {

       /* if (fragment_orders != null && fragment_orders.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_orders).commit();
        }

        if (fragment_offers != null && fragment_offers.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_offers).commit();
        }*/
        if (fragment_profile != null && fragment_profile.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_profile).commit();
        }
        if (fragment_main != null && fragment_main.isAdded()) {
            fragmentManager.beginTransaction().hide(fragment_main).commit();
        }

        if (fragment_more == null) {
            fragment_more = Fragment_more.newInstance();
        }

        if (fragment_more.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_more).commit();
        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_main_child, fragment_more, "fragment_more").addToBackStack("fragment_more").commit();

        }
        if (fragment_home != null && fragment_home.isAdded()) {
             fragment_home.UpdateAHBottomNavigationPosition(3);
        }

    }
    public void DisplayFragmentAbout() {
        fragment_count+=1;

        fragment_about_app = Fragment_About_App.newInstance().newInstance();


        if (fragment_about_app.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_about_app).commit();
        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_about_app, "fragment_about_app").addToBackStack("fragment_about_app").commit();

        }
    }
    public void DisplayFragmentshipping_main() {
        fragment_count+=1;

            fragment_main_shipping_transportation = Fragment_Main_Shipping_Transportation.newInstance().newInstance();


        if (fragment_main_shipping_transportation.isAdded()) {
            fragmentManager.beginTransaction().show(fragment_main_shipping_transportation).commit();
        } else {
            fragmentManager.beginTransaction().add(R.id.fragment_app_container, fragment_main_shipping_transportation, "fragment_main_shipping_transportation").addToBackStack("fragment_main_shipping_transportation").commit();

        }

    }

    public void DisplayFragmentshipping_First() {
        fragment_count+=1;

            fragment_first_shipping_transportation=Fragment_First_shipping_Transportation.newInstance();

        if(fragment_first_shipping_transportation.isAdded()){
            fragmentManager.beginTransaction().show(fragment_first_shipping_transportation).commit();
        }else{
            fragmentManager.beginTransaction().add(R.id.fragment_shipping_transportation,fragment_first_shipping_transportation,"fragment_first_shipping_transportation").addToBackStack("fragment_first_shipping_transportation").commit();
        }


    }

    public void DisplayFragmentshipping_second() {
        fragment_count+=1;

        fragment_seconed_shipping_transportation=Fragment_seconed_shipping_Transportation.newInstance();

        if(fragment_seconed_shipping_transportation.isAdded()){
            fragmentManager.beginTransaction().show(fragment_seconed_shipping_transportation).commit();
        }else{
            fragmentManager.beginTransaction().add(R.id.fragment_shipping_transportation,fragment_seconed_shipping_transportation,"fragment_seconed_shipping_transportation").addToBackStack("fragment_seconed_shipping_transportation").commit();
        }
        if(fragment_main_shipping_transportation!=null){
            fragment_main_shipping_transportation.update_bar(20);
        }
    }
    public void DisplayFragmentshipping_Third() {
        fragment_count+=1;

        fragment_third_shipping_transportation=Fragment_Third_shipping_Transportation.newInstance();

        if(fragment_third_shipping_transportation.isAdded()){
            fragmentManager.beginTransaction().show(fragment_third_shipping_transportation).commit();
        }else{
            fragmentManager.beginTransaction().add(R.id.fragment_shipping_transportation,fragment_third_shipping_transportation,"fragment_third_shipping_transportation").addToBackStack("fragment_third_shipping_transportation").commit();
        }
        if(fragment_main_shipping_transportation!=null){
            fragment_main_shipping_transportation.update_bar(40);
        }
    }
    @Override
    public void onBackPressed() {
Back();    }
    public void Back() {
        if (fragment_count > 1) {
            if(fragment_main_shipping_transportation!=null&&fragment_main_shipping_transportation.isVisible()){
                if(fragment_count>3){
                    fragment_count-=1;
                    fragment_main_shipping_transportation.update_bar();
                    super.onBackPressed();
                }
                else{
                    fragment_count-=2;
                    super.onBackPressed();
                    super.onBackPressed();

                }
            }else{
            fragment_count -= 1;
            super.onBackPressed();}
        } else {

            if (fragment_home != null && fragment_home.isVisible()) {
                if (fragment_main != null && fragment_main.isVisible()) {
                   /* if (userModel == null) {
                        NavigateToSignInActivity();
                    } else {*/
                        finish();
                   // }
                } else {
                    DisplayFragmentMain();
                }
            } else {
                DisplayFragmentHome();
            }
        }

    }
    public void NavigateToSignInActivity() {
        Intent intent = new Intent(this, Login_Activity.class);
        startActivity(intent);
        finish();

    }



}