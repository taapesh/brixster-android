package com.example.taapesh.prototype;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;


public class StoreBrowseActivity extends ActionBarActivity {
    // Tab buttons
    private static TextView tabBackground;
    private static ImageButton storeButton;
    private static ImageButton barcodeButton;
    private static ImageButton cartButton;
    private static final int NUM_TABS = 3;
    private static final int TAB_DIVIDER_WIDTH = 1;
    private static final int TAB_BAR_HEIGHT = 64;

    // Screen and tab bar dimensions
    private static int screenWidth;
    private static int screenHeight;
    private static int tabWidth;
    private static int tabBarHeight;
    private static float screenDensity;

    // Tab host
    private static TabHost th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_browse_activity);
        screenDensity = getResources().getDisplayMetrics().density;

        // Get tab bar buttons
        storeButton = (ImageButton) findViewById(R.id.storeButton);
        barcodeButton = (ImageButton) findViewById(R.id.barcodeButton);
        cartButton = (ImageButton) findViewById(R.id.cartButton);
        tabBackground = (TextView) findViewById(R.id.tabBackground);



        getScreenDimensions();
        setUpControlBar();
        setUpTabs();

        // Set tab bar click events
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToStore = new Intent(
                        StoreBrowseActivity.this, StoreBrowseActivity.class);
                startActivity(goToStore);
            }
        });

        barcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToScanning = new Intent(
                        StoreBrowseActivity.this, StoreScanActivity.class);
                startActivity(goToScanning);
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToCart = new Intent(
                        StoreBrowseActivity.this, StoreCartActivity.class);
                startActivity(goToCart);
            }
        });
    }

    /**
     * Set tab bar height and tab widths
     */
    private void setUpControlBar() {
        tabBackground.getLayoutParams().height = tabBarHeight;
        storeButton.getLayoutParams().height = tabBarHeight;
        storeButton.getLayoutParams().width = tabWidth;
        cartButton.getLayoutParams().height = tabBarHeight;
        cartButton.getLayoutParams().width = tabWidth;
        barcodeButton.getLayoutParams().height = tabBarHeight;
        barcodeButton.getLayoutParams().width = tabWidth;
    }

    /**
     * Setup tab subviews
     */
    private void setUpTabs() {
        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();

        // Setup browse tab
        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Browse");
        th.addTab(specs);

        // Setup deals tab
        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Deals");
        th.addTab(specs);

        // Setup store navigation tab
        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Store Map");
        th.addTab(specs);
    }

    /**
     * Get all screen dimensions and setup tab bar dimensions
     */
    private void getScreenDimensions() {
        // Get screen dimensions
        WindowManager w = getWindowManager();
        Point size = new Point();
        w.getDefaultDisplay().getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Setup tab button widths, subtract value to set divider length
        tabWidth = (screenWidth / NUM_TABS) - dpToPx(TAB_DIVIDER_WIDTH);
        tabBarHeight = dpToPx(TAB_BAR_HEIGHT);
    }

    /**
     * Convert dp to pixels
     */
    private int dpToPx(int dp) {
        return Math.round((float)dp * screenDensity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_store_browse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        overridePendingTransition(0,0);
        super.onResume();
    }
}
