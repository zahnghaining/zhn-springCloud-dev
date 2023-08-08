# 基础镜像
FROM  openjdk:8-jre
# author
MAINTAINER ex_weilingfeng@163.com

# 挂载目录
VOLUME /home/zhn
# 创建目录
RUN mkdir -p /home/zhn
# 指定路径
WORKDIR /home/zhn
# 复制jar文件到路径
COPY zhn-gateway/target/zhn-gateway.jar /home/zhn/zhn-gateway.jar
# 启动网关服务
ENTRYPOINT ["java","-jar","zhn-gateway.jar"]