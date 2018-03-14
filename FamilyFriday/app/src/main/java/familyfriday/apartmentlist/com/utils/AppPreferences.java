package familyfriday.apartmentlist.com.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppPreferences {

    private static String GROUPS_OF_THREE = "groupsOfThree";
    private static String GROUPS_OF_FOUR = "groupsOfFour";
    private static String GROUPS_OF_FIVE = "groupsOfFive";
    private static String GROUPS_OF_LESS_THAN_ELEVEN = "groupsOfLessThanEleven";
    private static String FIRST_TIME = "firstTime";

    /*
    *
    * Saving and Retrieving Groups
    * */

    public static void setGroupsOfThree(Context context, ArrayList<List<String>> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        SharedPreferences _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putString(GROUPS_OF_THREE, json);
        editor.commit();
    }

    public static ArrayList<List<String>> getGroupsOfThree(Context context) {
        Gson gson = new Gson();
        ArrayList<List<String>> groupsOfThree;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonPreferences = sharedPref.getString(GROUPS_OF_THREE, "");

        Type type = new TypeToken<ArrayList<List<String>>>() {
        }.getType();
        groupsOfThree = gson.fromJson(jsonPreferences, type);

        return groupsOfThree;
    }

    public static void setGroupsOfFour(Context context, ArrayList<List<String>> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        SharedPreferences _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putString(GROUPS_OF_FOUR, json);
        editor.commit();
    }

    public static ArrayList<List<String>> getGroupsOfFour(Context context) {
        Gson gson = new Gson();
        ArrayList<List<String>> groupsOfFour;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonPreferences = sharedPref.getString(GROUPS_OF_FOUR, "");

        Type type = new TypeToken<ArrayList<List<String>>>() {
        }.getType();
        groupsOfFour = gson.fromJson(jsonPreferences, type);

        return groupsOfFour;
    }

    public static void setGroupsOfFive(Context context, ArrayList<List<String>>list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        SharedPreferences _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putString(GROUPS_OF_FIVE, json);
        editor.commit();
    }

    public static ArrayList<List<String>> getGroupsOfFive(Context context) {
        Gson gson = new Gson();
        ArrayList<List<String>> groupsOfFive;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonPreferences = sharedPref.getString(GROUPS_OF_FIVE, "");

        Type type = new TypeToken<ArrayList<List<String>>>() {
        }.getType();
        groupsOfFive = gson.fromJson(jsonPreferences, type);

        return groupsOfFive;
    }

/*

Saving and retrieving data if the employees are less than 11
 */
    public static void setGroupsOfLessThanEleven(Context context, ArrayList<List<String>>list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        SharedPreferences _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putString(GROUPS_OF_LESS_THAN_ELEVEN, json);
        editor.commit();
    }

    public static ArrayList<List<String>> getGroupsOfLessThanEleven(Context context) {
        Gson gson = new Gson();
        ArrayList<List<String>> groupsOfLessThanEleven;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonPreferences = sharedPref.getString(GROUPS_OF_LESS_THAN_ELEVEN, "");

        Type type = new TypeToken<ArrayList<List<String>>>() {
        }.getType();
        groupsOfLessThanEleven = gson.fromJson(jsonPreferences, type);

        return groupsOfLessThanEleven;
    }

    /*
*
* To save check if  the app is opened for the first time
*
*
* */
    public static boolean isFirstTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(FIRST_TIME, true);
    }

    public static void setFirstTime(Context context, boolean gender) {
        SharedPreferences _sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putBoolean(FIRST_TIME, gender);
        editor.commit();
    }
}
