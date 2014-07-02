package plant.diseases.android.task;

import plant.diseases.android.DiseasesConstants;
import plant.diseases.android.util.NetworkUtil;
import plant.diseases.protocol.PhotoUploadReqBody;
import plant.diseases.protocol.PhotoUploadRespBody;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.zyt.communicate.client.AbsWebController;
import com.zyt.communicate.common.UploadFile;
import com.zyt.communicate.common.json.IReqBody;
import com.zyt.communicate.common.json.IRespBody;

/**
 * Created by Administrator on 2014-05-26.
 */
public class PhotoUploadTask extends AsyncTask<String, String, UploadResp> {
	private static final String TAG = PhotoUploadTask.class.getSimpleName();
	private static final String title = "照片上传";
	private static final String message = "照片上传中";

	private ProgressDialog dialog;
	private Context context;
	private IComplete<UploadResp> complete;
	private PhotoUploadReqBody request;
	private AbsWebController<IReqBody, IRespBody> webController;
	private com.zyt.communicate.common.UploadFile uploadFile;
	private int finishWaitTime = 800;

	public PhotoUploadTask(Context context, IComplete<UploadResp> complete, PhotoUploadReqBody request,
			AbsWebController<IReqBody, IRespBody> webController, UploadFile uploadFile) {
		this.context = context;
		this.complete = complete;
		this.request = request;
		this.webController = webController;
		this.uploadFile = uploadFile;
	}

	@Override
	protected void onPreExecute() {
		if (dialog == null) {
			dialog = getDialog(this.context, title, message);
		}
		if (!dialog.isShowing()) {
			dialog.show();
		}
	}

	@Override
	protected UploadResp doInBackground(String... params) {
		// 不允许dialog的取消操作
		this.dialog.setCancelable(false);
		if (NetworkUtil.isNetworkAvailable(context)) {
			// 提示正在下载文章
			publishProgress("您的照片正在上传...");
			try {
				PhotoUploadRespBody response = (PhotoUploadRespBody) webController.sendReq(request, uploadFile,
						DiseasesConstants.DISEASES_JSON_UPLOADFILE_URL);
				publishProgress("照片上传成功!");
				doFinishWait();
				return new UploadResp(true);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
				publishProgress("照片上传失败!原因：" + e.getMessage());
				doFinishWait();
				return new UploadResp(false);
			}
		} else {
			publishProgress("网络连接失败");
			doFinishWait();
			return new UploadResp(false);
		}
	}

	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		dialog.setMessage(values[0]);
	}

	@Override
	protected void onPostExecute(UploadResp result) {
		dialog.dismiss();
		if (this.complete != null) {
			this.complete.onComplete(result);
		}
	}

	private ProgressDialog getDialog(Context context, String mTitle, String message) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setTitle(mTitle);
		progressDialog.setMessage(message);
		return progressDialog;
	}

	private void doFinishWait() {
		if (this.finishWaitTime >= 100) {
			try {
				Thread.sleep(this.finishWaitTime);
			} catch (InterruptedException e) {
			}
		}
	}

}
