package com.project.podstreleny.pavol.baking.ui.recipeStepDetail;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.project.podstreleny.pavol.baking.R;
import com.project.podstreleny.pavol.baking.db.entities.RecipeStep;
import com.project.podstreleny.pavol.baking.utils.BundleHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepDetailActivity extends AppCompatActivity {


    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            Bundle bundle = intent.getBundleExtra(Intent.EXTRA_TEXT);

            final int position = bundle.getInt(BundleHelper.ACTUAL_POSITION);
            final ArrayList<RecipeStep> list = bundle.getParcelableArrayList(BundleHelper.LIST_OF_STEPS);


            PagerAdapter pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), list);
            mViewPager.setAdapter(pagerAdapter);
            mViewPager.setCurrentItem(position);

            mTabLayout = findViewById(R.id.tab_layout);

            //Check if there is TAB LAYOUT -> LANDSCAPE MODE NO TAB LAYOUT
            if (mTabLayout != null) {
                mTabLayout.setupWithViewPager(mViewPager);

            } else {
                final ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.hide();
                }
                View decorView = getWindow().getDecorView();
                // Hide the status bar.
                int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
                decorView.setSystemUiVisibility(uiOptions);
            }
        }


    }

}
