package familyfriday.apartmentlist.com.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import familyfriday.apartmentlist.com.familyfriday.R;

/**
 * Created by vikra on 3/12/2018.
 */

public class Util {

    /*

     */
    public static Dialog addNameDialog(Context mContext)
    {
        Dialog newNameDialog = new Dialog(mContext, R.style.DialogSlideAnim);
        fillDialogToWindow(mContext, newNameDialog);
        newNameDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        newNameDialog.setContentView(R.layout.add_member_dialog);
        newNameDialog.setCancelable(true);
        return newNameDialog;
    }

    /**
     * Fill Dialog Window
     * @param context
     * @param dialog
     */
    public static void fillDialogToWindow(Context context, Dialog dialog)
    {
        if (dialog != null)
        {
            // Grab the window of the dialog, and change the width
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = dialog.getWindow();
            lp.copyFrom(window.getAttributes());
            // This makes the dialog take up the full width
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
    }

    /*
    Logic of forming groups with maximum number of
    groups with 4 members.
    Logic explanation in the Writeup.
     */
    public static ArrayList<List<String>> shuffleListMostGroupsOfFour(List<String> names) {

        Collections.shuffle(names);
        int size = names.size();

        System.out.println("\ntotal size "+ size);
        ArrayList<List<String>> groups = new ArrayList<List<String>>();

        for(int i=0;i<size;i +=4) {
            List<String> newGroup = new ArrayList<String>();
            for(int j=i;j<i+4;j++) {
                System.out.print(names.get(j)+", ");
                newGroup.add(names.get(j));
            }

            groups.add(newGroup);
            System.out.println();
            System.out.println();

            int elementsLeft = size - (i+4);

            System.out.println("\nprint elements left "+ elementsLeft);


            if(elementsLeft <= 7) {

                if(elementsLeft ==7 || elementsLeft == 6) {
                    newGroup = new ArrayList<String>();
                    for(int k=i+4;k<i+7;k++) {
                        System.out.print(names.get(k)+", ");
                        newGroup.add(names.get(k));
                    }
                    groups.add(newGroup);
                    System.out.println();
                    System.out.println();
                    newGroup = new ArrayList<String>();
                    for(int k=i+7;k<size;k++) {
                        System.out.print(names.get(k)+", ");
                        newGroup.add(names.get(k));
                    }
                    groups.add(newGroup);


                }else {
                    newGroup = new ArrayList<String>();
                    for(int k=i+4;k<size;k++) {
                        System.out.print(names.get(k)+", ");
                        newGroup.add(names.get(k));
                    }
                    groups.add(newGroup);
                }

                break;
            }
        }
        return groups;
    }

  /*
    Logic of forming groups with maximum number of
    groups with 5 members.
    Logic explanation in the Writeup.
     */

    public static ArrayList<List<String>> shuffleListMostGroupsOfFive(List<String> names) {

        Collections.shuffle(names);
        int size = names.size();

        System.out.println("\ntotal size "+ size);
        ArrayList<List<String>> groups = new ArrayList<List<String>>();

        for(int i=0;i<size;i +=5) {
            List<String> newGroup = new ArrayList<String>();
            for(int j=i;j<i+5;j++) {
                System.out.print(names.get(j)+", ");
                newGroup.add(names.get(j));
            }

            groups.add(newGroup);
            System.out.println();
            System.out.println();

            int elementsLeft = size - (i+5);

            System.out.println("\nprint elements left "+ elementsLeft);


            if(elementsLeft < 10) {

                if(elementsLeft==10){
                    newGroup = new ArrayList<String>();
                    for(int k=i+5;k<i+10;k++) {
                        System.out.print(names.get(k)+", ");
                        newGroup.add(names.get(k));
                    }

                    groups.add(newGroup);
                    newGroup = new ArrayList<String>();
                    for(int k=i+10;k<i+15;k++) {
                        System.out.print(names.get(k)+", ");
                        newGroup.add(names.get(k));
                    }

                    groups.add(newGroup);
                }
               else if(elementsLeft ==7 || elementsLeft == 6) {

                    newGroup = new ArrayList<String>();
                    for(int k=i+5;k<i+8;k++) {
                        System.out.print(names.get(k)+", ");
                        newGroup.add(names.get(k));
                    }

                    groups.add(newGroup);
                    System.out.println();
                    System.out.println();

                    newGroup = new ArrayList<String>();
                    for(int k=i+8;k<size;k++) {
                        System.out.print(names.get(k)+", ");
                        newGroup.add(names.get(k));
                    }

                    groups.add(newGroup);

                    // 8 or 9
                }else {
                    newGroup = new ArrayList<String>();
                    for(int k=i+5;k<i+10;k++) {
                        System.out.print(names.get(k)+", ");
                        newGroup.add(names.get(k));
                    }

                    groups.add(newGroup);
                    System.out.println();
                    System.out.println();
                    newGroup = new ArrayList<String>();
                    for(int k=i+10;k<size;k++) {
                        System.out.print(names.get(k)+", ");
                        newGroup.add(names.get(k));
                    }

                    groups.add(newGroup);
                }


                break;
            }
        }
        return groups;
    }

    /*
     Logic of forming groups with maximum number of
     groups with 3 members.
     Logic explanation in the Writeup.
      */
    public static ArrayList<List<String>> shuffleListMostGroupsOfThree(List<String> names) {

        Collections.shuffle(names);
        int size = names.size();

        System.out.println("\ntotal size "+ size);
        ArrayList<List<String>> groups = new ArrayList<List<String>>();

        for(int i=0;i<size;i +=3) {
            List<String> newGroup = new ArrayList<String>();
            for(int j=i;j<i+3;j++) {
                System.out.print(names.get(j)+", ");
                newGroup.add(names.get(j));
            }

            groups.add(newGroup);
            System.out.println();
            System.out.println();

            int elementsLeft = size - (i+3);

            System.out.println("\nprint elements left "+ elementsLeft);


            if(elementsLeft <= 8) {

                newGroup = new ArrayList<String>();
                for(int k=i+3;k<i+6;k++) {
                    System.out.print(names.get(k)+", ");
                    newGroup.add(names.get(k));
                }

                groups.add(newGroup);
                System.out.println();
                System.out.println();
                newGroup = new ArrayList<String>();
                for(int k=i+6;k<size;k++) {
                    System.out.print(names.get(k)+", ");
                    newGroup.add(names.get(k));
                }

                groups.add(newGroup);

                break;
            }
        }
        return groups;
    }
    /*
      Logic of forming groups with less than 10 members.
      Logic explanation in the Writeup.
       */
    public static ArrayList<List<String>> shuffleListForLessThanTen(List<String> names) {

        Collections.shuffle(names);
        int size = names.size();

        System.out.println("\ntotal size " + size);
        ArrayList<List<String>> groups = new ArrayList<List<String>>();
        List<String> newGroup = new ArrayList<String>();

        if(size==10){
            newGroup = new ArrayList<String>();
            for(int k=0;k<5;k++) {
                System.out.print(names.get(k)+", ");
                newGroup.add(names.get(k));
            }

            groups.add(newGroup);
            newGroup = new ArrayList<String>();
            for(int k=5;k<10;k++) {
                System.out.print(names.get(k)+", ");
                newGroup.add(names.get(k));
            }

            groups.add(newGroup);
        }
        else if(size ==7 || size == 6) {

            newGroup = new ArrayList<String>();
            for(int k=0;k<3;k++) {
                System.out.print(names.get(k)+", ");
                newGroup.add(names.get(k));
            }

            groups.add(newGroup);
            System.out.println();
            System.out.println();

            newGroup = new ArrayList<String>();
            for(int k=3;k<size;k++) {
                System.out.print(names.get(k)+", ");
                newGroup.add(names.get(k));
            }

            groups.add(newGroup);

            // 8 or 9
        }else if(size ==8 || size == 9){
            newGroup = new ArrayList<String>();
            for(int k=0;k<5;k++) {
                System.out.print(names.get(k)+", ");
                newGroup.add(names.get(k));
            }

            groups.add(newGroup);
            System.out.println();
            System.out.println();
            newGroup = new ArrayList<String>();
            for(int k=5;k<size;k++) {
                System.out.print(names.get(k)+", ");
                newGroup.add(names.get(k));
            }

            groups.add(newGroup);
        }else if(size ==3 || size == 4 || size == 5){
            groups.add(names);
        }

        return groups;
    }

}
