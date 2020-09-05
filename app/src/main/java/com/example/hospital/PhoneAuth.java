package com.example.hospital;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneAuth extends AppCompatActivity {
    Button verify,codeverify;
    EditText Phone,codeedit;
    FirebaseAuth firebaseAuth;
    AlertDialog.Builder builder;
    PhoneAuthProvider.ForceResendingToken mResendToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         firebaseAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_phone_auth);
        Phone = findViewById(R.id.phone);
        verify = findViewById(R.id.verify);
        codeedit = findViewById(R.id.codeedit);
        codeverify = findViewById(R.id.codeverify);
        codeverify.setVisibility(View.GONE);
        codeedit.setVisibility(View.GONE);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = Phone.getText().toString().trim();

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+phone,        // Phone number to verify
                        120,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        PhoneAuth.this,
                        Callback);// Activity (for callback binding)

            }
        });
    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks Callback= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {

            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user actio
            Log.d("TAG", "onVerificationCompleted:" + credential);
            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            Log.w("TAG", "onVerificationFailed", e);
            codeverify.setVisibility(View.GONE);
            codeedit.setVisibility(View.GONE);
            verify.setVisibility(View.VISIBLE);
            Phone.setVisibility(View.VISIBLE);
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(PhoneAuth.this, "inValid request", Toast.LENGTH_SHORT).show();
            } else if (e instanceof FirebaseTooManyRequestsException) {
                Toast.makeText(PhoneAuth.this, "sms limit exceed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCodeSent(@NonNull final String verificationId,
                               @NonNull final PhoneAuthProvider.ForceResendingToken token) {
            // Save verification ID and resending token so we can use them late
            mResendToken = token;
            codeverify.setVisibility(View.VISIBLE);
            codeedit.setVisibility(View.VISIBLE);
            verify.setVisibility(View.GONE);
            Phone.setVisibility(View.GONE);
            codeverify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String code=codeedit.getText().toString();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                    signInWithPhoneAuthCredential(credential);

                }
            });


        }

    };


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();

                            Toast.makeText(PhoneAuth.this, "successful", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            // ...
                        } else {

                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

}