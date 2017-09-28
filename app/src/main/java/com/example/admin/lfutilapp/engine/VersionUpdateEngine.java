/*
package com.example.admin.lfutilapp.engine;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.stateunion.p2p.etongdai.BuildConfig;
import com.stateunion.p2p.etongdai.application.YiTongDaiApplication;
import com.stateunion.p2p.etongdai.custom.dialog.ETongDaiDialog;
import com.stateunion.p2p.etongdai.custom.dialog.UpdateProgressDialog;
import com.stateunion.p2p.etongdai.retrofit.RequestCommand;
import com.stateunion.p2p.etongdai.retrofit.bean.CheckUpdateBean;
import com.stateunion.p2p.etongdai.retrofit.bean.entity.CheckUpdateEntity;
import com.stateunion.p2p.etongdai.retrofit.callback.DialogCallback;
import com.stateunion.p2p.etongdai.retrofit.view.IBaseDialogView;
import com.stateunion.p2p.etongdai.util.AndroidUtil;
import com.stateunion.p2p.etongdai.util.ErrorDialogUtil;

import java.io.File;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;

*/
/**
 * 版本更新
 *//*


public class VersionUpdateEngine {
    public static final String SP_KEY_CHECK_MILLIS_TIME = "sp_key_check_Millis_time";
    public static final int CHECK_UPDATE_INTERVAL = 3 * 24 * 60 * 60 * 1000;
    private static final String SP_NAME_VERSION_UPDATE = "SP_NAME_VERSION_UPDATE_HOMEPAGE";

    */
/**
     * @param context
     * @param url
     *//*

    public static void download(final Context context, final String url) {
        AsyncHttpClient client = new AsyncHttpClient();
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + BuildConfig.APPLICATION_ID, "ETongDai.apk");
        client.get(url,
                new FileAsyncHttpResponseHandler(file) {
                    UpdateProgressDialog pBar;
                    @Override
                    public void onStart() {
                        pBar = new UpdateProgressDialog(context); // 进度条，在下载的时候实时更新进度，提高用户友好度
                        pBar.setCancelable(!isMandatoryUpdate());
                        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        pBar.setProgress(0);
                        pBar.show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                        pBar.cancel();
                        String leftButton;
                        if (isMandatoryUpdate()) {
                            leftButton = "退出应用";
                        } else {
                            leftButton = "暂不更新";
                        }
                        new ETongDaiDialog(context)
                                .setContent("网络不佳，下载失败！")
                                .setLeftButton(leftButton)
                                .setRightButton("重新下载")
                                .setDoubleListener(new ETongDaiDialog.EmptyETongDaiDialogListener() {
                                    @Override
                                    public void OnLeftButtonClicked(ETongDaiDialog dialog) {
                                        dialog.dismiss();
                                        if (isMandatoryUpdate()) {
                                            System.exit(0);
                                        }
                                    }
                                    @Override
                                    public void OnRightButtonClicked(ETongDaiDialog dialog) {
                                        download(context, url);
                                        dialog.dismiss();
                                    }
                                }).setCancelableChain(false)
                                .show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, final File file) {
                        pBar.cancel();
                        AndroidUtil.installApk(file);
                        if (isMandatoryUpdate()) {
                            new ETongDaiDialog(context)
                                    .setLeftButton("退出应用")
                                    .setRightButton("安装")
                                    .setContent("本次更新内容较多，不更新会导致部分功能无法使用，建议您完成本次升级")
                                    .setDoubleListener(new ETongDaiDialog.EmptyETongDaiDialogListener() {
                                        @Override
                                        public void OnLeftButtonClicked(ETongDaiDialog dialog) {
                                            dialog.dismiss();
                                            System.exit(0);
                                        }

                                        @Override
                                        public void OnRightButtonClicked(ETongDaiDialog dialog) {
                                            AndroidUtil.installApk(file);
                                        }
                                    }).show();
                        }
                    }

                    @Override
                    public void onProgress(long bytesWritten, long totalSize) {
                        pBar.setProgress((int) (bytesWritten * 100 / totalSize)); // 这里就是关键的实时更新进度了！
                    }
                });
    }

    static SharedPreferences getSharePreferences() {
        return YiTongDaiApplication.sApplication.getSharedPreferences(SP_NAME_VERSION_UPDATE, Context.MODE_PRIVATE);
    }

    public static boolean isMandatoryUpdate() {
        return getSharePreferences().getBoolean(AndroidUtil.getVersionName(), false);
    }

    public static void mandatoryUpdate() {
        getSharePreferences().edit().putBoolean(AndroidUtil.getVersionName(), true).apply();
    }

    public static boolean isCheckUpdate() {
        long checkMillis = getSharePreferences().getLong(SP_KEY_CHECK_MILLIS_TIME, -1);
        if (checkMillis == -1) return true;
        if (System.currentTimeMillis() >= checkMillis) return true;
        return false;
    }

    public static void setCheckUpdateTime() {
        getSharePreferences().edit().putLong(SP_KEY_CHECK_MILLIS_TIME, System.currentTimeMillis() + CHECK_UPDATE_INTERVAL).apply();
    }

    */
/**
     * 版本检测
     *//*

    public static void checkVersion(IVersionUpdateView view) {
        if (isCheckUpdate() || isMandatoryUpdate()) //提示
            RequestCommand.checkUpdate(new CheckUpdateCallback(view), AndroidUtil.getVersionName());
        else  //不提示
            view.onDontUpdate();
    }

    public interface IVersionUpdateView extends IBaseDialogView {
        void onDontUpdate();
    }

    private static class CheckUpdateCallback extends DialogCallback<CheckUpdateBean, IVersionUpdateView> {

        public CheckUpdateCallback(IVersionUpdateView requestView) {
            super(requestView);
        }

        @Override
        protected void onResponseSuccess(CheckUpdateBean checkUpdateBean, Call<CheckUpdateBean> call) {
            CheckUpdateBean.CheckUpdateBody updateBody = checkUpdateBean.body;
            String isUsing = updateBody.isUsing;
            CheckUpdateEntity versionMdl = updateBody.tbPpctVersionMdl;
            switch (isUsing) {
                case "1": //有新版本，不需要强制更新
                    ErrorDialogUtil.showVersionUpdateDialog(
                            getAttachTarget().getBaseActivity(),
                            versionMdl.getCurrentVersion(),
                            versionMdl.getDescription(),
                            new VersionUpdateDialogListener(getAttachTarget(), versionMdl.getUrl()));
                    break;
                case "2": //强制更新新版本
                    VersionUpdateEngine.mandatoryUpdate();
                    ErrorDialogUtil.showForceUpdateDialog(
                            getAttachTarget().getBaseActivity(),
                            versionMdl.getCurrentVersion(),
                            versionMdl.getDescription(),
                            new forceUpdateDialogListener(getAttachTarget(), versionMdl.getUrl()));
                    break;
            }
        }

        @Override
        public void onFailure(Call<CheckUpdateBean> call) {
            if (isMandatoryUpdate()) {
                new ETongDaiDialog(getAttachTarget().getBaseActivity())
                        .setCancelableChain(false)
                        .setLeftButton("退出应用")
                        .setRightButton("重新加载")
                        .setContent("本次更新内容较多，不更新会导致部分功能无法使用，建议您完成本次升级")
                        .setDoubleListener(new ETongDaiDialog.EmptyETongDaiDialogListener() {
                            @Override
                            public void OnLeftButtonClicked(ETongDaiDialog dialog) {
                                dialog.dismiss();
                                System.exit(0);
                            }

                            @Override
                            public void OnRightButtonClicked(ETongDaiDialog dialog) {
                                dialog.dismiss();
                                checkVersion(getAttachTarget());
                            }
                        }).show();
            } else {
                getAttachTarget().onDontUpdate();
            }

        }

        @Override
        protected void onResponseFailureMessage(CheckUpdateBean checkUpdateBean, Call<CheckUpdateBean> call) {
            getAttachTarget().onDontUpdate();
        }
    }

    */
/**
     * 版本更新（强制更新dialog Listener）
     *//*

    private static class forceUpdateDialogListener implements ETongDaiDialog.EtdSingleListener {
        private String url;
        private IVersionUpdateView view;

        public forceUpdateDialogListener(IVersionUpdateView view, String url) {
            this.url = url;
            this.view = view;
        }

        @Override
        public void OnLeftButtonClicked(ETongDaiDialog dialog) {
            dialog.dismiss();
            download(view.getBaseActivity(), url);
        }
    }

    */
/**
     * 版本更新（dialog Listener）
     *//*

    private static class VersionUpdateDialogListener implements ETongDaiDialog.EtdDoubleListener {
        String url;
        IVersionUpdateView view;

        VersionUpdateDialogListener(IVersionUpdateView view, String url) {
            this.url = url;
            this.view = view;
        }

        @Override
        public void OnLeftButtonClicked(ETongDaiDialog dialog) {
            dialog.dismiss();
            // 暂不更新, 指定时间后再提示
            setCheckUpdateTime();
            view.onDontUpdate();
        }

        @Override
        public void OnRightButtonClicked(ETongDaiDialog dialog) {
            dialog.dismiss();
            download(view.getBaseActivity(), url);
        }
    }
}
*/
