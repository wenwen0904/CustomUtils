package tw.customer.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: wenwen
 * @Date: 2016/4/25 15:52
 * @version: 1.0
 */
public class ImageUtil {

	private static int MAX_IMAGE_DIMENSION = 1280;
	private static final String TAG = "ImageUtil";

	/**
	 * 按比例压缩到指定宽高的图片
	 */
	public static String getReSizeImagePath(String oPath, String thumbfilePath, String ext, int WidthOrHeight) {
		if (TextUtils.isEmpty(oPath))
			return "";
		File file = new File(thumbfilePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 压缩图片
		Bitmap bitmap = getImage(oPath);
		// 原始图片的高宽
		int[] cur_img_size = new int[]{bitmap.getWidth(), bitmap.getHeight()};
		// 计算原始图片缩放后的宽高
		int[] new_img_size = scaleImageSize(cur_img_size, WidthOrHeight);
		bitmap = BitmapUtil.zoom(bitmap, new_img_size[0], new_img_size[1]);
		boolean isSuccess = ImageUtil.saveBitmapFile(bitmap, thumbfilePath, ext);
		if (isSuccess) {
			Log.d(TAG, thumbfilePath);
			return thumbfilePath;
		}
		return oPath;
	}

	/**
	 * 计算缩放图片的宽高
	 * @param img_size
	 * @param square_size
	 */
	public static int[] scaleImageSize(int[] img_size, int square_size) {
		if (img_size[0] <= square_size && img_size[1] <= square_size)
			return img_size;
		double ratio = square_size / (double) Math.max(img_size[0], img_size[1]);
		return new int[]{(int) (img_size[0] * ratio), (int) (img_size[1] * ratio)};
	}

	/**
	 * 将Bitmap文件保存为文件路径下
	 * @param bm
	 * @param imagePath
	 */
	public static boolean saveBitmapFile(Bitmap bm, String imagePath, String type) {
		if (imagePath == null || imagePath.equals(""))
			return false;
		if (bm == null)
			return false;
		File f = new File(imagePath);
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		BufferedOutputStream stream = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			stream = new BufferedOutputStream(fos);
			if (type != null && type.equals("png")) {
				bm.compress(Bitmap.CompressFormat.PNG, 80, stream);
			} else {
				bm.compress(Bitmap.CompressFormat.JPEG, 80, stream);
			}
			stream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ImageUtil", "压缩图片时出错:" + e.getMessage());
			return false;
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bm != null && !bm.isRecycled()) {
				bm.recycle();
				bm = null;
			}
		}
		return true;
	}


	public static Bitmap getImage(String fileName) {
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(fileName);
			FileDescriptor fd = stream.getFD();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 1;
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFileDescriptor(fd, null, options);
			if (options.mCancel || options.outWidth == -1
					|| options.outHeight == -1) {
				return null;
			}

			// 1.换算合适的图片缩放值，以减少对JVM太多的内存请求。
			options.inSampleSize = computeSampleSize(options.outWidth, options.outHeight);
			options.inJustDecodeBounds = false;

			options.inDither = false;
			options.inPreferredConfig = Bitmap.Config.ARGB_8888;

			// 2. inPurgeable 设定为 true，可以让java系统, 在内存不足时先行回收部分的内存
			options.inPurgeable = true;
			// 与inPurgeable 一起使用
			options.inInputShareable = true;

			try {
				// 4. inNativeAlloc 属性设置为true，可以不把使用的内存算到VM里
				BitmapFactory.Options.class.getField("inNativeAlloc").setBoolean(options, true);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			// 5. 使用decodeStream 解码，则利用NDK层中，利用nativeDecodeAsset（）
			// 进行解码，不用CreateBitmap
			return BitmapFactory.decodeStream(stream, null, options);

		} catch (IOException ex) {
			Log.e(TAG, "", ex);
		} catch (OutOfMemoryError oom) {
			Log.e(TAG, "Unable to decode file " + fileName + ". OutOfMemoryError.", oom);
		} finally {
			try {
				if (stream != null) {
					stream.close();
				}
			} catch (IOException ex) {
				Log.e(TAG, "", ex);
			}
		}
		return null;
	}

	public static int computeSampleSize(int width, int height) {
		if (width > MAX_IMAGE_DIMENSION || height > MAX_IMAGE_DIMENSION) {
			float widthRatio = ((float) width) / MAX_IMAGE_DIMENSION;
			float heightRatio = ((float) height) / MAX_IMAGE_DIMENSION;
			float maxRatio = Math.max(widthRatio, heightRatio);
			return (int) maxRatio;
		} else {
			return 1;
		}

	}
}
