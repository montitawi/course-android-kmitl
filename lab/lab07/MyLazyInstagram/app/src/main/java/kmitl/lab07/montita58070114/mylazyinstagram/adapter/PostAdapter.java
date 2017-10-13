package kmitl.lab07.montita58070114.mylazyinstagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab07.montita58070114.mylazyinstagram.R;
import kmitl.lab07.montita58070114.mylazyinstagram.model.PostModel;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {
    private Activity activity;
    private List<PostModel> data;

    public PostAdapter(Activity activity) {
        this.activity = activity;
        data = new ArrayList<>();
    }

    public void setData(List<PostModel> data) {
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        Holder holder = new Holder(itemView);
        return holder;
//        View view;
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        view = inflater.inflate(R.layout.item, null, false);
//        Holder holder = new Holder(view);
//        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String imageUrl = data.get(position).getUrl();
        Glide.with(activity).load(imageUrl).into(holder.imageView);
//        holder.like.setText(data.get(position).getLike());
//        holder.comment.setText(data.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView comment;
        TextView like;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
//            comment =  comment.findViewById(R.id.comment);
//            like =  like.findViewById(R.id.like);
        }
    }
}
