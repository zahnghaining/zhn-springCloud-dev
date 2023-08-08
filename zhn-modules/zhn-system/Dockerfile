# 基础镜像
FROM  openjdk:8-jre
# author
MAINTAINER ex_weilingfeng

# 挂载目录
VOLUME /home/zhn
# 创建目录
RUN mkdir -p /home/zhn
# 指定路径
WORKDIR /home/zhn
# 复制jar文件到路径
COPY zhn-modules/zhn-system/target/zhn-modules-system.jar /home/zhn/zhn-modules-system.jar
# 启动系统服务
ENTRYPOINT ["java","-jar","zhn-modules-system.jar"]