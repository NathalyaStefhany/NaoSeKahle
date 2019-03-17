package com.TESTE.TXappteste225205252;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    static Boolean clicou = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        clicou = false;

        final EditText nome = findViewById(R.id.nome);
        final EditText email = findViewById(R.id.email);
        final EditText senha = findViewById(R.id.senha);
        final EditText telefone = findViewById(R.id.phone);

        Button button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference();

                final ValueEventListener listener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (clicou) return;
                        clicou = true;
                        Map<String, Object> td = new HashMap<>();
                        List<Usuario> usuarios = new ArrayList<>();
                        for (DataSnapshot jobSnapshot: dataSnapshot.getChildren()) {
                            GenericTypeIndicator<Usuario> t = new GenericTypeIndicator<Usuario>() {};
                            Usuario usuario = jobSnapshot.getValue(Usuario.class);
                            usuarios.add(usuario);
                        }

                        Usuario usuario = new Usuario(nome.getText().toString(),
                                email.getText().toString(),
                                senha.getText().toString(),
                                telefone.getText().toString());
                        usuarios.add(usuario);
                        myRef.setValue(usuarios);

                        Toast.makeText(CadastroActivity.this, "Cadastro Efetuado com Sucesso!", Toast.LENGTH_SHORT).show();

                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };

                myRef.addValueEventListener(listener);
                myRef.removeValue();
            }
        });
    }
}
