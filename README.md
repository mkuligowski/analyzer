# analyzer

usage:
```shell script
Usage: analyzer [-C] [-S=<stopWords>] [-F=<files>]...
Count words and chars from text files
  -C                Count characters. If this argument provided, report will
                      also include charater count
  -F=<files>        File to analyse. You can put multiple files like "F file1.
                      txt -F file2.txt"
  -S=<stopWords>    Words you want to skip during analysis. If more then one,
                      please separate by comma

```

sample execution:

```shell script
java -jar scraper-1.0-SNAPSHOT.jar -F ./text1.txt -F ./text2.txt -C -S and,the
```

sample output:
```shell script
File path: ./text1.txt - words count: 282 - char count: 1852 
File path: ./text2.txt - words count: 317 - char count: 2182 
```

#building
in order to build the jar file, please run:
```shell script
./gradlew jar
```

then jar file should be created under build/libs directory