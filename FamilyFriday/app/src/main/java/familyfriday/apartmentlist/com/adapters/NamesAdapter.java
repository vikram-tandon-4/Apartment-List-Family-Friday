package familyfriday.apartmentlist.com.adapters;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import beans.NameBean;
import familyfriday.apartmentlist.com.familyfriday.R;
import familyfriday.apartmentlist.com.utils.AppPreferences;
import familyfriday.apartmentlist.com.utils.Util;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.MyViewHolder> {

    private List<NameBean> memberNames;

    private Context mContext;
    private FamilyDatabaseAdapter familyDatabaseAdapter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        CardView container;
        ImageView ivDelete;


        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            container = (CardView) view.findViewById(R.id.nameContainer);
            ivDelete = (ImageView) view.findViewById(R.id.ivDelete);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public NamesAdapter(List<NameBean> names, Context mContext) {
        this.memberNames = names;
        this.mContext = mContext;
        familyDatabaseAdapter=FamilyDatabaseAdapter.getNameListDBAdapterInstance(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.name_list_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvName.setText(memberNames.get(position).getName());

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlert(position,memberNames.get(position).getName());
            }
        });

        setAnimation(holder.container, position);
    }

    private void removePosition(int position) {
        memberNames.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, memberNames.size());
    }

    @Override
    public int getItemCount() {
        return memberNames.size();
    }


    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
    }
    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        (holder).clearAnimation();
    }

    private void showAlert(final int position, String name)
    {
        new AlertDialog.Builder(mContext)
                .setTitle(R.string.confirmation)
                .setMessage("Are you sure you want to remove "+name+" from the list?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        familyDatabaseAdapter.deleteName(memberNames.get(position).getNameId());
                        removePosition(position);
                        changeGroups();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                    }
                })
                .setIcon(R.drawable.ic_warning_black_24dp)
                .show();
    }

    private void changeGroups(){

        List<String>  namesOnly = familyDatabaseAdapter.getOnlyNames();

        if(namesOnly.size()>10){
            AppPreferences.setGroupsOfFive(mContext, Util.shuffleListMostGroupsOfFive(namesOnly));
            AppPreferences.setGroupsOfFour(mContext,Util.shuffleListMostGroupsOfFour(namesOnly));
            AppPreferences.setGroupsOfThree(mContext,Util.shuffleListMostGroupsOfThree(namesOnly));
        }else{
            AppPreferences.setGroupsOfLessThanEleven(mContext,Util.shuffleListForLessThanTen(namesOnly));
        }
    }
}
