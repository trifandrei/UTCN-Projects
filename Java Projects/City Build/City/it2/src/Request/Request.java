package Request;

import java.io.Serializable;

public class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	private final int port = 9000;
	private final String host = "localhost";

	private String requestType;
	private String data;
	private String dataType;

	private int dst;
	private int lt;

	public Request(String req, String datatp) {
		this.dataType = datatp;
		this.requestType = req;

	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getData() {
		return data;
	}

	public int getDst() {
		return dst;
	}

	public void setDst(int dst) {
		this.dst = dst;
	}

	public int getLt() {
		return lt;
	}

	public void setLt(int lt) {
		this.lt = lt;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getPort() {
		return port;
	}

	public String getHost() {
		return host;
	}

}
