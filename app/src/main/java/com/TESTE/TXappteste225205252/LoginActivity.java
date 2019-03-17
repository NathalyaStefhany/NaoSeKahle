package com.TESTE.TXappteste225205252;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText login = findViewById(R.id.editText26);
        final EditText senha = findViewById(R.id.editText25);
        Button botao = findViewById(R.id.button);
        Button botao1 = findViewById(R.id.button3);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Map<String, Object> td = new HashMap<>();
                        ArrayList<Usuario> usuarios = new ArrayList<>();
                        for (DataSnapshot jobSnapshot: dataSnapshot.getChildren()) {
                            Usuario usuario = jobSnapshot.getValue(Usuario.class);
                            String log = login.getText().toString();

                            if (login.getText().toString().equals(usuario.email)
                                    && senha.getText().toString().equals(usuario.senha)) {
                                Toast.makeText(LoginActivity.this,
                                        "usuario logado", Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("usuario", usuario);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                return;
                            }

                            Toast.makeText(LoginActivity.this,
                                    "usuario errado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        botao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
}
