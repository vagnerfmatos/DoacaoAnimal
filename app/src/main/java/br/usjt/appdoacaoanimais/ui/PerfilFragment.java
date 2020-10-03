package br.usjt.appdoacaoanimais.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.usjt.appdoacaoanimais.R;
import br.usjt.appdoacaoanimais.model.Usuario;
import br.usjt.appdoacaoanimais.model.UsuarioViewModel;


public class PerfilFragment extends Fragment {

    public static final String PERFIL_FRAGMENT_TAG = "perfil_fragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private UsuarioViewModel usuarioViewModel;
    private EditText editTextNome;
    private EditText editTextCPF;
    private EditText editTextTelefone;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private Usuario usuarioCorrente;
    private Button buttonSalvar;


    private Boolean vemDoLogin;
    private String mParam2;

    public PerfilFragment() {
    }


    public static PerfilFragment newInstance(Boolean param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            vemDoLogin = getArguments().getBoolean(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Hawk.init(getActivity()).build();

        usuarioCorrente = new Usuario();

        editTextNome = getView().findViewById(R.id.nomeEditText);
        editTextCPF = getView().findViewById(R.id.cpfEditText);
        editTextEmail = getView().findViewById(R.id.emailEditText);
        editTextTelefone = getView().findViewById(R.id.telefoneEditText);
        editTextSenha = getView().findViewById(R.id.senhaEditText);
        buttonSalvar = getView().findViewById(R.id.salvarButton);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        Log.d("CICLO_DE_VIDA","MainActivity --> onCreate");
        usuarioViewModel = new ViewModelProvider(getActivity()).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuario().observe(getActivity(), new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateView(usuario);
            }
        });
    }
    private void updateView(Usuario usuario){
        if(usuario != null && usuario.getId() > 0){
            usuarioCorrente = usuario;
            editTextNome.setText(usuario.getNome());
            editTextCPF.setText(usuario.getCpf());
            editTextEmail.setText(usuario.getEmail());
            editTextSenha.setText(usuario.getSenha());
            editTextTelefone.setText(usuario.getTelefone());
        }
    }

    public void salvar(){
        usuarioCorrente.setNome(editTextNome.getText().toString());
        usuarioCorrente.setCpf(editTextCPF.getText().toString());
        usuarioCorrente.setEmail(editTextEmail.getText().toString());
        usuarioCorrente.setSenha(editTextSenha.getText().toString());
        usuarioCorrente.setTelefone(editTextTelefone.getText().toString());
        usuarioViewModel.insert(usuarioCorrente);
        Hawk.put("tem_cadastro",true);
        Toast.makeText(getActivity(),"Usuário salvo com sucesso!",Toast.LENGTH_SHORT).show();
        if(vemDoLogin){
            getActivity().finish();
        }
    }
}