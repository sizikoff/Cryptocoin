package ru.tihomirov.cryptocoin.activity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.tihomirov.cryptocoin.Api;
import ru.tihomirov.cryptocoin.App;
import ru.tihomirov.cryptocoin.Coin;
import ru.tihomirov.cryptocoin.CoinsAdapter;
import ru.tihomirov.cryptocoin.InAppBillingResources;
import ru.tihomirov.cryptocoin.PreferencesManager;
import ru.tihomirov.cryptocoin.R;

public class MainActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler{

    private RecyclerView recycler;
    private AdView mAdView;
    private CoinsAdapter adapter;
    private BillingProcessor bp;  // переменная нашего процессора
    boolean adsStatus;    // храним текущий статус отображения рекламы
    private boolean initialize;   // храним готовность к покупкам
    PreferencesManager prefManager; // класс, который работает с SharedPreferences. Я для себя решил вынести всю логику отдельно
    private Resources resources;// для работы с ресурсами. Раз получаем и постоянно обращаемся
    Context context;

    private static String PRODUCT_ID_BOUGHT = "item_1_bought";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context  = getApplicationContext();
        prefManager = new PreferencesManager(context);
        bp = new BillingProcessor(context, InAppBillingResources.getRsaKey(), InAppBillingResources.getMerchantId(), this);
        adsStatus = prefManager.getAdsStatus();        // получаем из `SharedPreferences` сохраненное состояние рекламы (ВКЛ / ВЫКЛ)
        bp.initialize();
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        adapter = new CoinsAdapter();

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        loadData();
        if(!BillingProcessor.isIabServiceAvailable(this)) {
            Toast.makeText(this, R.string.toast_show, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dollars:
                donate();
                return true;
            case R.id.devops:
                dev();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void dev() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.developement)
                .setMessage(R.string.waldemar)
                .setCancelable(false)
                .setNegativeButton(R.string.correct,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void donate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.out);  // заголовок
        builder.setMessage(R.string.reklama); // сообщение
        builder.setIcon(R.drawable.luck);
        builder.setPositiveButton(R.string.help, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                bp.purchase(MainActivity.this, InAppBillingResources.getSKU_Disable_Ads());
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


        private void loadData() {

        Application application = getApplication();
        App app = (App) application;
        Api api = app.getApi();

        api.ticker().enqueue(new Callback<List<Coin>>() {
            @Override
            public void onResponse(Call<List<Coin>> call, Response<List<Coin>> response) {
                Log.d("MainActivity", "onResponse");

                List<Coin> coins = response.body();

                Collections.sort(coins, new Comparator<Coin>() {
                    @Override
                    public int compare(Coin o1, Coin o2) {
                        return Double.compare(o2.priceUsd, o1.priceUsd);
                    }
                });


                adapter.setCoins(coins);
            }

            @Override
            public void onFailure(Call<List<Coin>> call, Throwable t) {
                Log.e("MainActivity", "onFailure", t);
            }
        });
    }
    

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        Toast.makeText(context, "Спасибо за поддержку", Toast.LENGTH_SHORT).show();
        }
    

    @Override
    public void onPurchaseHistoryRestored() {
        
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {
        initialize = true;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        
        }

   


    private String PRODUCT_ID(){
        return getResources().getString(R.string.product_id);
    }

    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();

        super.onDestroy();
    }
}
