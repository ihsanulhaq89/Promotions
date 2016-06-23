package com.af.promotions;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;

import com.af.promotions.activities.DetailActivity;
import com.af.promotions.activities.MainActivity;

public class TestCase extends InstrumentationTestCase {
    Instrumentation instrumentation;
    Activity currentActivity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("setUp");
    }

    @Override
    protected void tearDown() throws Exception {
        //super.tearDown();
        System.out.println("tearDown");
    }

    @MediumTest
    public void test1(){
        instrumentation = getInstrumentation();

        // Register we are interested in the main activity
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(MainActivity.class.getName(), null, false);

        // Start the main activity as the first activity
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(instrumentation.getTargetContext(), MainActivity.class.getName());
        instrumentation.startActivitySync(intent);

        // Wait for it to start...
        currentActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);

        RecyclerView recyclerView = (RecyclerView) currentActivity.findViewById(R.id.recycler_view);
        final View card1 = recyclerView.getChildAt(0);
        final View card2 = recyclerView.getChildAt(1);

        instrumentation.waitForIdleSync();
        delay();

        // Register we are interested in the second activity...
        // this has to be done before we do something that will send us to that
        // activity...
        instrumentation.removeMonitor(monitor);
        monitor = instrumentation.addMonitor(DetailActivity.class.getName(), null, false);

        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                card1.performClick();
            }
        });

        // Wait for the second activity to start...
        currentActivity = instrumentation.waitForMonitorWithTimeout(monitor, 3000);
        instrumentation.waitForIdleSync();
        delay();

        instrumentation.removeMonitor(monitor);
        monitor = instrumentation.addMonitor(MainActivity.class.getName(), null, false);

        currentActivity.finish();
        currentActivity = instrumentation.waitForMonitorWithTimeout(monitor, 3000);
        instrumentation.waitForIdleSync();
        delay();
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                card2.performClick();
            }
        });

        instrumentation.waitForIdleSync();
        delay();
    }

    private void delay(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
