package tanvir.testrecyclerviewcontextualaction;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by USER on 01-Feb-17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    MainActivity mainActivity;
    ArrayList<String> countryNames;
    Context context;

    public RecyclerAdapter(Context context, ArrayList<String> countryNames)
    {
        this.context=context;
        this.countryNames = countryNames;
        mainActivity = (MainActivity) context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_to_inflate_in_recyclerview,parent,false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view,context,countryNames,mainActivity);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.textView1.setText(countryNames.get(position));

        if (!mainActivity.is_in_action_mode)
        {
            holder.checkBox.setVisibility(View.GONE);
        }
        else
        {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.checkBox.setChecked(false);

        }



    }

    @Override
    public int getItemCount() {
        return countryNames.size();
    }


    public class  RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

    {
        TextView textView1;

        ArrayList<String> countryNames;
        MainActivity mainActivity;
        CheckBox checkBox;

        Context context;

        CardView cardView;

        public RecyclerViewHolder(View view, final Context context, final ArrayList<String> countryNames,MainActivity mainActivity)
        {
            super(view);
            this.countryNames = countryNames;

            this.context=context;
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            cardView = (CardView) view.findViewById(R.id.cardView);

            ///cardView.setOnLongClickListener(mainActivity);

            textView1 = (TextView) view.findViewById(R.id.noteTitle);
            this.mainActivity = mainActivity;

            checkBox.setOnClickListener(this);
           textView1.setOnClickListener(this);
            textView1.setOnLongClickListener(mainActivity);


        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();

            if (mainActivity.is_in_action_mode)
            {

                ///mainActivity.prepareSelection(view,getAdapterPosition());

                ///Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();

                ColorDrawable drawable = (ColorDrawable)textView1.getBackground();
                int colorCode = drawable.getColor();

                if (colorCode==-1)
                {
                    cardView.setBackgroundColor(Color.RED);
                    textView1.setBackgroundColor(Color.RED);
                }
                else
                {
                    textView1.setBackgroundColor(Color.WHITE);
                    cardView.setBackgroundColor(Color.WHITE);
                }


                ///Toast.makeText(context,Integer.toString(colorCode), Toast.LENGTH_SHORT).show();

               /// Toast.makeText(context, drawable.toString(), Toast.LENGTH_SHORT).show();

                ///textView1.setBackgroundColor(Color.RED);


                ///ColorDrawable cd = (ColorDrawable) textView1.getBackground();
                ///int colorCode = cd.getColor();

                ///

                ///textView1.setBackgroundColor(Color.BLUE);
            }
            else
            {
                Toast.makeText(context, Integer.toString(position), Toast.LENGTH_SHORT).show();
            }


        }

    }

    public void updateAdapter(ArrayList<String> list)
    {
        for (int i=0;i<list.size();i++)
        {
            countryNames.remove(i);
        }
        notifyDataSetChanged();
    }


}
