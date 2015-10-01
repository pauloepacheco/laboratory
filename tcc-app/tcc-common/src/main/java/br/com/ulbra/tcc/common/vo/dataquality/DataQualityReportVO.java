package br.com.ulbra.tcc.common.vo.dataquality;

/**
 * The DataQualityReportVO class
 * 
 * @author Paulo Pacheco
 *
 */
public class DataQualityReportVO {

	private String reportId;
	private String reportName;
	private boolean reportAvailable;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
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
