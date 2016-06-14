package com.aj.ayush.gibbr_fill;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.view.ViewAnimationUtils;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    private Button loginButton, loginButtonSelected;
    private FloatingActionButton registerFab, loginFab;
    private CardView registerCardView, loginCardView;
    private EditText password, email, registerPassword, confirmPassword, registerEmail;

    //TextWatcher watch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login_button);
        loginButtonSelected = (Button) findViewById(R.id.login_button_selected);
        loginCardView = (CardView) findViewById(R.id.login_cardview);
        registerCardView = (CardView) findViewById(R.id.register_cardview);
        registerFab = (FloatingActionButton) findViewById(R.id.register_fab);
        loginFab = (FloatingActionButton) findViewById(R.id.login_fab);
        password = (EditText) findViewById(R.id.input_password);
        email = (EditText) findViewById(R.id.input_email);

        registerPassword = (EditText) findViewById(R.id.register_input_password);
        confirmPassword = (EditText) findViewById(R.id.register_confirm_input_password);
        registerEmail = (EditText) findViewById(R.id.register_input_email);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide(loginButton);
            }
        });
        loginButtonSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show(loginButton);
            }
        });
        registerFab.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                hideLayout(loginCardView);
                email.setText("");
                password.setText("");
            }
        });

        loginFab.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                showLayout(loginCardView);
                registerPassword.setText("");
                registerEmail.setText("");
                confirmPassword.setText("");
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void show(final View view) {
        // get the center for the clipping circle
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
                0, finalRadius);
        anim.setDuration(500);

        // make the view visible and start the animation
        view.setVisibility(View.VISIBLE);
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showLayout(final View view) {
        // get the center for the clipping circle
        int cx = (registerFab.getLeft() + registerFab.getRight()) / 2;
        int cy = (registerFab.getTop() + registerFab.getBottom()) / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
                0, finalRadius);
        anim.setDuration(1000);

        // make the view visible and start the animation
        view.setVisibility(View.VISIBLE);
        anim.start();
    }

    // To hide a previously visible view using this effect:
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void hide(final View view) {

        // get the center for the clipping circle
        int cx = (view.getLeft() + view.getRight()) / 2;
        int cy = (view.getTop() + view.getBottom()) / 2;

        // get the initial radius for the clipping circle
        int initialRadius = view.getWidth();

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
                initialRadius, 0);
        anim.setDuration(500);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });

        // start the animation
        anim.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void hideLayout(final View view) {

        // get the center for the clipping circle
        int cx = (registerFab.getLeft() + registerFab.getRight()) / 2;
        int cy = (registerFab.getTop() + registerFab.getBottom()) / 2;

        // get the initial radius for the clipping circle
        int initialRadius = view.getWidth();

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy,
                initialRadius, 0);
        anim.setDuration(1000);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
            }
        });

        // start the animation
        anim.start();
    }

}
