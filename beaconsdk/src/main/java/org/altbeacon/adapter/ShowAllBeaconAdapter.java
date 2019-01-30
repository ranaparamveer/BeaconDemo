package org.altbeacon.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import org.altbeacon.beaconreference.R;
import org.altbeacon.beaconreference.databinding.AdapterShowAllBeaconBinding;

import java.util.ArrayList;

/**
 * Created by KaurSarabjot on 1/24/2019.
 */

public class ShowAllBeaconAdapter extends RecyclerView.Adapter<ShowAllBeaconAdapter.ShowAllBeaconViewHolder> {

    private Context mContext;
    private ArrayList<String> mShowAllBeaconList;
    private LayoutInflater layoutInflater;

    public ShowAllBeaconAdapter(Context mContext, ArrayList<String> mShowAllBeaconList) {
        this.mContext = mContext;
        this.mShowAllBeaconList = mShowAllBeaconList;
    }

    @NonNull
    @Override
    public ShowAllBeaconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        AdapterShowAllBeaconBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_show_all_beacon, viewGroup, false);
        return new ShowAllBeaconViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowAllBeaconAdapter.ShowAllBeaconViewHolder showAllBeaconViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ShowAllBeaconViewHolder extends RecyclerView.ViewHolder {

        public ShowAllBeaconViewHolder(final AdapterShowAllBeaconBinding itemBinding) {
            super(itemBinding.getRoot());
        }
    }
}
