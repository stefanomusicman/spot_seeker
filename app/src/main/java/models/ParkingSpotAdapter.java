package models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.spot_seeker.DetailedSpot;
import com.example.spot_seeker.R;

import java.util.ArrayList;

public class ParkingSpotAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<Listing> userListings;

    Listing listing;

    public ParkingSpotAdapter(Context context, ArrayList<Listing> userListings) {
        this.context = context;
        this.userListings = userListings;
    }

    @Override
    public int getCount() {
        return userListings.size();
    }

    @Override
    public Object getItem(int position) {
        return userListings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listingCard;
        TextView tvAddress;

        LayoutInflater inflater = LayoutInflater.from(context);
        listingCard = inflater.inflate(R.layout.listing_card, parent, false);

        // Access to each item in the card
        tvAddress = listingCard.findViewById(R.id.tvAddress);
        listingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedSpot.class);
                intent.putExtra("listing", userListings.get(position));
                context.startActivity(intent);
            }
        });

        // Populate the information of listing_card.xml
        listing = userListings.get(position);
        tvAddress.setText(listing.getAddress());

        return listingCard;
    }
}
