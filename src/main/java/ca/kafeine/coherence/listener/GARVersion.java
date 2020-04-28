package ca.kafeine.coherence.listener;

public class GARVersion implements GARVersionMBean {

	public String version;

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String getVersion() {
		return this.version;
	}



}
