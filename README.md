# Just a experiment related to **Mutation Testing**

- For this purpose we are using Pitest with Junit tests

## Some important stuff
### To run from the command line:
```
mvn org.pitest:pitest-maven:mutationCoverage
```
This will output an html report to **target/pit-reports/YYYYMMDDHHMI**.

The following command speeds-up the analysis.
```
mvn -DwithHistory org.pitest:pitest-maven:mutationCoverage
```

### To run only specific classes:
```
<plugin>
  <groupId>org.pitest</groupId>
  <artifactId>pitest-maven</artifactId>
  <version>LATEST</version>
  <configuration>
      <targetClasses>
        <param>com.your.package.root.want.to.mutate*</param>
      </targetClasses>
      <targetTests>
        <param>com.your.package.root*</param>
      </targetTests>
  </configuration>
</plugin>
```

