package net.daniellechner.simulation;

import java.io.IOException;
import java.util.ArrayList;
import org.knowm.xchart.VectorGraphicsEncoder;
import org.knowm.xchart.VectorGraphicsEncoder.VectorGraphicsFormat;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;

/**
 * 
 * @author Daniel Lechner
 *
 */
public class Chart implements ExampleChart<XYChart> {

	private ArrayList<Integer> deathCharX;
	private ArrayList<Integer> deathCharY;
	private ArrayList<Integer> infectionCharX;
	private ArrayList<Integer> infectionCharY;
	private XYChart chart;

	public Chart(ArrayList<Integer> deathCharX, ArrayList<Integer> deathCharY, ArrayList<Integer> infectionCharX,
			ArrayList<Integer> infectionCharY) {
		this.infectionCharX = infectionCharX;
		this.infectionCharY = infectionCharY;
		this.deathCharX = deathCharX;
		this.deathCharY = deathCharY;
	}

	/**
	 * returns the new Chart
	 */
	public XYChart getChart() {

		chart = new XYChartBuilder().width(1000).height(750).title(getClass().getSimpleName()).xAxisTitle("Days")
				.yAxisTitle("Patients").theme(ChartTheme.Matlab).build();
		chart.addInfoContent("test");

		chart.getStyler().setLegendPosition(LegendPosition.InsideNE);
		chart.getStyler().setAxisTitlesVisible(true);
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);

		chart.addSeries("Deaths", deathCharX, deathCharY);
		chart.addSeries("Infections", infectionCharX, infectionCharY);

		return chart;
	}

	/**
	 * Saves the Chart as an Image
	 * 
	 * @param path Path
	 * @throws IOException
	 */
	public void safe(String path) throws IOException {
		VectorGraphicsEncoder.saveVectorGraphic(chart, path, VectorGraphicsFormat.PDF);
	}
}