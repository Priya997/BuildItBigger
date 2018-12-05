

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
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
    public boolean testFlag = false;
    PublisherInterstitialAd mPublisherInterstitialAd = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MobileAds.initialize(getContext(),"ca-app-pub-3688611183353098~2791914911");
        mPublisherInterstitialAd = new PublisherInterstitialAd(getContext());
        mPublisherInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                progressBar.setVisibility(View.VISIBLE);
                getJoke();
                requestNewInterstitial();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                Log.i("Ads", "onAdFailedToLoad: ad Failed to load. Reloading...");
                requestNewInterstitial();
            }

            @Override
            public void onAdLoaded() {
                Log.i("Ads", "onAdLoaded: interstitial is ready!");
                super.onAdLoaded();
            }
        });
        requestNewInterstitial();
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        AdView mAdView = root.findViewById(R.id.adView);
        Button button =  root.findViewById(R.id.joke_btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mPublisherInterstitialAd.isLoaded()) {
                    Log.i("Ads", "onClick: interstitial was ready");
                    mPublisherInterstitialAd.show();
                } else {
                    Log.i("Ads", "onClick: interstitial was not ready.");
                    progressBar.setVisibility(View.VISIBLE);
                    getJoke();
                }
            }
        });

        progressBar =  root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);


        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    public void getJoke(){
        EndpointAsyncTask endpointAsyncTask = new EndpointAsyncTask(this);
        endpointAsyncTask.execute();
    }

    private void requestNewInterstitial() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mPublisherInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onDataReceived(String data) {
        if (!testFlag) {
            Context context = getActivity();
            Intent intent = new Intent(context, DisplayJokeActivity.class);
            intent.putExtra(context.getString(R.string.jokeEnvelope), data);
            context.startActivity(intent);
            progressBar.setVisibility(View.GONE);
        }
    }
}