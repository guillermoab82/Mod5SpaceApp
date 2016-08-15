package memoscorp.unam.mx.spaceappm5.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import memoscorp.unam.mx.spaceappm5.R;
import memoscorp.unam.mx.spaceappm5.RecyclerViewActivity;

/**
 * Created by GuillermoAB on 13/08/2016.
 */
public class NasaApodLoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {
    @BindView(R.id.fb_loging_button)
    LoginButton login;
    CallbackManager callBackManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nasaapodloginactivity);
        ButterKnife.bind(this);

        callBackManager = CallbackManager.Factory.create();
        login.registerCallback(callBackManager,this);

        if(AccessToken.getCurrentAccessToken()!=null){
            //Snackbar.make(findViewById(android.R.id.content),"Login IF",Snackbar.LENGTH_SHORT).show();
            startActivity(new Intent(this, RecyclerViewActivity.class));
        }
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        //Snackbar.make(findViewById(android.R.id.content),"Login",Snackbar.LENGTH_SHORT).show();
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    @Override
    public void onCancel() {
        Snackbar.make(findViewById(android.R.id.content),"Login Cancel",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Snackbar.make(findViewById(android.R.id.content), error.getMessage(),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callBackManager.onActivityResult(requestCode,resultCode,data);
    }
}
