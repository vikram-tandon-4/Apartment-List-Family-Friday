package familyfriday.apartmentlist.com.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import familyfriday.apartmentlist.com.adapters.FamilyDatabaseAdapter;
import familyfriday.apartmentlist.com.adapters.GroupsAdapter;
import familyfriday.apartmentlist.com.adapters.SimpleSpinnerAdaptor;
import familyfriday.apartmentlist.com.familyfriday.R;
import familyfriday.apartmentlist.com.utils.AppPreferences;
import familyfriday.apartmentlist.com.utils.Util;

/**
 * Created by vikra on 3/11/2018.
 */

public class GroupActivity extends AppCompatActivity {

    private Context mContext;
    private Spinner spinnerGroups;
    private ArrayAdapter<String> simpleSpinnerAdaptor;
    private GroupsAdapter mAdapter;
    private RecyclerView rvGroups;
    private FamilyDatabaseAdapter familyDatabaseAdapter;
    private List<String> names;
    private TextView tvShuffle;
    private Toolbar toolbar;
    private TextView tvTbTitle;
    private static int currentPosition=0;
    private static int listSize=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        mContext= this;

        init();
    }
/*

initialising widgets
getting instance of database
 */
    private void init() {

        familyDatabaseAdapter=FamilyDatabaseAdapter.getNameListDBAdapterInstance(mContext);

        names=familyDatabaseAdapter.getOnlyNames();
        listSize= names.size();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTbTitle = (TextView) findViewById(R.id.tvTbTitle);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvTbTitle.setText(R.string.groups);

        rvGroups = (RecyclerView)findViewById(R.id.rvGroups);
        tvShuffle = (TextView) findViewById(R.id.tvShuffle);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvGroups.setLayoutManager(mLayoutManager);
        rvGroups.setItemAnimator(new DefaultItemAnimator());

        spinnerGroups = (Spinner) findViewById(R.id.spinnerGroup);

        if(listSize<=10){
            tvShuffle.setVisibility(View.GONE);
            spinnerGroups.setVisibility(View.GONE);

            if(AppPreferences.getGroupsOfLessThanEleven(mContext)!=null && AppPreferences.getGroupsOfLessThanEleven(mContext).size()==0){
                AppPreferences.setGroupsOfLessThanEleven(mContext,Util.shuffleListForLessThanTen(names));
            }
            mAdapter = new GroupsAdapter(AppPreferences.getGroupsOfLessThanEleven(mContext), mContext);
            rvGroups.setAdapter(mAdapter);
        }else{
             ArrayList<List<String>> nameEmptyList = new ArrayList<>();
            AppPreferences.setGroupsOfLessThanEleven(mContext,nameEmptyList);
        }

        setGroupCategory();

        tvShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition == 0){
                    shuffleGroupOfThree();
                }else  if(currentPosition == 1){
                    shuffleGroupOfFour();
                }else{
                    shuffleGroupOfFive();
                }
            }
        });


        spinnerGroups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentPosition=position;
                if (position == 0) {
                    if(listSize>10){
                        tvShuffle.setText(R.string.shuffle_three);
                        ArrayList<List<String>> groupList = AppPreferences.getGroupsOfThree(mContext);
                        mAdapter = new GroupsAdapter(groupList, mContext);
                        rvGroups.setAdapter(mAdapter);
                    }

                }else if(position ==1){
                    if(listSize>10) {
                        tvShuffle.setText(R.string.shuffle_four);
                        ArrayList<List<String>> groupList = AppPreferences.getGroupsOfFour(mContext);
                        mAdapter = new GroupsAdapter(groupList, mContext);
                        rvGroups.setAdapter(mAdapter);
                    }
                }else if(position ==2){
                    if(listSize>10) {
                        tvShuffle.setText(R.string.shuffle_five);
                        ArrayList<List<String>> groupList = AppPreferences.getGroupsOfFive(mContext);
                        mAdapter = new GroupsAdapter(groupList, mContext);
                        rvGroups.setAdapter(mAdapter);
                    }
                }
        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
/*

Adding categories to dropdown
 */
    private void setGroupCategory(){

        ArrayList<String> groupCategory=new ArrayList<>();

        groupCategory.add(getString(R.string.group_size_3));
        groupCategory.add(getString(R.string.group_size_4));
        groupCategory.add(getString(R.string.group_size_5));


        spinnerGroups.setSelection(0);
        simpleSpinnerAdaptor = new SimpleSpinnerAdaptor(mContext, android.R.layout.simple_spinner_item, groupCategory);
        simpleSpinnerAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroups.setAdapter(simpleSpinnerAdaptor);

    }
/*

Shuffling group of three, four and five
To get new combinations of the group
if the employees are more than 10
 */
    private void shuffleGroupOfFive(){
        names=familyDatabaseAdapter.getOnlyNames();
        Toast.makeText(mContext,"Max 5 members="+names.size(),Toast.LENGTH_LONG).show();
        if(names.size()>10) {
            ArrayList<List<String>> groupList = Util.shuffleListMostGroupsOfFive(names);
            mAdapter = new GroupsAdapter(Util.shuffleListMostGroupsOfFive(names), mContext);
            rvGroups.setAdapter(mAdapter);
            AppPreferences.setGroupsOfFive(mContext,groupList);
        }
    }

    private void shuffleGroupOfFour(){
        names=familyDatabaseAdapter.getOnlyNames();
        Toast.makeText(mContext,"Max 4 members="+names.size(),Toast.LENGTH_LONG).show();

        if(names.size()>10) {
            ArrayList<List<String>> groupList = Util.shuffleListMostGroupsOfFour(names);
            mAdapter = new GroupsAdapter(groupList, mContext);
            rvGroups.setAdapter(mAdapter);
            AppPreferences.setGroupsOfFour(mContext,groupList);
        }
    }

    private void shuffleGroupOfThree(){
        names=familyDatabaseAdapter.getOnlyNames();
        Toast.makeText(mContext,"Max 3 members="+names.size(),Toast.LENGTH_LONG).show();


        if(names.size()>10) {
            ArrayList<List<String>> groupList = Util.shuffleListMostGroupsOfThree(names);
            mAdapter = new GroupsAdapter(groupList, mContext);
            rvGroups.setAdapter(mAdapter);
            AppPreferences.setGroupsOfThree(mContext,groupList);
        }
    }
}
