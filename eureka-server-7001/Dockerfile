FROM java:8
ENV TZ=Asia/Shanghai
COPY eureka-server-7001-1.0-SNAPSHOT.jar eureka.jar
ENTRYPOINT ["java","-jar","/eureka.jar"]
EXPOSE 7001
