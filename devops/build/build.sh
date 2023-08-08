#!/bin/bash
echo "==============build begin 当前环境:【$ENV】=============="
if [[ "$ENV" = "" ]]; then
    ENV=$env
fi

if [[ $ENV == 'test' ]]; then
    HUB_USERNAME=wlf19941002
    HUB_PASSWORD=901002wei
    UCLOUD_HUB=registry.cn-hangzhou.aliyuncs.com/weilingfeng-dev
  elif [[ $ENV == 'prod' ]]; then
    HUB_USERNAME=wlf19941002
    HUB_PASSWORD=901002wei
    UCLOUD_HUB=registry.cn-hangzhou.aliyuncs.com/weilingfeng-prod
  else
    echo 'Please enter right environment! eg: test || prod(master or v/V*.*.* branch)'
    exit 1
fi

#编译后将包拷贝至新建的publish目录下，gitlab上可以在对应节点下载包。
mvn clean package -Dmaven.test.skip=true || exit 1 && \
  echo 'build successful!'

# 构建 TAG 变量
TAG="${CI_COMMIT_SHA:0:4}${CI_PIPELINE_ID:0:4}"

# 打印 TAG 变量的值
echo "TAG value: $TAG"

ZHN_AUTH=zhn-auth
ZHN_GATEWAY=zhn-gateway
ZHN_SYSTEM=zhn-system

#构建镜像
docker build -f zhn-gateway/Dockerfile -t $UCLOUD_HUB/${ZHN_GATEWAY}:$TAG .
docker build -f zhn-auth/Dockerfile -t $UCLOUD_HUB/${ZHN_AUTH}:$TAG .
docker build -f zhn-modules/zhn-system/Dockerfile -t $UCLOUD_HUB/${ZHN_SYSTEM}:$TAG .
#镜像仓库登录
docker login registry.cn-hangzhou.aliyuncs.com --username $HUB_USERNAME --password $HUB_PASSWORD

#推送镜像
docker push $UCLOUD_HUB/${ZHN_AUTH}:$TAG
docker push $UCLOUD_HUB/${ZHN_GATEWAY}:$TAG
docker push $UCLOUD_HUB/${ZHN_SYSTEM}:$TAG

#删除镜像
docker rmi -f $UCLOUD_HUB/${ZHN_AUTH}:$TAG
docker rmi -f $UCLOUD_HUB/${ZHN_GATEWAY}:$TAG
docker push $UCLOUD_HUB/${ZHN_SYSTEM}:$TAG

#删除none 镜像
docker image prune -f --filter "dangling=true"

echo "$UCLOUD_HUB/${ZHN_AUTH}:$TAG"
echo "$UCLOUD_HUB/${ZHN_GATEWAY}:$TAG"
echo "$UCLOUD_HUB/${ZHN_SYSTEM}:$TAG"
echo "==============build end!!!================"
