package br.com.ulbra.tcc.common.vo.dataquality;

/**
 * The DataQualityReportVO class
 * 
 * @author Paulo Pacheco
 *
 */
public class DataQualityReportVO {

	private String reportZipFolderPath;
	private String reportName;
	private boolean reportAvailable;

	public String getReportZipFolderPath() {
		return reportZipFolderPath;
	}

	public void setReportZipFolderPath(String reportZipFolderPath) {
		this.reportZipFolderPath = reportZipFolderPath;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public boolean isReportAvailable() {
		return reportAvailable;
	}

	public void setReportAvailable(boolean reportAvailable) {
		this.reportAvailable = reportAvailable;
	}
}
