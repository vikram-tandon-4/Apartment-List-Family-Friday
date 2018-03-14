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

import java.util.ArrayList;
import java.util.List;

import beans.NameBean;
import familyfriday.apartmentlist.com.familyfriday.R;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.MyViewHolder> {

    private ArrayList<List<String>> groups;

    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNames, tvGroupName;
        CardView container;

        public MyViewHolder(View view) {
            super(view);
            tvNames = (TextView) view.findViewById(R.id.tvNames);
            tvGroupName = (TextView) view.findViewById(R.id.tvGroupName);
            container = (CardView) view.findViewById(R.id.nameContainer);
        }

        public void clearAnimation() {
            container.clearAnimation();
        }
    }


    public GroupsAdapter( ArrayList<List<String>> groups, Context mContext) {
        this.groups = groups;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_list_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        StringBuilder sb = new StringBuilder();

        List<String> groupMembers= groups.get(position);

        for(String member: groupMembers){

            sb.append(member);
            sb.append(System.getProperty("line.separator"));
        }

        holder.tvNames.setText(sb.toString());
        holder.tvGroupName.setText(mContext.getString(R.string.group_number)+ (position+1));


        setAnimation(holder.container, position);
    }

    @Override
    public int getItemCount() {
        return groups.size();
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
}
