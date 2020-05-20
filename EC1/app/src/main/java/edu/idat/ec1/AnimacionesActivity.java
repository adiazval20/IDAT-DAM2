package edu.idat.ec1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnimacionesActivity extends AppCompatActivity {
    CustomView cvCudrado;
    Button btnDerecha, btnIzquierda;
    int duracion = 500;
    float rotacion = 90f;
    float traslacion = 200f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animaciones);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Animaciones!");
        setSupportActionBar(toolbar);

        cvCudrado = findViewById(R.id.cvCuadrado);
        btnDerecha = findViewById(R.id.btnDerecha);
        btnIzquierda = findViewById(R.id.btnIzquierda);

        btnDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet set = new AnimatorSet();

                ObjectAnimator animTraslacion = ObjectAnimator.ofFloat(cvCudrado, "translationX", traslacion);
                animTraslacion.setDuration(duracion);
                set.play(animTraslacion);

                ObjectAnimator animRotacion = ObjectAnimator.ofFloat(cvCudrado, "rotation", rotacion);
                animRotacion.setDuration(duracion);
                set.play(animRotacion);

                set.start();
            }
        });

        btnIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet set = new AnimatorSet();

                ObjectAnimator animTraslacion = ObjectAnimator.ofFloat(cvCudrado, "translationX", 0);
                animTraslacion.setDuration(duracion);
                set.play(animTraslacion);

                ObjectAnimator animRotacion = ObjectAnimator.ofFloat(cvCudrado, "rotation", 0);
                animRotacion.setDuration(duracion);
                set.play(animRotacion);

                set.start();
            }
        });
    }
}