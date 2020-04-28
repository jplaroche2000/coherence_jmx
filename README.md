# Load a custom MBean to expose GAR version
This project describes the different parts required to expose the version of a GAR (set in the META-INF/MANIFEST.MF file of the gar) in Coherence MBeans

## Create the custom MBean

Create a class and an interface with a getter/setter for a String version property.  
These classes need to be deployed in the libs of the GAR.

[GARVersion.java](https://github.com/jplaroche2000/coherence_jmx/blob/master/src/main/java/ca/kafeine/coherence/listener/GARVersion.java) and [GARVersionMBean.java](https://github.com/jplaroche2000/coherence_jmx/blob/master/src/main/java/ca/kafeine/coherence/listener/GARVersionMBean.java)


## Create the GAR life cycle listener

- To extract the Gar-Version manifest main attribute from the MANIFEST.MF file
- To register the MBean

This class need to be deployed in the libs of the GAR.

[LifeCycleListener.java](https://github.com/jplaroche2000/coherence_jmx/blob/master/src/main/java/ca/kafeine/coherence/listener/LifeCycleListener.java)


## Declare the listener in coherence-application.xml

```
  <application-lifecycle-listener>
        <class-name>ca.kafeine.coherence.listener.LifeCycleListener</class-name>
  </application-lifecycle-listener>
```
See 
[coherence-application.xml](https://github.com/jplaroche2000/coherence_jmx/blob/master/src/main/resources/META-INF/coherence-application.xml)


## Inject custom main attribute (Gar-Version) in GAR's MANIFEST file

- Edit the pom of the gar project
```
	<build>
		<plugins>      
      ...
			<!-- inject in MANIFEST custom Gar-Version attribute -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-jar-plugin</artifactId>
				<version>3.2.0</version>
				<configuration>
					<archive>
						<manifestEntries>
							<Gar-Version>${project.version}</Gar-Version>
						</manifestEntries>
					</archive>
				</configuration>
			</plugin>
      ...
		</plugins>
	</build>
```
