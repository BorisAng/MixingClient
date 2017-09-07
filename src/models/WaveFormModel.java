package models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JOptionPane;

/**
 * REFERENCES FROM
 * http://stackoverflow.com/questions/26452026/how-to-make-waveform-rendering-
 * more-interesting
 * 
 * Model class for the WaveFormPanelview. Holds the data of the wave form.
 * 
 * @author bba278 + REFERENCES
 */
public class WaveFormModel implements WaveFormModelInt {

	/*
	 * Fields
	 */
	private BufferedImage img;
	private int boxWidth = 4;
	private Dimension size = new Dimension(getBoxWidth() == 1 ? 512 : 1000, 160);

	private String songDirectoryName;
	private float[] samples;
	private Graphics2D g2d;

	public WaveFormModel() {
	}

	/**
	 * Method to draw the extracted audio data
	 */
	@Override
	public void drawWaveForm(float[] samples) {

	
		this.setG2d(getImg().createGraphics());

		int numSubsets = getSize().width / getBoxWidth();
		int subsetLength = samples.length / numSubsets;

		float[] subsets = new float[numSubsets];

		// find average(abs) of each box subset
		int s = 0;
		for (int i = 0; i < subsets.length; i++) {

			double sum = 0;
			for (int k = 0; k < subsetLength; k++) {
				sum += Math.abs(samples[s++]);
			}

			subsets[i] = (float) (sum / subsetLength);
		}

		// find the peak so the waveform can be normalized
		// to the height of the image
		float normal = 0;
		for (float sample : subsets) {
			if (sample > normal)
				normal = sample;
		}

		// normalize and scale
		normal = 32768.0f / normal;
		for (int i = 0; i < subsets.length; i++) {
			subsets[i] *= normal;
			subsets[i] = (subsets[i] / 32768.0f) * (getSize().height / 2);
		}

		getG2d().setColor(Color.GRAY);

		// convert to image coords and do actual drawing
		for (int i = 0; i < subsets.length; i++) {
			int sample = (int) subsets[i];

			int posY = (getSize().height / 2) - sample;
			int negY = (getSize().height / 2) + sample;

			int x = i * getBoxWidth();

			if (getBoxWidth() == 1) {
				getG2d().drawLine(x, posY, x, negY);
			} else {
				getG2d().setColor(Color.GRAY);
				getG2d().fillRect(x + 1, posY + 1, getBoxWidth() - 1, negY - posY - 1);
				getG2d().setColor(Color.DARK_GRAY);
				getG2d().drawRect(x, posY, getBoxWidth(), negY - posY);
			}
		}

		getG2d().dispose();

	}

	/**
	 * Method to extract audio data from an MP3
	 */
	@Override
	public void getMP3Amplitude() {

		System.out.println("Song Directory name is " + songDirectoryName);

		File file = new File(this.getSongDirectoryName());
		try {

			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			AudioInputStream din = null;
			AudioFormat baseFormat = audioInputStream.getFormat();

			AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
					baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);

			din = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream);

			System.out.println(din.getFormat());

			boolean big = decodedFormat.isBigEndian();
			int chans = decodedFormat.getChannels();
			System.out.println("Number of channels " + chans);
			int bits = decodedFormat.getSampleSizeInBits();
			int bytes = bits + 7 >> 3;
			System.out.println("Bytes " + bytes);

			int frameLength = (int) din.getFrameLength();
			System.out.println("Frame Length is: " + frameLength);

			int bufferLength = chans * bytes * 1024;
			System.out.println("The Buffer Length is " + bufferLength);

			setSamples(new float[29000000]);

			byte[] buf = new byte[bufferLength];

			int i = 0;
			int bRead;
			while ((bRead = din.read(buf)) > -1) {

				for (int b = 0; b < bRead;) {
					double sum = 0;

					// (sums to mono if multiple channels)
					for (int c = 0; c < chans; c++) {
						if (bytes == 1) {
							sum = sum + (buf[b++] << 8);

						} else {
							int sample = 0;

							// (quantizes to 16-bit)
							if (big) {
								sample = sample | (buf[b++] & 0xFF) << 8;
								sample = sample | (buf[b++] & 0xFF);
								b += bytes - 2;
							} else {
								b += bytes - 2;
								sample = sample | (buf[b++] & 0xFF);
								sample = sample | (buf[b++] & 0xFF) << 8;
							}

							final int sign = 1 << 15;
							final int mask = -1 << 16;
							if ((sample & sign) == sign) {
								sample = sample | mask;
							}

							sum += sample;
						}
					}

					this.getSamples()[i++] = (float) (sum / chans);

				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
			problem(e);
			return;
		}

		if (this.getImg() == null) {
			this.setImg(new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB));
		}
		
		

	}

	private void problem(Object msg) {
		JOptionPane.showMessageDialog(null, String.valueOf(msg));
	}

	/*
	 * GETTERS AND SETTERS FROM THE INTERFACE
	 */

	@Override
	public String getSongDirectoryName() {
		return songDirectoryName;
	}

	@Override
	public void setSongDirectoryName(String songDirectoryName) {
		this.songDirectoryName = songDirectoryName;
	}

	@Override
	public BufferedImage getImg() {
		return img;
	}

	@Override
	public void setImg(BufferedImage img) {
		this.img = img;
	}

	@Override
	public Dimension getSize() {
		return size;
	}

	@Override
	public void setSize(Dimension size) {
		this.size = size;
	}

	@Override
	public int getBoxWidth() {
		return boxWidth;
	}

	@Override
	public void setBoxWidth(int boxWidth) {
		this.boxWidth = boxWidth;
	}

	@Override
	public float[] getSamples() {
		return samples;
	}

	@Override
	public void setSamples(float[] samples) {
		this.samples = samples;
	}

	@Override
	public Graphics2D getG2d() {
		return g2d;
	}

	@Override
	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

}
