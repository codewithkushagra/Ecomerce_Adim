package ViewHolder;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agarwal_co.R;

import interfacep.ItemClickListner;

public class CompanyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView companyname;
    public ItemClickListner listner;
    public CompanyViewHolder(@NonNull View itemView)
    {
        super(itemView);
        companyname=(TextView) itemView.findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v)
    {
    listner.onClick(v, getAdapterPosition(), false);
    }
    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner=listner;
    }
}
