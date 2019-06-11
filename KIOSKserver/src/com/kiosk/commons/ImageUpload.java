package com.kiosk.commons;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

public class ImageUpload {
	public static String encoding(File file) {
	
		String base64Img=null;
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream(1000);
			BufferedImage img=ImageIO.read(file);
			ImageIO.write(img, "jpg", baos);
			base64Img=Base64.getEncoder().encodeToString(baos.toByteArray());
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base64Img;
	}
	public static void decoding(String base64Image,String file_name) {
		try {
			byte[] serializedImage = Base64.getDecoder().decode(base64Image);
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(serializedImage));
			// 역직렬화된 Member 객체를 읽어온다.
			String path = System.getProperty("user.dir");
			System.out.println(path);
			File file = new File(path + "/menu_res/" + file_name);
			ImageIO.write(img, "jpg", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static BufferedImage createImg(String base64Image) {
		BufferedImage img=null;
		try {
			byte[] serializedImage = Base64.getDecoder().decode(base64Image);
			img = ImageIO.read(new ByteArrayInputStream(serializedImage));
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
}
