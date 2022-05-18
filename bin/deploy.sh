#!/bin/bash
set -e

DATE=$(date +%Y%m%d%H%M)
# 基础路径
BASE_PATH=/var/jenkins_home/workspace/tsingeye-iot/tsingeye-admin
# 编译后 jar 的地址。部署时，Jenkins 会上传 jar 包到该目录下
SOURCE_PATH=$BASE_PATH/target
# 服务名称。同时约定部署服务的 jar 包名字也为它。
SERVER_NAME=tsingeye-admin
# 环境
PROFILES_ACTIVE=local

# JVM 参数
JAVA_OPS="-Xms512m -Xmx512m"

# 备份
function backup() {
    # 如果不存在，则无需备份
    if [ ! -f "$BASE_PATH/$SERVER_NAME.jar" ]; then
        echo "[backup] $BASE_PATH/$SERVER_NAME.jar 不存在，跳过备份"
    # 如果存在，则备份到 backup 目录下，使用时间作为后缀
    else
        echo "[backup] 开始备份 $SERVER_NAME ..."
        cp $BASE_PATH/$SERVER_NAME.jar $BASE_PATH/backup/$SERVER_NAME-$DATE.jar
        echo "[backup] 备份 $SERVER_NAME 完成"
    fi
}

# 最新构建代码 移动到项目环境
function transfer() {
    echo "[transfer] 开始转移 $SERVER_NAME.jar"

    # 删除原 jar 包
    if [ ! -f "$BASE_PATH/$SERVER_NAME.jar" ]; then
        echo "[transfer] $BASE_PATH/$SERVER_NAME.jar 不存在，跳过删除"
    else
        echo "[transfer] 移除 $BASE_PATH/$SERVER_NAME.jar 完成"
        rm $BASE_PATH/$SERVER_NAME.jar
    fi

    # 复制新 jar 包
    echo "[transfer] 从 $SOURCE_PATH 中获取 $SERVER_NAME.jar 并迁移至 $BASE_PATH ...."
    cp $SOURCE_PATH/$SERVER_NAME.jar $BASE_PATH

    echo "[transfer] 转移 $SERVER_NAME.jar 完成"
}

# 停止
function stop() {
    echo "[stop] 开始停止 $SOURCE_PATH/$SERVER_NAME"
    PID=$(ps -ef | grep $SOURCE_PATH/$SERVER_NAME | grep -v "grep" | awk '{print $1}')
    # 如果 Java 服务启动中，则进行关闭
    if [ -n "$PID" ]; then
        # 正常关闭
        echo "[stop] $SOURCE_PATH/$SERVER_NAME 运行中，开始 kill [$PID]"
        kill -15 $PID
        # 等待最大 60 秒，直到关闭完成。
        for ((i = 0; i < 60; i++))
            do
                sleep 1
                PID=$(ps -ef | grep $SOURCE_PATH/$SERVER_NAME | grep -v "grep" | awk '{print $1}')
                if [ -n "$PID" ]; then
                    echo -e ".\c"
                else
                    echo '[stop] 停止 $SOURCE_PATH/$SERVER_NAME 成功'
                    break
                fi
		    done

        # 如果正常关闭失败，那么进行强制 kill -9 进行关闭
        if [ -n "$PID" ]; then
            echo "[stop] $SOURCE_PATH/$SERVER_NAME 失败，强制 kill -9 $PID"
            kill -9 $PID
        fi
    # 如果 Java 服务未启动，则无需关闭
    else
        echo "[stop] $SOURCE_PATH/$SERVER_NAME 未启动，无需停止"
    fi
}

# 启动
function start() {
    # 开启启动前，打印启动参数
    echo "[start] 开始启动 $SOURCE_PATH/$SERVER_NAME"
    echo "[start] JAVA_OPS: $JAVA_OPS"
    echo "[start] JAVA_AGENT: $JAVA_AGENT"
    echo "[start] PROFILES: $PROFILES_ACTIVE"


    chmod 755 $SOURCE_PATH/$SERVER_NAME.jar
#    chmod 777 /home/tsingeye/tsingeyeLogs
    # 开始启动
    JENKINS_NODE_COOKIE=dontKillMe nohup java -jar $JAVA_OPS $SOURCE_PATH/$SERVER_NAME.jar > $BASE_PATH/nohup.out 2>&1 &
    echo "[start] 启动 $SOURCE_PATH/$SERVER_NAME 完成"
}

# 部署
function deploy() {
    cd $BASE_PATH
    # 停止 Java 服务
    stop
    # 启动 Java 服务
    start
}

deploy
