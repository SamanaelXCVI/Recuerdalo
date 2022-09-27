package app.recuerdalo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private final ArrayList<DataModel> dataSet;
    public static class MyViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {

        TextView textViewName; TextView textViewVersion; ImageView imageViewIcon;

        public MyViewHolder(View itemView) { super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewVersion = itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = itemView.findViewById(R.id.imageView);
        }
    }

    public CustomAdapter(ArrayList<DataModel> data) { this.dataSet = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        view.setOnClickListener(Recycler.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view); return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewVersion;
        ImageView imageView = holder.imageViewIcon;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewVersion.setText(dataSet.get(listPosition).getHint());
        imageView.setImageResource(dataSet.get(listPosition).getImage());
    }

    @Override
    public int getItemCount() { return dataSet.size();
    }
}


