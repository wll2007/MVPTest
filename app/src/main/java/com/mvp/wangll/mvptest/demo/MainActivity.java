package com.mvp.wangll.mvptest.demo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mvp.wangll.mvptest.Mvpframework.base.PresentFactory;
import com.mvp.wangll.mvptest.Mvpframework.base.PresenterLoader;
import com.mvp.wangll.mvptest.Mvpframework.support.Lce.activity.MvpLceActivity;
import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.camera.CameraPreviewFragment;
import com.mvp.wangll.mvptest.customWidget.commonAlertDialog.CommonAlertDialog;
import com.mvp.wangll.mvptest.customWidget.navigationBar.DefaultNavigationBar;
import com.mvp.wangll.mvptest.demo.IView.ILoginView;
import com.mvp.wangll.mvptest.demo.list.LoadLcAnimator;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.DownInfo;
import com.mvp.wangll.mvptest.demo.list.loginPresenter;
import com.mvp.wangll.mvptest.greenDao.DbManager;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


@RuntimePermissions
public class MainActivity extends MvpLceActivity<MainActivity,loginPresenter<ActivityEvent,MainActivity>,ActivityEvent> implements ILoginView<ActivityEvent>,  View.OnClickListener {
    private EditText name;
    private EditText password;
    private Button login;
    private Button look;
    private Button down;
    private ProgressBar progress;
    private Button stop;
    private Button cancel;
    private TextView progressTxtView;
    private Button Camera;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        look = (Button)findViewById(R.id.look);
        look.setOnClickListener(this);
        down = (Button)findViewById(R.id.download);
        down.setOnClickListener(this);
        setLceAnimator(new LoadLcAnimator());
        progress = findViewById(R.id.progress);
        stop = findViewById(R.id.stop);
        stop.setOnClickListener(this);
        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
        progressTxtView=(TextView) findViewById(R.id.downProgress);
        Camera = findViewById(R.id.Camera);
        Camera.setOnClickListener(this);
        /*Camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivityPermissionsDispatcher.showCameraWithPermissionCheck(MainActivity.this);
            }
        });*/
//        MainActivityPermissionsDispatcher.applyStoragePermissionWithPermissionCheck(this);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                GetPresenter().login(name.getText().toString(), password.getText().toString());
                break;
            case R.id.download:
                GetPresenter().down("http://dldir1.qq.com/weixin/android/weixin657android1040.apk");
                break;
            case R.id.look:
                GetPresenter().look("http://dldir1.qq.com/weixin/android/weixin657android1040.apk");
                break;
            case R.id.stop:
                GetPresenter().stop("http://dldir1.qq.com/weixin/android/weixin657android1040.apk");
                break;
            case R.id.cancel:
                GetPresenter().cancel("http://dldir1.qq.com/weixin/android/weixin657android1040.apk");
                break;
            case R.id.Camera:
               /* CommonAlertDialog dialog = new CommonAlertDialog.Builder(this, R.style.dialog)
                        .setContentView(R.layout.dialog_test)
                        .fromButtom(true)
                        .fullWith()
                        .show();*/
               startActivity(new Intent(MainActivity.this,Main2Activity.class));
                break;
        }
    }


    @Override
    public MainActivity CreateView() {
        return this;
    }

    @Override
    public Loader<loginPresenter<ActivityEvent,MainActivity>> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(MainActivity.this, new PresentFactory<loginPresenter<ActivityEvent,MainActivity>>() {
            @Override
            public loginPresenter<ActivityEvent,MainActivity> create() {
                return new loginPresenter<>();
            }
        });
    }

    @Override
    public LifecycleProvider<ActivityEvent> getRxLifecycle() {
        return this;
    }

    @Override
    public ActivityEvent getRxEvent() {
        return ActivityEvent.DESTROY;
    }

    @Override
    public void bindData(DownInfo bean) {
        if(bean!=null) {
            Toast.makeText(this, bean.toString(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "没有数据！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateProgress(long ready, long total) {
        progress.setMax((int) (total/1024));
        progress.setProgress((int) (ready/1024));
        progressTxtView.setText(progress.getProgress()+"/"+progress.getMax());
    }

    @Override
    public void completeProgress(long total) {
        stop.setClickable(false);
    }

    @Override
    public void errorOfFileDown(String Msg) {
        Toast.makeText(this,Msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void preFileDown() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        // NOTE: Perform action that requires the permission. If this is run by PermissionsDispatcher, the permission will have been granted
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample_content_fragment, CameraPreviewFragment.newInstance())
                .addToBackStack("camera")
                .commitAllowingStateLoss();
    }

   /* @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied() {
        // NOTE: Deal with a denied permission, e.g. by showing specific UI
        // or disabling certain functionality
        Toast.makeText(this, R.string.permission_camera_denied, Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(PermissionRequest request) {
        // NOTE: Show a rationale to explain why the permission is needed, e.g. with a dialog.
        // Call proceed() or cancel() on the provided PermissionRequest to continue or abort
        showRationaleDialog(R.string.permission_camera_rationale, request);
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNeverAskAgain() {
        Toast.makeText(this, R.string.permission_camera_never_ask_again, Toast.LENGTH_SHORT).show();
    }*/

    private void showRationaleDialog(@StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.button_deny, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void applyStoragePermission() {
    }
}
