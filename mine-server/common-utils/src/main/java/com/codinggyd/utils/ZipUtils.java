package com.codinggyd.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ZipUtils {
	private static final int BUFFER_SIZE = 4096;

	/**
	 * 
	 * 使用gzip进行压缩
	 */
	public static byte[] gzip(byte[] data) {
		if (data == null || data.length == 0) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return out.toByteArray();
	}

	/**
	 * 
	 * 使用gzip进行压缩
	 */
	public static byte[] gzip1(String primStr) {
		if (primStr == null || primStr.length() == 0) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(primStr.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return out.toByteArray();
	}

	/**
	 *
	 * <p>
	 * Description:使用gzip进行解压缩
	 * </p>
	 * 
	 * @param compressedStr
	 * @return
	 */
	public static byte[] gunzip(byte[] compressed) {
		if (compressed == null) {
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		byte[] decompressed = null;
		try {
			in = new ByteArrayInputStream(compressed);
			
			out = new ByteArrayOutputStream();
			
			deCompress(in,out);
			
			decompressed = out.toByteArray();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}

		return decompressed;
	}
	/**
	 *
	 * <p>
	 * Description:使用gzip进行解压缩
	 * </p>
	 * 
	 * @param compressedStr
	 * @return
	 */
	public static byte[] ungzip(byte[] compressed) {
		if (compressed == null) {
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		byte[] decompressed = null;
		try {
			in = new ByteArrayInputStream(compressed);
			
			out = new ByteArrayOutputStream();
			
			deCompress(in,out);
			
			decompressed = out.toByteArray();
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}

		return decompressed;
	}

	public static void deCompress(InputStream is, OutputStream os) throws IOException {
		GZIPInputStream gzip = null;
		try {
			gzip = new GZIPInputStream(is);
			int count;
			byte data[] = new byte[BUFFER_SIZE];
			while ((count = gzip.read(data, 0, BUFFER_SIZE)) != -1) {
				os.write(data, 0, count);
			}
			gzip.close();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
