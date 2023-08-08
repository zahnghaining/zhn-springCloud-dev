#!/bin/bash
echo "==============deploy begin!!!================"

UCLOUD_HUB=registry.cn-hangzhou.aliyuncs.com/weilingfeng-dev
UCLOUD_HUB=registry.cn-hangzhou.aliyuncs.com/weilingfeng-prod

TAG="${CI_COMMIT_SHA:0:4}${CI_PIPELINE_ID:0:4}"

ZHN_GATEWAY=zhn-gateway
ZHN_AUTH=zhn-auth
ZHN_SYSTEM=zhn-system

#停止
if [ -n "$(docker ps -a -q -f name=$ZHN_GATEWAY)" ]; then
    docker rm -f $ZHN_GATEWAY
    echo "$ZHN_GATEWAY 已经停止"
fi

if [ -n "$(docker ps -a -q -f name=$ZHN_AUTH)" ]; then
    docker rm -f $ZHN_AUTH
    echo "$ZHN_AUTH 已经停止"
fi

if [ -n "$(docker ps -a -q -f name=$ZHN_SYSTEM)" ]; then
    docker rm -f $ZHN_SYSTEM
    echo "$ZHN_SYSTEM 已经停止"
fi

#删除所有镜像
docker images | grep "zhn-gateway" | awk '{print $3}' | while read image_id; do docker rmi -f "$image_id"; done
docker images | grep "zhn-auth" | awk '{print $3}' | while read image_id; do docker rmi -f "$image_id"; done
docker images | grep "zhn-system" | awk '{print $3}' | while read image_id; do docker rmi -f "$image_id"; done


#拉取镜像
docker pull $UCLOUD_HUB/${ZHN_AUTH}:$TAG || exit 1
docker pull $UCLOUD_HUB/${ZHN_SYSTEM}:$TAG || exit 1
docker pull $UCLOUD_HUB/${ZHN_GATEWAY}:$TAG || exit 1

#发布
docker run -itd -p 8080:8080 --name=zhn-gateway --restart=always $UCLOUD_HUB/${ZHN_GATEWAY}:$TAG || exit 1
docker run -itd -p 9200:9200 --name=zhn-auth --restart=always $UCLOUD_HUB/${ZHN_AUTH}:$TAG || exit 1
docker run -itd -p 9201:9201 --name=zhn-system --restart=always $UCLOUD_HUB/${ZHN_SYSTEM}:$TAG || exit 1

echo "==============deploy success end!!!=================="


