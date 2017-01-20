package com.example.mohamedmabrouk.photomatch;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by Mohamed Mabrouk on 25/04/2016.
 */
public class PhotoFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView levels;
    private List<Integer> UsedPhotos;
    private ImageView current;
    private int WrongPlay=0;
    private int counter=0 , currentLevel=0;
    private List<String> compare=new ArrayList<>();
    private RatingBar ratingBar;
    public  static String ID="com.example.mohamedmabrouk.photomatch.id";
    private Integer []allPhotos={
        R.drawable.photo0,
            R.drawable.photo1,
            R.drawable.photo2,
            R.drawable.photo3,
            R.drawable.photo4,
            R.drawable.photo5,
            R.drawable.photo6,
            R.drawable.photo7,
            R.drawable.photo8,
            R.drawable.photo9,
            R.drawable.photo10,
            R.drawable.photo11,
            R.drawable.photo12,
            R.drawable.photo13,
            R.drawable.photo14,
            R.drawable.photo15,
            R.drawable.photo16,
            R.drawable.photo17,
            R.drawable.photo18,
            R.drawable.photo19,
            R.drawable.photo20,
            R.drawable.photo21,
            R.drawable.photo22,
            R.drawable.photo23,
            R.drawable.photo24,
            R.drawable.photo25,
            R.drawable.photo26,
            R.drawable.photo27,
            R.drawable.photo28,
            R.drawable.photo29,
            R.drawable.photo30

    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }


    private List<Integer> getUsedPhotos(int value , int size){
List<Integer> integers=new ArrayList<>();
        Random rng = new Random();
        Set<Integer> generated = new LinkedHashSet<Integer>();
        while (generated.size() < size)
        {
            Integer next = rng.nextInt(value) + 0;
            generated.add(next);

        }

        for (Integer integer:generated){
            integers.add(integer);
        }

      return integers;
    }

    private List<Integer> getRandomPhoto(List<Integer> list ) {
        List<Integer> integers = new ArrayList<>();

        int index = 0;
        for (int i = 0; i < 12; i++) {

            if (i >= 6) {
                integers.add(allPhotos[list.get(index)]);
                index++;
            } else {
                integers.add(allPhotos[list.get(i)]);
            }

        }
        return integers;

    }

    private List<Integer> getFinalPhotos(List<Integer> integerList){
        List<Integer> list=new ArrayList<>();
        List<Integer>  integers=getUsedPhotos(12,12);
        for(Integer integer:integers){
            list.add(integerList.get(integer));
        }

        return list;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.game_fragment, container, false);
        levels=(TextView)view.findViewById(R.id.levels);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyler_view);
        ratingBar=(RatingBar)view.findViewById(R.id.ratingBar);
        ratingBar.setEnabled(false);
        WindowManager windowManager=getActivity().getWindowManager();
        Display display=windowManager.getDefaultDisplay();

        if (display.getWidth()>display.getHeight()){
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        }
        //************************ for get number of levels *******************//
        LevelsCount  levelsCount=new LevelsCount();
        if (levelsCount.Read(getActivity())!=0){
          levels.setText("Level "+levelsCount.Read(getActivity()));
        }else{
            levels.setText("Level "+0);
        }
        UsedPhotos=getFinalPhotos(getRandomPhoto(getUsedPhotos(30, 6)));
        PhotoAdepter photoAdepter=new PhotoAdepter(UsedPhotos);
        recyclerView.setAdapter(photoAdepter);
        return view;
    }

    private class PhotoHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        public PhotoHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.photo_view);
            imageView.setOnClickListener(this);
        }

        public void bindphoto(Integer photo){
             imageView.setImageResource(photo);

        }

        @Override
        public void onClick(View v) {
           if(compare.size()==0){
            compare.add(String.valueOf(UsedPhotos.get(this.getAdapterPosition())));
               imageView.setImageResource(UsedPhotos.get(this.getAdapterPosition()));
               imageView.setEnabled(false);
               current=imageView;
           }else {
               if(compare.get(0).equals(String.valueOf(UsedPhotos.get(this.getAdapterPosition())))){
                   imageView.setImageResource(UsedPhotos.get(this.getAdapterPosition()));

                  new CountDownTimer(300,300) {
                      @Override
                      public void onTick(long millisUntilFinished) {

                      }

                      @Override
                      public void onFinish() {
                          imageView.setVisibility(View.INVISIBLE);
                          current.setVisibility(View.INVISIBLE);
                          compare.clear();
                      }
                  }.start();
                   if (counter==5){
                       counter=0;
                       ratingBar.setRating(counter);
                       Intent intent=new Intent(getActivity(),WinActivity.class);
                       intent.putExtra(ID, String.valueOf(WrongPlay));
                       startActivity(intent);
                       getActivity().finish();
                   }else {
                       counter++;
                       ratingBar.setRating(counter);
                   }
               }else{
                   WrongPlay++;
                   current.setEnabled(true);
                   imageView.setImageResource(UsedPhotos.get(this.getAdapterPosition()));
                  new CountDownTimer(500,500) {
                       @Override
                       public void onTick(long millisUntilFinished) {

                       }

                       @Override
                       public void onFinish() {
                           imageView.setImageResource(R.drawable.logo);
                           current.setImageResource(R.drawable.logo);
                           compare.clear();

                       }
                  }.start();
               }

           }
        }
    }



    private class PhotoAdepter extends RecyclerView.Adapter<PhotoHolder>{
        List<Integer> photos;

        public PhotoAdepter(List<Integer> Photos){
            this.photos=Photos;
        }
        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(getActivity());
            View  view=inflater.inflate(R.layout.image_view_list,parent,false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoHolder holder, int position) {
            holder.bindphoto(R.drawable.logo);
        }

        @Override
        public int getItemCount() {
            return photos.size();
        }
    }
}

