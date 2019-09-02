package com.qkby.jchart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

import sun.misc.BASE64Encoder;

/**
 * 提供jfreechart的多种图形调用，主要满足report中的图形 多线图，每种线不同的颜色； 饼图，3D效果； 柱状图，3D效果。
 * 
 * @author liz
 * 
 */
public class ReportChart {
	private static int[] sizes;

	public static JFreeChart getPieChart3D(DefaultPieDataset dataset,
			String title) {
		JFreeChart chart = null;
		chart = ChartFactory
				.createPieChart3D(title, dataset, true, true, false);
		// 重新设置图标标题，改变字体
		chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC, 22)));
		// 设置背景为白色
		// chart.setBackgroundPaint(Color.white);
		// 取得统计图标的第一个图例
		LegendTitle legend = chart.getLegend();
		// 修改图例的字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 10));
		legend.setPosition(RectangleEdge.RIGHT);
//		legend.setHorizontalAlignment(HorizontalAlignment.LEFT);
		//legend.setLegendItemLayOut(LegendTitle.VERTICAL);

//		legend.setPosition(RectangleEdge.BOTTOM);
		//设置图例子项的布局方式(需要更新jfreechart的jar包)
		//legend.setLegendItemLayOut(LegendTitle.VERTICAL);
		//legend.setPadding(0d, 100d, 0d, 100d);
		// 获得饼图的Plot对象
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// 设置饼图各部分的标签字体
		plot.setLabelFont(new Font("宋体", Font.BOLD, 12));
		// 设定背景透明度（0-1.0之间）
		plot.setBackgroundAlpha(0.0f);
		// 设定前景透明度（0-1.0之间）
		plot.setForegroundAlpha(1f);
		plot.setBaseSectionPaint(Color.BLACK);
		// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
		// plot.setLabelGenerator(new
		// StandardPieSectionLabelGenerator("{0}：{1}({2})",
		// NumberFormat.getNumberInstance(),new DecimalFormat("0%")));
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}({2})",
				NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
		// 设置标签长度
		plot.setMaximumLabelWidth(0.2);
		// 设置饼图为圆
		plot.setCircular(true);
		// 设置标签和图的距离
		// plot.setLabelGap(0.05D);
		// 设置边框为白色
		plot.setOutlinePaint(Color.WHITE);
		return chart;
	}
	
	public static JFreeChart getPieChart3D2(DefaultPieDataset dataset,
			String title) {
		JFreeChart chart = null;
		chart = ChartFactory
				.createPieChart3D(title, dataset, true, true, false);
		// 重新设置图标标题，改变字体
		chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC, 22)));
		// 设置背景为白色
		// chart.setBackgroundPaint(Color.white);
		// 取得统计图标的第一个图例
		LegendTitle legend = chart.getLegend();
		// 修改图例的字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 10));
		legend.setPosition(RectangleEdge.BOTTOM);
//		legend.setHorizontalAlignment(HorizontalAlignment.LEFT);
		//legend.setLegendItemLayOut(LegendTitle.VERTICAL);

		// 获得饼图的Plot对象
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// 设置饼图各部分的标签字体
		plot.setLabelFont(new Font("宋体", Font.BOLD, 12));
		// 设定背景透明度（0-1.0之间）
		plot.setBackgroundAlpha(0.0f);
		// 设定前景透明度（0-1.0之间）
		plot.setForegroundAlpha(1f);
		plot.setBaseSectionPaint(Color.BLACK);
		// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
		// plot.setLabelGenerator(new
		// StandardPieSectionLabelGenerator("{0}：{1}({2})",
		// NumberFormat.getNumberInstance(),new DecimalFormat("0%")));
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{1}({2})",
				NumberFormat.getNumberInstance(), new DecimalFormat("0%")));
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}"));
		// 设置标签长度
		plot.setMaximumLabelWidth(0.2);
		// 设置饼图为圆
		plot.setCircular(true);
		// 设置标签和图的距离
		// plot.setLabelGap(0.05D);
		// 设置边框为白色
		plot.setOutlinePaint(Color.WHITE);
		return chart;
	}

	public static JFreeChart getBarChart3D(CategoryDataset dataset,
			String xAxis, String yAxis, String title) {
		JFreeChart chart = null;
		chart = ChartFactory.createBarChart3D(title, xAxis, yAxis, dataset,
				PlotOrientation.VERTICAL, true, true, false);
		// 设置背景为白色
		// chart.setBackgroundPaint(Color.white);
		chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC , 22)));
		// 取得统计图标的第一个图例
		LegendTitle legend = chart.getLegend(0);
		// 修改图例的字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 16));
		// 获得饼图的Plot对象
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);
		CategoryAxis domainAxis = plot.getDomainAxis();// 柱状图的x轴
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));// 设置x轴坐标上的字体

		ValueAxis valueAxis = plot.getRangeAxis();
		valueAxis.setAutoTickUnitSelection(false); // 设置Y轴的显示的跨度为整数
		valueAxis.setLabelFont(new Font("黑体",Font.PLAIN,15));//设置y轴的字体

		BarRenderer renderer = new BarRenderer();
		renderer.setIncludeBaseInRange(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 12));
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));// 数值显示位置
		plot.setRenderer(renderer);
		plot.setForegroundAlpha(1.0f);// 设置柱的透明度

		return chart;
	}

	/**
	 * 可以设置y轴间距的3D饼图方法
	 * 
	 * @param dataset
	 * @param xAxis
	 * @param yAxis
	 * @param title
	 * @param size
	 *            间距
	 * @return
	 */
	public static JFreeChart getBarChart3D(CategoryDataset dataset,
			String xAxis, String yAxis, String title, Double size) {
		JFreeChart chart = null;
		chart = ChartFactory.createBarChart3D(title, xAxis, yAxis, dataset,
				PlotOrientation.VERTICAL, true, true, false);
		// 设置背景为白色
		// chart.setBackgroundPaint(Color.white);
		chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC , 22)));
		// 取得统计图标的第一个图例
		LegendTitle legend = chart.getLegend(0);
		// 修改图例的字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 16));
		// 获得饼图的Plot对象
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.WHITE);
		plot.setRangeGridlinePaint(Color.BLACK);
		CategoryAxis domainAxis = plot.getDomainAxis();// 柱状图的x轴
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));// 设置x轴坐标上的字体

		ValueAxis valueAxis = plot.getRangeAxis();
		valueAxis.setAutoTickUnitSelection(false); // 设置Y轴的显示的跨度为整数
		valueAxis.setLabelFont(new Font("黑体",Font.PLAIN,15));//设置y轴的字体

		NumberAxis axis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
		axis.setTickUnit(new NumberTickUnit(size));// 1为一个间隔单位

		BarRenderer renderer = new BarRenderer();
		renderer.setIncludeBaseInRange(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelFont(new Font("宋体", Font.BOLD, 12));
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));// 数值显示位置
		plot.setRenderer(renderer);
		plot.setForegroundAlpha(1.0f);// 设置柱的透明度

		return chart;
	}

	public static JFreeChart getLineChart(CategoryDataset dataset,
			String xAxis, String yAxis, String title) {
		JFreeChart chart = null;
		chart = ChartFactory.createLineChart(title, xAxis, yAxis, dataset,
				PlotOrientation.VERTICAL, true, true, false);
		chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC , 22)));
		chart.setBackgroundPaint(Color.WHITE);
		CategoryPlot cplot = chart.getCategoryPlot();
		cplot.setBackgroundPaint(Color.WHITE);
		cplot.setRangeGridlinePaint(Color.BLACK);
		CategoryAxis domainAxis = cplot.getDomainAxis();// 柱状图的x轴
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 15));// 设置x轴坐标上的字体

		NumberAxis numberaxis = (NumberAxis) cplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); // 关键就是这句

		CategoryItemRenderer render = (CategoryItemRenderer) cplot
				.getRenderer();
		render.setSeriesStroke(0, new BasicStroke(2.0F));
		render.setSeriesStroke(1, new BasicStroke(2.0F));
		render.setSeriesStroke(2, new BasicStroke(2.0F));
		// render.setBaseSeriesVisible(true);

		// 取得统计图标的第一个图例
		LegendTitle legend = chart.getLegend(0);
		// 修改图例的字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 16));
		ValueAxis valueAxis = cplot.getRangeAxis();
		// 设置Y轴的最小值
		valueAxis.setLowerBound(0);

		return chart;
	}

	public static JFreeChart getLineChart2(CategoryDataset dataset,
			String xAxis, String yAxis, String title) {

		JFreeChart chart = null;
		chart = ChartFactory.createLineChart(title, xAxis, yAxis, dataset,
				PlotOrientation.VERTICAL, true, true, false);
		chart.setTitle(new TextTitle(title, new Font("黑体", Font.ITALIC , 22)));
		CategoryPlot cplot = chart.getCategoryPlot();
		cplot.setBackgroundPaint(Color.WHITE);
		cplot.setRangeGridlinePaint(Color.BLACK);
		// 取得统计图标的第一个图例
		LegendTitle legend = chart.getLegend(0);
		// 修改图例的字体
		legend.setItemFont(new Font("宋体", Font.BOLD, 16));
		ValueAxis valueAxis = chart.getCategoryPlot().getRangeAxis();
		// 设置Y轴的最小值
		valueAxis.setLowerBound(0);
		// 设置Y轴的最大值
		valueAxis.setUpperBound(100);
		// 自动设置数据轴数据范围时数据范围的最小跨度
		valueAxis.setAutoRangeMinimumSize(10);

		CategoryItemRenderer render = (CategoryItemRenderer) cplot
				.getRenderer();
		render.setSeriesStroke(0, new BasicStroke(2.0F));
		render.setSeriesStroke(1, new BasicStroke(2.0F));
		render.setSeriesStroke(2, new BasicStroke(2.0F));

		chart.setBackgroundPaint(Color.WHITE);
		return chart;
	}

	public static String getBase64ChartString(JFreeChart jchart, int width,
			int height) {
		BASE64Encoder encoder = new BASE64Encoder();
		try {
			return encoder.encode(ChartUtilities.encodeAsPNG(jchart
					.createBufferedImage(width, height)));
		} catch (IOException e) {
			return "";
		}
	}

	/**
	 * 传入Y轴上的最大坐标，获取Y轴间距
	 * 
	 * @param size 如果是整的，传入参数是加上小数 88-->88.0
	 * @return 返回合适的间距
	 */
	public static double getYAsisItervalValue(double size) {
		int index=Math.round((float)(Math.round(size/10))/5);
		if(index>0){
			return 5*index;
		}else{
			return 1;
		}
	}

	public static void main(String[] args) {
//		System.out.println(getYAsisItervalValue(8329682));
		/*
		DefaultPieDataset data = new DefaultPieDataset();
		data.setValue("高危", 10.0D);
		data.setValue("中危", 15D);
		data.setValue("低危", 75D);

		File file = new File("D://test.JPEG");
		try {
			ChartUtilities.saveChartAsJPEG(file, getPieChart3D(data, "测试"),
					500, 400);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// row keys...
		String series1 = "First";
		String series2 = "Second";
		String series3 = "Third";

		// column keys...
		String category1 = "部门1";
		String category2 = "部门2";
		String category3 = "部门3";
		String category4 = "部门4";
		String category5 = "部门5";

		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(1.0, series1, category1);
		dataset.addValue(4.0, series1, category2);
		dataset.addValue(3.0, series1, category3);
		dataset.addValue(5.0, series1, category4);
		dataset.addValue(5.0, series1, category5);

		dataset.addValue(5.0, series2, category1);
		dataset.addValue(7.0, series2, category2);
		dataset.addValue(6.0, series2, category3);
		dataset.addValue(8.0, series2, category4);
		dataset.addValue(4.0, series2, category5);

		dataset.addValue(4.0, series3, category1);
		dataset.addValue(3.0, series3, category2);
		dataset.addValue(2.0, series3, category3);
		dataset.addValue(3.0, series3, category4);
		dataset.addValue(6.0, series3, category5);

		File file1 = new File("D://test11.JPEG");
		try {
			ChartUtilities.saveChartAsJPEG(file1,
					getBarChart3D(dataset, "", "", ""), 500, 400);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	*/}

}
