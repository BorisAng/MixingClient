package models;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public interface WaveFormModelInt {

	void drawWaveForm(float[] samples);

	void getMP3Amplitude();

	String getSongDirectoryName();

	void setSongDirectoryName(String songDirectoryName);

	BufferedImage getImg();

	void setImg(BufferedImage img);

	Dimension getSize();

	void setSize(Dimension size);

	int getBoxWidth();

	void setBoxWidth(int boxWidth);

	float[] getSamples();

	void setSamples(float[] samples);

	Graphics2D getG2d();

	void setG2d(Graphics2D g2d);
}