package org.altbeacon.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import org.altbeacon.adapter.ShowAllBeaconAdapter;
import org.altbeacon.beaconreference.R;
import org.altbeacon.beaconreference.databinding.ActivityShowAllBeaconBinding;
import java.util.ArrayList;

public class ShowAllBeaconActivity extends Activity {

    private ActivityShowAllBeaconBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_show_all_beacon);
        setRecyclerView();
    }

    private void setRecyclerView(){
        ArrayList<String> mShowAllBeaconList = new ArrayList<>();
        //RecyclerView rvAllBeacon = findViewById(R.id.rvAllBeacon);
        ShowAllBeaconAdapter showAllBeaconAdapter = new ShowAllBeaconAdapter(this, mShowAllBeaconList);
        mBinding.rvAllBeacon.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvAllBeacon.setAdapter(showAllBeaconAdapter);
        mBinding.rvAllBeacon.addItemDecoration(new DividerItemDecoration(mBinding.rvAllBeacon.getContext(), DividerItemDecoration.VERTICAL));
        showAllBeaconAdapter.notifyDataSetChanged();
    }
}
