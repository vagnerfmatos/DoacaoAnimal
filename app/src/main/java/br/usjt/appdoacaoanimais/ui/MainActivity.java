package br.usjt.appdoacaoanimais.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.usjt.appdoacaoanimais.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cadastro:
                Intent intent = new Intent(this, CadastroUsuarioActivity.class);
                startActivity(intent);
                return (true);
            case R.id.sair:
                finish();
                return (true);
        }
        return (super.onOptionsItemSelected(item));


    }
}