package edu.idat.semana4;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class EfectosActivity extends AppCompatActivity {
    private Button btnIzquierda;
    private Button btnDerecha;
    private ImageView imgAnimX;

    private Button btnArriba;
    private Button btnAbajo;
    private ImageView imgAnimY;

    private Button btnMostrar;
    private Button btnOcultar;
    private ImageView imgAnimAlpha;

    private Button btnRotarIzquierda;
    private Button btnRotarDerecha;
    private ImageView imgAnimRotacion;

    private Button btnCombinado1;
    private Button btnCombinado2;
    private ImageView imgAnimCombinado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efectos);

        btnIzquierda = findViewById(R.id.btnIzquierda);
        btnDerecha = findViewById(R.id.btnDerecha);
        imgAnimX = findViewById(R.id.imgAnimX);

        btnArriba = findViewById(R.id.btnArriba);
        btnAbajo = findViewById(R.id.btnAbajo);
        imgAnimY = findViewById(R.id.imgAnimY);

        btnMostrar = findViewById(R.id.btnMostrar);
        btnOcultar = findViewById(R.id.btnOcultar);
        imgAnimAlpha = findViewById(R.id.imgAnimAlpha);

        btnRotarIzquierda = findViewById(R.id.btnRotarIzquierda);
        btnRotarDerecha = findViewById(R.id.btnRotarDerecha);
        imgAnimRotacion = findViewById(R.id.imgAnimRotacion);

        btnCombinado1 = findViewById(R.id.btnCombinado1);
        btnCombinado2 = findViewById(R.id.btnCombinado2);
        imgAnimCombinado = findViewById(R.id.imgAnimCombinado);

        btnDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animar(imgAnimX, "translationX", 200f, 600);
            }
        });

        btnIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animar(imgAnimX, "translationX", 0f, 600);
            }
        });

        btnArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animar(imgAnimX, "translationY", -200f, 600);
            }
        });

        btnAbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animar(imgAnimX, "translationY", 0f, 600);
            }
        });

        btnOcultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animar(imgAnimAlpha, "alpha", 1f, 0f, 600);
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animar(imgAnimAlpha, "alpha", 0f, 1f, 600);
            }
        });

        btnRotarIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animar(imgAnimRotacion, "rotation", 90f, 0f, 600);
            }
        });

        btnRotarDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animar(imgAnimRotacion, "rotation", 0f, 90f, 600);
            }
        });

        btnCombinado1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combinar(false, 600);
            }
        });

        btnCombinado2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                combinar(true, 600);
            }
        });
    }

    private void animar(View view, String efecto, float valor, int duracion) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, efecto, valor);
        animator.setDuration(duracion);
        animator.start();
    }

    private void animar(View view, String efecto, float desde, float hasta, int duracion) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, efecto, desde, hasta);
        animator.setDuration(duracion);
        animator.start();
    }

    private void combinar(boolean inicio, int duracion) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator traslacion, rotacion;
        float valorTraslacion = inicio ? 200f : 0f;
        float valorRotacionInicio = inicio ? 90f : 0f;
        float valorRotacionFinal = inicio ? 0f : 90f;

        traslacion = ObjectAnimator.ofFloat(imgAnimCombinado, "translationX", valorTraslacion);
        traslacion.setDuration(duracion);

        rotacion = ObjectAnimator.ofFloat(imgAnimCombinado, "rotation", valorRotacionInicio, valorRotacionFinal);
        rotacion.setDuration(duracion);

        animatorSet.play(traslacion);
        animatorSet.play(rotacion);
        animatorSet.start();
    }
}
