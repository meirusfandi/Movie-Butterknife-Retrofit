package com.meirusfandi.searchmovie.helper;import android.content.Context;import android.support.annotation.NonNull;import android.support.v7.widget.RecyclerView;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ImageView;import android.widget.TextView;import com.meirusfandi.searchmovie.BuildConfig;import com.meirusfandi.searchmovie.R;import com.meirusfandi.searchmovie.data.MovieModel;import com.squareup.picasso.Picasso;import java.text.ParseException;import java.text.SimpleDateFormat;import java.util.Date;import java.util.List;import butterknife.BindView;import butterknife.ButterKnife;public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder>{    private List<MovieModel> movieModels;    private Context context;    public MovieAdapter(List<MovieModel> movieModels, Context context) {        this.movieModels = movieModels;        this.context = context;    }    public List<MovieModel> getMovieModels() {        return movieModels;    }    public void setMovieModels(List<MovieModel> movieModels) {        this.movieModels = movieModels;    }    @NonNull    @Override    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {        return new MovieHolder(LayoutInflater.from(context).inflate(R.layout.list_movie, parent, false));    }    @Override    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {        MovieModel movieModel = movieModels.get(position);        String judul = movieModel.getTitle();        holder.title.setText(judul);        String getrilis = movieModel.getRelease();        String rilis = "";        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");        try {            Date date = dateFormat.parse(getrilis);            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MM dd, yyyy");            rilis = simpleDateFormat.format(date);            holder.release.setText(rilis);        } catch (ParseException e){            e.printStackTrace();        }        String deskripsi = "Nothing Overview Data";        if (!movieModel.getOverview().isEmpty()){            deskripsi = movieModel.getOverview();        }        holder.overview.setText(deskripsi);        String path = movieModel.getPoster();        Picasso.with(context).                load(BuildConfig.POSTER_PATH+path)                .placeholder(context.getResources().getDrawable(R.drawable.ic_launcher_foreground))                .error(context.getResources().getDrawable(R.drawable.ic_launcher_foreground))                .into(holder.poster);        holder.itemView.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {            }        });    }    @Override    public int getItemCount() {        if (movieModels == null)            return 0;        return movieModels.size();    }    class MovieHolder extends RecyclerView.ViewHolder {        @BindView(R.id.poster)        ImageView poster;        @BindView(R.id.title)        TextView title;        @BindView(R.id.release)        TextView release;        @BindView(R.id.overview)        TextView overview;        MovieHolder(View itemView) {            super(itemView);            ButterKnife.bind(this, itemView);        }    }}