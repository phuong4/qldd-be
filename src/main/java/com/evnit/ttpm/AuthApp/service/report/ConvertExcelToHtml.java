package com.evnit.ttpm.AuthApp.service.report;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertExcelToHtml {
	private final StringBuilder out = new StringBuilder(65536);
	private SimpleDateFormat sdf;
	private HSSFWorkbook book;
	private HSSFPalette palette;
	private FormulaEvaluator evaluator;
	private int colIndex;
	private int rowIndex, mergeStartCol, mergeEndCol, mergeStartRow, mergeEndRow;
	private final HashMap<String, CellRangeAddress> lstMerge = new HashMap<String, CellRangeAddress>();
	private final HashMap<Integer, List<Integer>> lstMergeRemove = new HashMap<Integer, List<Integer>>();
	private long widthTable = 0;

	// Row -> Column -> Pictures
	private final Map<Integer, Map<Short, List<HSSFPictureData>>> pix = new HashMap<Integer, Map<Short, List<HSSFPictureData>>>();

	/**
	 * Generates HTML from the InputStream of an Excel file. Generates sheet name in
	 * HTML h1 element.
	 *
	 * @param in InputStream of the Excel file.
	 * @throws IOException When POI cannot read from the input stream.
	 */
	public StringBuilder ConvertExcelToHtml(final InputStream in, boolean fixWidth) throws IOException {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (in == null) {
			book = null;
			palette = null;
			evaluator = null;
			return null;
		}
		book = new HSSFWorkbook(in);
		palette = book.getCustomPalette();
		evaluator = book.getCreationHelper().createFormulaEvaluator();
		for (int i = 0; i < book.getNumberOfSheets(); ++i) {
			table(book.getSheetAt(i), fixWidth, false);
		}
		return out;
	}

	public StringBuilder ConvertExcelToHtmlHomeView(final InputStream in, boolean fixWidth) throws IOException {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (in == null) {
			book = null;
			palette = null;
			evaluator = null;
			return null;
		}
		book = new HSSFWorkbook(in);
		palette = book.getCustomPalette();
		evaluator = book.getCreationHelper().createFormulaEvaluator();
		for (int i = 0; i < book.getNumberOfSheets(); ++i) {
			table(book.getSheetAt(i), fixWidth, true);
		}
		return out;
	}

	public StringBuilder ConvertExcelToHtml(final InputStream in, int iSheet, boolean fixWidth) throws IOException {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (in == null) {
			book = null;
			palette = null;
			evaluator = null;
			return null;
		}
		book = new HSSFWorkbook(in);
		palette = book.getCustomPalette();
		evaluator = book.getCreationHelper().createFormulaEvaluator();
		if (book.getNumberOfSheets() >= iSheet + 1) {
			table(book.getSheetAt(iSheet), fixWidth, false);
		}
		return out;
	}

	/**
	 * (Each Excel sheet produces an HTML table) Generates an HTML table with no
	 * cell, border spacing or padding.
	 *
	 * @param sheet The Excel sheet.
	 */
	private void table(final HSSFSheet sheet, boolean fixWidth, boolean homeView) {
		if (sheet == null) {
			return;
		}
		if (sheet.getDrawingPatriarch() != null) {
			final List<HSSFShape> shapes = sheet.getDrawingPatriarch().getChildren();
			for (int i = 0; i < shapes.size(); ++i) {
				if (shapes.get(i) instanceof HSSFPicture) {
					try {
						// Gain access to private field anchor.
						final HSSFShape pic = shapes.get(i);
						final Field f = HSSFShape.class.getDeclaredField("anchor");
						f.setAccessible(true);
						final HSSFClientAnchor anchor = (HSSFClientAnchor) f.get(pic);
						// Store picture cell row, column and picture data.
						if (!pix.containsKey(anchor.getRow1())) {
							pix.put(anchor.getRow1(), new HashMap<Short, List<HSSFPictureData>>());
						}
						if (!pix.get(anchor.getRow1()).containsKey(anchor.getCol1())) {
							pix.get(anchor.getRow1()).put(anchor.getCol1(), new ArrayList<HSSFPictureData>());
						}
						pix.get(anchor.getRow1()).get(anchor.getCol1())
								.add(book.getAllPictures().get(((HSSFPicture) pic).getPictureIndex()));
					} catch (final Exception e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		List<Integer> tempLst = new ArrayList<Integer>();
		List<Integer> tempLst2 = new ArrayList<Integer>();
		for (int i = 0; i < sheet.getNumMergedRegions(); ++i) {
			final CellRangeAddress merge = sheet.getMergedRegion(i);
			lstMerge.put(merge.getFirstRow() + ":" + merge.getFirstColumn(), merge);

			for (int n = merge.getFirstRow(); n <= merge.getLastRow(); n++) {
				tempLst = new ArrayList<Integer>();
				for (int m = merge.getFirstColumn(); m <= merge.getLastColumn(); m++) {
					tempLst.add(m);
				}
				if (lstMergeRemove.containsKey(n)) {
					tempLst2 = lstMergeRemove.get(n);
					for (int k = 0; k < tempLst.size(); k++) {
						tempLst2.add(tempLst.get(k));
					}
					lstMergeRemove.put(n, tempLst2);

				} else {
					lstMergeRemove.put(n, tempLst);
				}
			}

		}

		HSSFRow tempRow;
		tempRow = sheet.getRow(0);
		Double temp;
		for (int i = 0; i < tempRow.getLastCellNum(); ++i) {
			if (!tempRow.getSheet().isColumnHidden(i)) {
				if (!tempRow.getZeroHeight()) {
					if (tempRow.getSheet().getColumnWidth(i) != -1) {
						temp = tempRow.getSheet().getColumnWidth(i) / 400.0;
						widthTable = widthTable + Math.round(tempRow.getSheet().getColumnWidth(i) * 8 / 256)
								- temp.intValue();
					}
				}
			}
		}
		if (fixWidth) {
			out.append("<table cellspacing='0' style='border-spacing:0; border-collapse:collapse;width:" + widthTable
					+ "px'>\n");
		} else {
			out.append("<table cellspacing='0' style='border-spacing:0; border-collapse:collapse;width:100%'>\n");
		}
		for (rowIndex = 0; rowIndex < sheet.getPhysicalNumberOfRows(); ++rowIndex) {
			tr(sheet.getRow(rowIndex), homeView);
		}
		out.append("</table>\n");
	}

	/**
	 * (Each Excel sheet row becomes an HTML table row) Generates an HTML table row
	 * which has the same height as the Excel row.
	 *
	 * @param row The Excel row.
	 */
	private void tr(final HSSFRow row, boolean homeView) {
		if (row == null) {
			return;
		}

		out.append("<tr ");
		// Find merged cells in current row.

		out.append("style='");
		if (homeView) {
			if (row.getLastCellNum() <= 0) {
				out.append("display:none !important;'");
			}
		}
		if (row.getHeight() != -1) {
			out.append("height: ").append(Math.round(row.getHeight() / 20 * 1.33333)).append("px; ");
		}

		out.append("'>\n");
		for (colIndex = 0; colIndex < row.getLastCellNum(); ++colIndex) {
			if (!row.getSheet().isColumnHidden(colIndex)) {
				if (!row.getZeroHeight()) {

					td(row.getCell(colIndex), homeView);
				}
			}
		}
		out.append("</tr>\n");
	}

	/**
	 * (Each Excel sheet cell becomes an HTML table cell) Generates an HTML table
	 * cell which has the same font styles, alignments, colours and borders as the
	 * Excel cell.
	 *
	 * @param cell The Excel cell.
	 */
	private void td(final HSSFCell cell, boolean homeView) {
		int colspan = 1;
		int rowspan = 1;

		// Khong hien thu chu neu mau dinh dang trang
		boolean flagViewValue = true;
		// Xu ly truong hop o bat dau merge
		if (lstMerge.containsKey(rowIndex + ":" + colIndex)) {
			final CellRangeAddress tempCellRangeAddress = lstMerge.get(rowIndex + ":" + colIndex);
			colspan = tempCellRangeAddress.getLastColumn() - tempCellRangeAddress.getFirstColumn() + 1;
			rowspan = tempCellRangeAddress.getLastRow() - tempCellRangeAddress.getFirstRow() + 1;
		} else {
			if (lstMergeRemove.containsKey(rowIndex)) {
				if (lstMergeRemove.get(rowIndex).contains(colIndex)) {
					return;
				}
			}
		}
		out.append("<td ");
		if (colspan > 1) {
			out.append("colspan='").append(colspan).append("' ");
		}
		if (rowspan > 1) {
			out.append("rowspan='").append(rowspan).append("' ");
		}
		if (cell == null) {
			out.append("/>\n");
			return;
		}
		Double temp;
		out.append("style='");
		out.append("width:");
		if (cell.getSheet().getColumnWidth(colIndex) != -1) {
			temp = cell.getSheet().getColumnWidth(colIndex) / 400.0;
			out.append(Math.round(cell.getSheet().getColumnWidth(colIndex) * 8 / 256) - temp.intValue()).append("px;");
		}
		HSSFCellStyle style = cell.getCellStyle();
		// Text alignment
		switch (style.getAlignment()) {
		case CellStyle.ALIGN_LEFT:
			out.append("text-align: left; ");
			break;
		case CellStyle.ALIGN_RIGHT:
			out.append("text-align: right; ");
			break;
		case CellStyle.ALIGN_CENTER:
			out.append("text-align: center; ");
			break;
		default:
			break;
		}
		// Font style, size and weight
		final HSSFFont font = style.getFont(book);
		if (font.getBoldweight() == HSSFFont.BOLDWEIGHT_BOLD) {
			out.append("font-weight: bold; ");
		}
		if (font.getItalic()) {
			out.append("font-style: italic; ");
		}
		if (font.getUnderline() != HSSFFont.U_NONE) {
			out.append("text-decoration: underline; ");
		}
		out.append("font-size: ").append(Math.floor(font.getFontHeightInPoints() * 0.8)).append("pt; ");
		// Cell background and font colours
		final short[] backRGB = style.getFillForegroundColorColor().getTriplet();
		final HSSFColor foreColor = palette.getColor(font.getColor());
		if (!homeView) {
			if (foreColor != null) {
				final short[] foreRGB = foreColor.getTriplet();
				if (foreRGB[0] != 0 || foreRGB[1] != 0 || foreRGB[2] != 0) {
					out.append("color: rgb(").append(foreRGB[0]).append(',').append(foreRGB[1]).append(',')
							.append(foreRGB[2]).append(");");
					if (foreRGB[0] == 255 && foreRGB[1] == 255 && foreRGB[2] == 255) {
						flagViewValue = false;
					}
				}
			}
			if (backRGB[0] != 0 || backRGB[1] != 0 || backRGB[2] != 0) {
				out.append("background-color: rgb(").append(backRGB[0]).append(',').append(backRGB[1]).append(',')
						.append(backRGB[2]).append(");");
			}
		}
		// Border
		String borderBottomRGBStr = "";
		String borderTopRGBStr = "";
		String borderLeftRGBStr = "";
		String borderRightRGBStr = "";
		try {
			final HSSFColor borderBottomColor = palette.getColor(style.getBottomBorderColor());
			final short[] borderBottomRGB = borderBottomColor.getTriplet();
			// final HSSFColor borderTopColor = palette.getColor(style.getTopBorderColor());
			// final short[] borderTopColorRGB = borderTopColor.getTriplet();
			final HSSFColor borderLeftColor = palette.getColor(style.getLeftBorderColor());
			final short[] borderLeftColorRGB = borderLeftColor.getTriplet();
			final HSSFColor borderRightColor = palette.getColor(style.getRightBorderColor());
			final short[] borderRightColorRGB = borderRightColor.getTriplet();
			borderBottomRGBStr = "rgb(" + (borderBottomRGB[0]) + "," + (borderBottomRGB[1]) + "," + (borderBottomRGB[2])
					+ ") !important;";
			// borderTopRGBStr = "rgb(" + borderTopColorRGB[0] + "," + borderTopColorRGB[1]
			// + "," + borderTopColorRGB[2] + ") !important;";
			borderLeftRGBStr = "rgb(" + (borderLeftColorRGB[0]) + "," + (borderLeftColorRGB[1]) + ","
					+ (borderLeftColorRGB[2]) + ") !important;";
			borderRightRGBStr = "rgb(" + (borderRightColorRGB[0]) + "," + (borderRightColorRGB[1]) + ","
					+ (borderRightColorRGB[2]) + ") !important;";
		} catch (Exception e) {
		}

		if (style.getBorderTop() != HSSFCellStyle.BORDER_NONE) {

			if (!borderTopRGBStr.equals("") && !homeView) {
				out.append("border-top-style: solid;border-width:1px 1px 1px 1px;border-color:" + borderTopRGBStr);
			} else {
				out.append("border-top-style: solid;border-width:1px 1px 1px 1px;");

			}
		}
		if (style.getBorderRight() != HSSFCellStyle.BORDER_NONE) {

			if (!borderRightRGBStr.equals("") && !homeView) {
				out.append("border-right-style: solid;border-width:1px 1px 1px 1px;border-color:" + borderRightRGBStr);
			} else {
				out.append("border-right-style: solid;border-width:1px 1px 1px 1px;");
			}

		}
		if (style.getBorderBottom() != HSSFCellStyle.BORDER_NONE) {
			if (!borderBottomRGBStr.equals("") && !homeView) {
				out.append(
						"border-bottom-style: solid;border-width:1px 1px 1px 1px;border-color:" + borderBottomRGBStr);
			} else {
				out.append("border-bottom-style: solid;border-width:1px 1px 1px 1px;");
			}
		}
		if (style.getBorderLeft() != HSSFCellStyle.BORDER_NONE) {
			if (!borderLeftRGBStr.equals("") && !homeView) {
				out.append("border-left-style: solid;border-width:1px 1px 1px 1px;border-color:" + borderLeftRGBStr);
			} else {
				out.append("border-left-style: solid;border-width:1px 1px 1px 1px;");
			}
		}
		out.append(";padding-left: 5px!important;padding-right: 5px!important'>");
		String val = "";
		String valTemp = "";
		try {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_STRING:
				val = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				// POI does not distinguish between integer and double, thus:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					String dateFmt = cell.getCellStyle().getDataFormatString();
					val = new CellDateFormatter(dateFmt).format(cell.getDateCellValue());
				} else {
					final double original = cell.getNumericCellValue(), rounded = Math.round(original);
					if (Math.abs(rounded - original) < 0.00000000000000001) {
						DecimalFormat myFormatter = new DecimalFormat("###,###,###,###,###");
						val = String.valueOf(myFormatter.format(rounded));
					} else {
						DecimalFormat myFormatter = new DecimalFormat("###,###,###,###,##0.00");
						val = String.valueOf(myFormatter.format(original));
					}
					// val = val.replace(".", "/");
					// val = val.replace(",", ".");
					// val = val.replace("/", ",");
				}
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				if (flagViewValue) {
					final CellValue cv = evaluator.evaluate(cell);
					switch (cv.getCellType()) {
					case Cell.CELL_TYPE_BOOLEAN:
						out.append(cv.getBooleanValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:

						final double original = cv.getNumberValue(), rounded = Math.round(original);
						if (Math.abs(rounded - original) < 0.00000000000000001) {
							DecimalFormat myFormatter = new DecimalFormat("###,###,###,###,###");
							valTemp = String.valueOf(myFormatter.format(rounded));
						} else {
							DecimalFormat myFormatter = new DecimalFormat("###,###,###,###,##0.00");
							valTemp = String.valueOf(myFormatter.format(original));
						}
						// valTemp = valTemp.replace(".", "/");
						// valTemp = valTemp.replace(",", ".");
						// valTemp = valTemp.replace("/", ",");
						out.append(valTemp);
						break;
					case Cell.CELL_TYPE_STRING:
						out.append(cv.getStringValue());
						break;
					case Cell.CELL_TYPE_BLANK:
						break;
					case Cell.CELL_TYPE_ERROR:
						out.append(cv.getErrorValue());
						break;
					default:
						break;
					}
				}
				break;
			default:
				// Neither string or number? Could be a date.
				try {
					val = sdf.format(cell.getDateCellValue());
				} catch (final Exception e1) {
				}
			}
		} catch (final Exception e) {
			val = e.getMessage();
		}
		if ("null".equals(val)) {
			val = "";
		}
		if (pix.containsKey(rowIndex)) {
			if (pix.get(rowIndex).containsKey(colIndex)) {
				for (final HSSFPictureData pic : pix.get(rowIndex).get(colIndex)) {
					out.append("<img alt='Image in Excel sheet' src='data:");
					out.append(pic.getMimeType());
					out.append(";base64,");
					out.append(new String(Base64.encodeBase64(pic.getData()), StandardCharsets.US_ASCII));
					out.append("'/>");
				}
			}
		}
		if (flagViewValue) {
			val = val.replace("~", "</br>");
			out.append(val);
		}
		out.append("</td>\n");
	}

	public String getHTML() {
		return out.toString();
	}
}
