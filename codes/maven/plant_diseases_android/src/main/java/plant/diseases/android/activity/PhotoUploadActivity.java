package plant.diseases.android.activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.zyt.communicate.client.AbsWebController;
import com.zyt.communicate.common.UploadFile;
import com.zyt.communicate.common.UploadMessage;
import com.zyt.communicate.common.json.IReqBody;
import com.zyt.communicate.common.json.IRespBody;
import plant.diseases.android.DiseasesConstants;
import plant.diseases.android.R;
import plant.diseases.android.application.DiseasesApplication;
import plant.diseases.android.task.IComplete;
import plant.diseases.android.task.PhotoUploadTask;
import plant.diseases.android.task.UploadResp;
import plant.diseases.android.util.DeviceUtil;
import plant.diseases.android.util.ImageUtil;
import plant.diseases.android.util.MemoryUtil;
import plant.diseases.protocol.PhotoUploadReqBody;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Timothy on 2014/6/29.
 */
public class PhotoUploadActivity extends BaseActivity implements View.OnClickListener, IComplete<UploadResp>,
		Validator.ValidationListener {

	private static final String TAG = PhotoUploadActivity.class.getSimpleName();
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd_hhmmss");
	private final int RESULT_LOAD_IMAGE = 1;
	private final int RESULT_CAMERA = 2;

	@Inject
	AbsWebController<IReqBody, IRespBody> webController;

	@Required(order = 1, message = "请输入您的姓名")
	private EditText etUserName;
	@Required(order = 2, message = "请输入联系电话")
	private EditText etUserMobile;
	private EditText etUserDesc;
	@Required(order = 3, message = "请上传照片")
	private ImageView ivPhoto;
	private TextView tvPhotoSize;

	private Dialog dialog;
	private UploadMessage uploadMessage;
	private UploadFile uploadFile;
	private Validator validator;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_upload);
		((DiseasesApplication) this.getApplication()).inject(this);
		initHeader();
		init();
		// 初始化校验
		validator = new Validator(this);
		validator.setValidationListener(this);
	}

	@Override
	protected void onDestroy() {
		try {
			dialog.dismiss();
		} catch (Exception e) {
		}
		super.onDestroy();
	}

	private void init() {
		etUserName = (EditText) findViewById(R.id.etUserName);
		etUserMobile = (EditText) findViewById(R.id.etUserMobile);
		etUserDesc = (EditText) findViewById(R.id.etUserDesc);
		ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
		ivPhoto.setOnClickListener(this);
		tvPhotoSize = (TextView) findViewById(R.id.tvPhotoSize);
		Button btnUpload = (Button) findViewById(R.id.btnUpload);
		btnUpload.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivPhoto:
			openCamera();
			break;
		case R.id.btnPicByCamera:
			Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机
			startActivityForResult(intentCamera, RESULT_CAMERA);
			break;
		case R.id.btnPicByLibrary:
			Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, RESULT_LOAD_IMAGE);
			break;
		case R.id.btnClearPic:
			ivPhoto.setImageResource(R.drawable.feedback_upload);
			uploadMessage = null;
			uploadFile = null;
			tvPhotoSize.setText("");
			if (dialog != null) {
				dialog.dismiss();
			}
			break;
		case R.id.btnPicCancel:
			if (dialog != null) {
				dialog.dismiss();
			}
			break;
		case R.id.btnUpload:
			validator.validate();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case RESULT_LOAD_IMAGE:
			if (dialog != null) {
				dialog.dismiss();
			}
			if (resultCode == RESULT_OK && data != null) {
				try {
					Uri selectedImage = data.getData();
					String[] filePathColumn = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME,
							MediaStore.Images.Media.MIME_TYPE, MediaStore.Images.Media.SIZE };

					Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
					cursor.moveToFirst();
					String picturePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
					String fileName = cursor.getString(cursor.getColumnIndex(filePathColumn[1]));
					String mimeType = cursor.getString(cursor.getColumnIndex(filePathColumn[2]));
					Long fileSize = cursor.getLong(cursor.getColumnIndex(filePathColumn[3]));
					Log.i("fileSize", "" + fileSize);
					tvPhotoSize.setText("上传图片大小为" + fileSize / 1024 + "K");
					Log.i("memorySize", "" + MemoryUtil.getmem_UNUSED(this));
					cursor.close();
					if (fileSize > 1024 * 1024 * 4) {
						Toast.makeText(this, "图片大小应保持在4M以内", Toast.LENGTH_SHORT).show();
					} else if (fileSize >= MemoryUtil.getmem_UNUSED(this)) {
						Toast.makeText(this, "手机内存不足，请修改图片大小", Toast.LENGTH_SHORT).show();
					} else {
						Bitmap bitmap = ImageUtil.revitionImageSize(picturePath, 100);
						// Log.i("revitionImage", "" + bitmap.getByteCount());
						ivPhoto.setImageBitmap(bitmap);
						uploadMessage = new UploadMessage("fileField", fileName, mimeType);
						uploadFile = new UploadFile("fileField", fileName, mimeType, picturePath);
					}
				} catch (Exception ex) {
					Log.e(TAG, ex.getMessage(), ex);
					Toast.makeText(this, "未知错误:" + ex.getMessage(), Toast.LENGTH_SHORT);
				}
			}
			break;
		case RESULT_CAMERA:
			if (dialog != null) {
				dialog.dismiss();
			}
			if (resultCode == RESULT_OK && data != null) {
				cameraPhotoSave(data);
			}
			break;
		}
	}

	private void openCamera() {
		dialog = new android.app.Dialog(this, R.style.dialog_selectPic);
		dialog.setContentView(R.layout.dialog_picselecttype);
		dialog.getWindow().setGravity(Gravity.BOTTOM);
		dialog.setCanceledOnTouchOutside(false);
		Button btnCamera = (Button) dialog.findViewById(R.id.btnPicByCamera);
		btnCamera.setOnClickListener(this);
		Button btnLibrary = (Button) dialog.findViewById(R.id.btnPicByLibrary);
		btnLibrary.setOnClickListener(this);
		Button btnClearPic = (Button) dialog.findViewById(R.id.btnClearPic);
		btnClearPic.setOnClickListener(this);
		Button btnCancel = (Button) dialog.findViewById(R.id.btnPicCancel);
		btnCancel.setOnClickListener(this);
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		display.getMetrics(dm);
		dialog.getWindow().getAttributes().width = dm.widthPixels;
		dialog.show();
	}

	private void cameraPhotoSave(Intent data) {
		String sdState = Environment.getExternalStorageState();
		if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "未找到SD卡", Toast.LENGTH_SHORT).show();
			return;
		}
		Bundle bundle = data.getExtras();
		// 获取相机返回的数据，并转换为图片格式
		Bitmap bitmap = (Bitmap) bundle.get("data");
		// 取得照片保存DIR
		String photoSaveDir = DeviceUtil.getSDCardPath() + DiseasesConstants.getPhotoSaveDir();
		String saveFileName = DATE_FORMAT.format(new Date()) + ".jpg";
		File saveFile = new File(photoSaveDir + File.separator + saveFileName);
		if (!saveFile.getParentFile().exists()) {
			boolean mkDirsResult = saveFile.getParentFile().mkdirs();
			if (!mkDirsResult) {
				String errMsg = "创建照片保存路径失败：" + saveFile.getParent();
				Log.w(TAG, errMsg);
				Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
				return;
			}
		}
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(saveFile);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
			// 显示图片
			if (bitmap.getRowBytes() * bitmap.getHeight() > 1024 * 1024 * 4) {
				tvPhotoSize.setText("");
				Toast.makeText(this, "图片大小应保持在4M以内", Toast.LENGTH_SHORT).show();
			} else {
				ivPhoto.setImageBitmap(bitmap);
				uploadMessage = new com.zyt.communicate.common.UploadMessage("fileField", saveFileName, "image/jpg");
				uploadFile = new UploadFile("fileField", saveFileName, "image/jpg", saveFile.getPath());
				tvPhotoSize.setText("上传图片大小为" + bitmap.getRowBytes() * bitmap.getHeight() / 1024 + "K");
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		} finally {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void onValidationSucceeded() {
		if (uploadFile == null) {
			Toast.makeText(this, "请上传照片", Toast.LENGTH_SHORT).show();
		} else {
			PhotoUploadReqBody reqBody = new PhotoUploadReqBody();
			reqBody.setImg(uploadMessage);
			reqBody.setUserName(etUserName.getText().toString());
			reqBody.setUserMobile(etUserMobile.getText().toString());
			reqBody.setUserDesc(etUserDesc.getText().toString());
			PhotoUploadTask task = new PhotoUploadTask(this, this, reqBody, webController, uploadFile);
			task.execute();
		}
	}

	@Override
	public void onValidationFailed(View failedView, Rule<?> failedRule) {
		String message = failedRule.getFailureMessage();
		if (failedView instanceof EditText) {
			failedView.requestFocus();
			((EditText) failedView).setError(message);
		} else {
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onComplete(UploadResp uploadResp) {
		if (uploadResp.isResult()) {
			finish();
		}
	}
}
