package texodian.com.snale;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;


public class SplashScreen extends Activity{

    //Splash screen timer
    //private static int SPLASH_TIME_OUT = 3000;

    private Thread mSplashThread;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        final SplashScreen splash = this;

        mSplashThread = new Thread(){
            @Override
            public void run(){
                try{
                    synchronized (this){
                        wait(1000);
                    }
                }catch (InterruptedException ex){
                }

                finish();
                Intent intent = new Intent();
                intent.setClass(splash, SignInActivity.class);
                startActivity(intent);
            }
        };
        mSplashThread.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent evt){
        if(evt.getAction() == MotionEvent.ACTION_DOWN){
            synchronized (mSplashThread){
                mSplashThread.notifyAll();
            }
        }
        return true;
    }
}
