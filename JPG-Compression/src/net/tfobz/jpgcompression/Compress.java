package net.tfobz.jpgcompression;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class Compress implements Callable<BufferedImage> {

	private BufferedImage image;
	private double quality;

	public Compress(BufferedImage image, double quality) {
		this.image = image;
		this.quality = quality;
	}

	public double getRes() {
		return quality;
	}

	@Override
	public BufferedImage call() throws Exception {
		return JPGImageCompress.compressImage(image, quality);
	}
}