package com.example.ozedu;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SettlingInAustralia extends AppCompatActivity {

    WebView webView;
    public String fileName = "PermanentResidency.html";

    ExpandableListAd listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settling_in_australia);

        webView = (WebView) findViewById(R.id.PermanentResidency);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/" + fileName);

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        prepareListData();

        listAdapter = new ExpandableListAd(this, listDataHeader, listDataChild);

        expListView.setAdapter(listAdapter);

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("TEMPORARY VISA OPTIONS");
        listDataHeader.add("PERMANENT VISA OPTIONS");
        listDataHeader.add("PARTNER VISA OPTIONS");
        listDataHeader.add("FULL-TIME WORK VISA OPTIONS");
        listDataHeader.add("PART-TIME WORK VISA OPTIONS");

        List<String> temporaryVisaOptions = new ArrayList<String>();
        temporaryVisaOptions.add("A TEMPORARY GRADUATE VISA (SUBCLASS 485) VISA");
        temporaryVisaOptions.add("TEMPORARY WORK SKILLED (SUBCLASS 457) VISA");
        temporaryVisaOptions.add("SKILLED REGIONAL (PROVISIONAL) (SUBCLASS 189) VISA");


        List<String> permanentVisaOptions = new ArrayList<String>();
        permanentVisaOptions.add("SKILLED NOMINATED (SUBCLASS 190) VISA");
        permanentVisaOptions.add("SKILLED INDEPENDENT (SUBCLASS 189) VISA");
        permanentVisaOptions.add("EMPLOYER NOMINATED SCHEME VISA (ENS)");
        permanentVisaOptions.add("REGIONAL SPONSORED MIGRATION VISA (RSMS)");


        List<String> partnerVisaOptions = new ArrayList<String>();
        partnerVisaOptions.add("PARTNER VISA (subclass 820/801) Onshore visa");


        List<String> fullTimeVisaOptions = new ArrayList<String>();
        fullTimeVisaOptions.add("FULL-TIME SKILLED (SUBCLASS 488) VISA");
        fullTimeVisaOptions.add("FULL-TIME NON-SKILLED (SUBCLASS 444) VISA");
        fullTimeVisaOptions.add("FULL-TIME TRAINEE (SUBCLASS 100) VISA");


        List<String> partTimeVisaOptions = new ArrayList<String>();
        partTimeVisaOptions.add("PART-TIME SKILLED (SUBCLASS 488) VISA");
        partTimeVisaOptions.add("PART-TIME NON-SKILLED (SUBCLASS 444) VISA");
        partTimeVisaOptions.add("PART-TIME TRAINEE (SUBCLASS 100) VISA");

        listDataChild.put(listDataHeader.get(0), temporaryVisaOptions);
        listDataChild.put(listDataHeader.get(1), permanentVisaOptions);
        listDataChild.put(listDataHeader.get(2), partnerVisaOptions);
        listDataChild.put(listDataHeader.get(3), fullTimeVisaOptions);
        listDataChild.put(listDataHeader.get(4), partTimeVisaOptions);
    }
}
