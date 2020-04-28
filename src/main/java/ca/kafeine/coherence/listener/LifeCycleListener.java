package ca.kafeine.coherence.listener;

import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import com.tangosol.application.Context;
import com.tangosol.net.CacheFactory;
import com.tangosol.net.management.Registry;

public class LifeCycleListener implements com.tangosol.application.LifecycleListener {

	@Override
	public void postStart(Context ctxt) {

		String applicationName = ctxt.getExtendedContext().getApplicationName();

		CacheFactory.log("LifeCycleListener.postStart", CacheFactory.LOG_DEBUG);

		String version = "unknown";

		// Gather the Gar-Version main attribute from the MANIFEST

		try {
			Enumeration<URL> resources = this.getClass().getClassLoader().getResources(JarFile.MANIFEST_NAME);
			while (resources.hasMoreElements()) {

				URL url = resources.nextElement();

				if (url.toString().matches(".*/" + applicationName + "/\\w+/META-INF/MANIFEST.MF")) {

					CacheFactory.log("Manifest URL: " + url, CacheFactory.LOG_DEBUG);

					Manifest manifest = new Manifest(url.openStream());

					Attributes attributes = manifest.getMainAttributes();

					version = manifest.getMainAttributes().getValue("Gar-Version");

					CacheFactory.log("manifest.getAttributes(\"Gar-Version\"): " + attributes.getValue("Gar-Version"),
							CacheFactory.LOG_DEBUG);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Register the MBean

		try {

			Registry registry = CacheFactory.ensureCluster().getManagement();
			GARVersionMBean bean = new GARVersion();
			bean.setVersion(version);
			String sName = registry.ensureGlobalName("type=Version,name=" + applicationName);
			registry.register(sName, bean);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void postStop(Context ctxt) {
		CacheFactory.log("LifeCycleListener.postStop", CacheFactory.LOG_DEBUG);
	}

	@Override
	public void preStart(Context ctxt) {
		CacheFactory.log("LifeCycleListener.preStart", CacheFactory.LOG_DEBUG);
	}

	@Override
	public void preStop(Context ctxt) {
		CacheFactory.log("LifeCycleListener.preStop", CacheFactory.LOG_DEBUG);
	}

}