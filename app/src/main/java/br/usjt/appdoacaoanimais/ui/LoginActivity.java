package br.usjt.appdoacaoanimais.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.usjt.appdoacaoanimais.R;
import br.usjt.appdoacaoanimais.model.Usuario;
import br.usjt.appdoacaoanimais.model.UsuarioViewModel;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsuario;
    private EditText editTextSenha;
    private Button buttonLogin;
    private TextView textViewNovoCadastro;
    private UsuarioViewModel usuarioViewModel;
    private Usuario usuarioCorrente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Hawk.init(this).build();

        editTextSenha = findViewById(R.id.senhaEditText);
        editTextUsuario = findViewById(R.id.usuarioEditText);
        buttonLogin = findViewById(R.id.loginButton);
        textViewNovoCadastro = findViewById(R.id.novoCadastroTextView);

        verificaCadastro();

        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);
        usuarioViewModel.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                usuarioCorrente = usuario;
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        verificaCadastro();
    }

    private void verificaCadastro(){
        //existe a propriedade cadastro?
        if(Hawk.contains("tem_cadastro")){

            if(Hawk.get("tem_cadastro")){
                //Se usuario tem cadastro
                textViewNovoCadastro.setVisibility(View.GONE);
                buttonLogin.setEnabled(true);
                buttonLogin.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            }else{
                textViewNovoCadastro.setVisibility(View.VISIBLE);
                buttonLogin.setEnabled(false);
                buttonLogin.setBackgroundColor(Color.GRAY);
            }

        }else{
            //Se usuario não tem cadastro
            textViewNovoCadastro.setVisibility(View.VISIBLE);
            buttonLogin.setEnabled(false);
            buttonLogin.setBackgroundColor(Color.GRAY);
        }

    }

    public void logar(View view) {

        if(usuarioCorrente !=null){
            if(usuarioCorrente.getEmail().equalsIgnoreCase(editTextUsuario.getText().toString())
                    && usuarioCorrente.getSenha().equalsIgnoreCase(editTextSenha.getText().toString())){
                editTextSenha.setText("");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this,"Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void novoCadastro(View view) {

        Intent intent = new Intent(this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}