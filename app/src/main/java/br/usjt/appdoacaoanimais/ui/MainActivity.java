package br.usjt.appdoacaoanimais.ui;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.usjt.appdoacaoanimais.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigationView();
        replaceFragment(R.id.frameLayoutMainActivity,
                HomeFragment.newInstance("",""),
                HomeFragment.HOME_FRAGMENT_TAG,
                "home");
    }

    private void setupNavigationView(){
        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                menuItem ->{
                    switch (menuItem.getItemId()){
                        case R.id.home:
                            replaceFragment(R.id.frameLayoutMainActivity,
                                    HomeFragment.newInstance("",""),
                                    HomeFragment.HOME_FRAGMENT_TAG,
                                    "home");
                            break;
                        case R.id.novoAnuncio:
                            replaceFragment(R.id.frameLayoutMainActivity,
                                    AnuncioFragment.newInstance("",""),
                                    AnuncioFragment.ANUNCIO_FRAGMENT_TAG,
                                    "home");
                            break;
                        case R.id.perfil:
                            replaceFragment(R.id.frameLayoutMainActivity,
                                    PerfilFragment.newInstance(false,""),
                                    PerfilFragment.PERFIL_FRAGMENT_TAG,
                                    "home");
                            break;
                        case R.id.configuracao:
                            replaceFragment(R.id.frameLayoutMainActivity,
                                    ConfiguracaoFragment.newInstance("",""),
                                    ConfiguracaoFragment.CONFIGURACAO_FRAGMENT_TAG,
                                    "home");
                            break;
                        default:
                            // nada!
                    }
                    return false;
                }
        );
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sair:
                finish();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}