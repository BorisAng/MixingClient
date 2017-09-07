package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import models.WaveFormModelInt;
import models.WaveFormModel;

/**
 * A view class for the WaveForm Panel
 * 
 * @author bba278
 *
 */
public class WaveFormPanelView extends JPanel {

	/*
	 * Fields
	 */
	private static final long serialVersionUID = 1L;
	private WaveFormModelInt waveFormModel;

	private boolean clear = false;

	/**
	 * Constructor to create the WaveForm Panel. It also creates the
	 * WaveFormModel
	 */
	public WaveFormPanelView() {

		super();
		this.setWaveFormModel(new WaveFormModel());

		this.setBackground(Color.WHITE);
		this.setPreferredSize(
				new Dimension(this.getWaveFormModel().getSize().width, this.getWaveFormModel().getSize().height));

	}

	/**
	 * Overrides JPanel's paintComponent so that we can draw the wave form
	 * BufferedImage to this Panel
	 */
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (!clear) {

			if (this.getWaveFormModel().getImg() != null) {
				g.drawImage(this.getWaveFormModel().getImg(), 1, 1, this.getWaveFormModel().getImg().getWidth(),
						this.getWaveFormModel().getImg().getHeight(), null);

			}
		} else {
			// Draw and fill a rectangle of same size as the panel
			Rectangle r = this.getBounds();
			g.setColor(this.getBackground());
			g.fillRect(r.x, r.y, r.width, r.height);
			clear = false;
		}
	};

	/*
	 * GETTERS AND SETTERS
	 */

	public void setClear(boolean clearFlag) {
		clear = clearFlag;
	}



	public WaveFormModelInt getWaveFormModel() {
		return waveFormModel;
	}

	public void setWaveFormModel(WaveFormModelInt waveFormModel) {
		this.waveFormModel = waveFormModel;
	}

}
