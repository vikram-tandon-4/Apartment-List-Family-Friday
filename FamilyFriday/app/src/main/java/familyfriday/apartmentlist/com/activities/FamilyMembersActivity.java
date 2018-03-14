package familyfriday.apartmentlist.com.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import beans.NameBean;
import familyfriday.apartmentlist.com.adapters.FamilyDatabaseAdapter;
import familyfriday.apartmentlist.com.adapters.NamesAdapter;
import familyfriday.apartmentlist.com.familyfriday.R;
import familyfriday.apartmentlist.com.utils.AppPreferences;
import familyfriday.apartmentlist.com.utils.Util;

public class FamilyMembersActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FamilyDatabaseAdapter familyDatabaseAdapter;
    private List<NameBean> names;
    private RecyclerView rvNames;
    private NamesAdapter mAdapter;
    private Context mContext;
    private Dialog addMember;

    /*

initial static list to be populated
 */
    String[] existingNames = {"Noah","Liam","Mason","Jacob","William","Ethan","James","Alexander","Michael"
            ,"Benjamin","Elijah","Daniel","Aiden","Logan","Matthew","Lucas","Jackson","David","Oliver"
            ,"Jayden","Joseph","Gabriel","Samuel","Carter","Anthony","John","Dylan"	,"Luke","Henry"
            ,"Andrew","Isaac","Christopher","Joshua","Wyatt","Sebastian","Owen","Caleb","Nathan"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);

        mContext=this;

        init();
    }
/*

initialising all the widgets of the screen
initialising the database
 */
    private void init() {

        familyDatabaseAdapter=FamilyDatabaseAdapter.getNameListDBAdapterInstance(mContext);

        if(AppPreferences.isFirstTime(mContext)){
            for(String name: existingNames){
                familyDatabaseAdapter.insertName(name);
            }
            AppPreferences.setFirstTime(mContext,false);
            shuffle();
        }


              /*
        *
        * initializing toolbar
        *
        * */
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.inflateMenu(R.menu.home);
        toolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.addMember:
                        addMemberDialog();

                        return true;
                    case R.id.group:
                        List<String>  namesOnly = familyDatabaseAdapter.getOnlyNames();
                        if(namesOnly.size()>2) {
                            Intent intent = new Intent(FamilyMembersActivity.this, GroupActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(mContext, R.string.minimum_members_message,Toast.LENGTH_LONG).show();
                        }
                        return true;
                }
                return false;
            }
        });

        rvNames = (RecyclerView)findViewById(R.id.rvNames);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvNames.setLayoutManager(mLayoutManager);
        rvNames.setItemAnimator(new DefaultItemAnimator());

        setList(false);
    }

/*

Dialog to add new members
Validation of name: more than 2 characters
Name added to the database and the list
 */
    private void addMemberDialog()
    {
        addMember = Util.addNameDialog(mContext);
        addMember.show();

        final TextView tvSubmit = (TextView) addMember.findViewById(R.id.tvSubmit);
        final EditText etName=(EditText) addMember.findViewById(R.id.etName);

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etName.getText().toString().trim())) {
                    Toast.makeText(mContext, getResources().getString(R.string.enter_name), Toast.LENGTH_SHORT).show();
                } else if ((etName.getText().toString().trim().length()< 3)) {
                    Toast.makeText(mContext, getResources().getString(R.string.enter_valid_name), Toast.LENGTH_SHORT).show();
                } else {
                    if(familyDatabaseAdapter.insertName(etName.getText().toString())){
                        Toast.makeText(mContext, R.string.member_added,Toast.LENGTH_LONG).show();
                        setList(true);
                    }else{
                        Toast.makeText(mContext, R.string.error_insertion,Toast.LENGTH_LONG).show();
                    }
                    addMember.dismiss();
                }
            }
        });
    }
/*

Setting new list when data changes

if insertion boolean is true

groups will be shuffled again
 */
    private void setList(boolean insertion){
        names=familyDatabaseAdapter.getAllNames();
        if(names.size()>0) {
            mAdapter = new NamesAdapter(names, mContext);
            rvNames.setAdapter(mAdapter);
        }



        if(insertion){
            shuffle();
        }

    }
/*
Changing and saving new groups in Preferences.

 */
    private void shuffle(){
        List<String>  namesOnly = familyDatabaseAdapter.getOnlyNames();
        if(namesOnly.size()>10){
            AppPreferences.setGroupsOfFive(mContext,Util.shuffleListMostGroupsOfFive(namesOnly));
            AppPreferences.setGroupsOfFour(mContext,Util.shuffleListMostGroupsOfFour(namesOnly));
            AppPreferences.setGroupsOfThree(mContext,Util.shuffleListMostGroupsOfThree(namesOnly));
        }else{
            AppPreferences.setGroupsOfLessThanEleven(mContext,Util.shuffleListForLessThanTen(namesOnly));
        }

    }
}
