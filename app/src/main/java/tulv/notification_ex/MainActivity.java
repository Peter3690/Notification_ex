package tulv.notification_ex;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View v){
        // khoi tao notificationmanager
        final NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //tao pintent khi click thong bao thi chay chuong trinh
        Intent intents=new Intent(MainActivity.this,Main2Activity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,1,intents,0);

        //xay dung notification.builder
        final Notification.Builder notify=new Notification.Builder(this);


        //thiet lap cac thuoc tinh cho notify
        notify.setSmallIcon(R.mipmap.ic_launcher);
        notify.setContentText("noi dung thong bao");
        notify.setContentTitle("thong bao");
        notify.setContentIntent(pendingIntent);
        //tu dong tat notify khi click
        notify.setAutoCancel(true);

        //tao them action cho notify
        notify.addAction(R.mipmap.ic_launcher,"goi dien",pendingIntent);
        notify.addAction(R.mipmap.ic_launcher,"nhan tin",pendingIntent);
        //tao noi dung hien thi dai
            notify.setStyle(new Notification.BigTextStyle().bigText("noi dung thong bao moi \n noi dung thong bao moi"));

        //thiet lap process cho notify

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<=10;i++){
                    notify.setProgress(10,i,false);
                    notificationManager.notify(1,notify.build());
                    try {
                        Thread.sleep(1*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //xoa process
                notify.setProgress(0,0,false);
                //
                notify.setContentTitle("hoan thanh");
                notificationManager.notify(1,notify.build());
            }
        }).start();


        notificationManager.notify(1,notify.build());
    }
}
