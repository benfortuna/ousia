Groovy bindings for Substance and other great third-party Swing technologies

# Download #

The latest snapshot builds are available here:

[http://build.mnode.org/snapshots/org/mnode/ousia/ousia/](http://build.mnode.org/snapshots/org/mnode/ousia/ousia/)

# Maven #

```
<project>

	<dependencies>
		<dependency>
			<groupId>org.mnode.ousia</groupId>
			<artifactId>ousia</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
	</dependencies>

	<repositories>
		<repository>
	        <id>micronode-snapshots</id>
	        <url>http://build.mnode.org/snapshots</url>
	        <releases>
	           <enabled>false</enabled>
	        </releases>
	        <snapshots>
	          <enabled>true</enabled>
	        </snapshots>        
	     </repository>
	</repositories>
</project>
```