import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.prudhvi.androidjokelib.DisplayJokeActivity;
import com.udacity.gradle.builditbigger.DataRecieveInterface;
import com.udacity.gradle.builditbigger.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements DataRecieveInterface {

    public MainActivityFragment() {
    }

    ProgressBar progressBar = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
               View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button =  root.findViewById(R.id.joke_btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
            }
        });

        progressBar =  root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);

        return root;
    }


    public void getJoke(){
        EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask(this);
        endpointAsyncTask.execute();
    }


    @Override
    public void onDataReceived(String data) {
            Context context = getActivity();
            Intent intent = new Intent(context, DisplayJokeActivity.class);
            intent.putExtra(context.getString(R.string.jokeEnvelope), data);
            context.startActivity(intent);
            progressBar.setVisibility(View.GONE);
    }
}
