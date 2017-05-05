package com.example.filu.deckofcards;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class DisplayCardsFragment extends Fragment {
    CardImageAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    List<Drawable> images;
    int columnSpan;
    RecyclerView recyclerView;

    public DisplayCardsFragment() {
        // Required empty public constructor
    }

    public static DisplayCardsFragment newInstance(int columnSpan, List<Drawable> images) {
        DisplayCardsFragment fragment = new DisplayCardsFragment();
        //not a good way of passing arguments, gonna change it
        fragment.columnSpan = columnSpan;
        fragment.images = images;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_display_cards, container, false);

        recyclerView = (RecyclerView) rootView;
        mLayoutManager = new GridLayoutManager(getActivity(), columnSpan);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.scrollToPosition(0);

        mAdapter = new CardImageAdapter(images);
        recyclerView.setAdapter(mAdapter);

        return rootView;
    }



    public class CardImageAdapter extends RecyclerView.Adapter<CardImageAdapter.ViewHolder> {
        List<Drawable> images;

        public CardImageAdapter(List<Drawable> images) {
            this.images = images;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.img_row_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.img.setImageDrawable(images.get(position));
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img;

            public ViewHolder(View itemView) {
                super(itemView);
                img = (ImageView) itemView.findViewById(R.id.row_img);
                img.setImageDrawable(images.get(0)); //doesnt matter but I have give it some image to start with
            }
        }
    }
}
