package com.stateside.myweatherapp.common.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;

public class BaseActivity extends AppCompatActivity{

    private ProgressDialog progress = null;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

    }

    /*@Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;*/

    /*@Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    protected void closeFragment(BaseFragment f) {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
                    .remove(f)
                    .commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void goToFragment(BaseFragment f, @IdRes int idLayout) {

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                .replace(idLayout, f)
                .addToBackStack(null)
                .commit();
    }


    @Nullable
    public ProgressDialog getProgress() {
        return progress;
    }

    public void showProgress() {
        showProgress("");
    }

    public void showProgress(String msj){
        if (progress == null || progress.isShowing()) {
            progress = new ProgressDialog(this);
            progress.setIndeterminate(true);
            progress.setCanceledOnTouchOutside(false);
            progress.setCancelable(false);

            if (msj.isEmpty()) progress.setMessage(msj);

            progress.show();
        }

    }

    public void hideProgress() {
        if (progress != null && progress.isShowing()) {
            progress.hide();
            progress.dismiss();
            progress = null;
        }
    }

    public void clearAllFragmentStack() {
        FragmentManager fm = this.getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }

        String[] arregloasd = new String[0];
    }

    protected void popFragment(){
        getSupportFragmentManager().popBackStack();
    }


    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
