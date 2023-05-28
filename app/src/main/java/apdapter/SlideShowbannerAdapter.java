package apdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salemanager.R;


import java.util.List;

import model.SlideShowBanner;


public class SlideShowbannerAdapter extends RecyclerView.Adapter<SlideShowbannerAdapter.SlideShowHolder>  {

    private List<SlideShowBanner> mlistSlide;

    public SlideShowbannerAdapter(List<SlideShowBanner> mlistSlide) {
        this.mlistSlide = mlistSlide;
    }

    @NonNull
    @Override
    public SlideShowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slidebanner,parent,false);

        return new SlideShowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideShowHolder holder, int position) {
        SlideShowBanner slideShowBanner = mlistSlide.get(position);
        if(slideShowBanner == null){

            return;
        }
        holder.imageView.setImageResource(slideShowBanner.getResourceId());
    }

    @Override
    public int getItemCount() {
        if(mlistSlide != null){
            return  mlistSlide.size();
        }
        return 0;
    }

    public class SlideShowHolder extends RecyclerView.ViewHolder{
        private ImageView imageView ;

        public SlideShowHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_photo);
        }
    }
}
